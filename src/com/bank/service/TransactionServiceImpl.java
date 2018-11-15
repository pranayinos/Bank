package com.bank.service;

import java.math.BigDecimal;
import java.util.Comparator;

import com.bank.exception.UserException;
import com.bank.generator.TransactionIdGenerator;
import com.bank.model.Account;
import com.bank.model.EntryType;
import com.bank.model.TransactionDetail;
import com.bank.persistance.AccountRepository;
import com.bank.persistance.TransactionRepository;
import com.bank.utilities.Logger;
import com.bank.utilities.Validator;

public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionIdGenerator transactionIdGenerator;

    public TransactionServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository,
            TransactionIdGenerator transactionIdGenerator) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.transactionIdGenerator = transactionIdGenerator;
    }

    @Override
    public BigDecimal getBalance(long accountNumber) throws UserException {
        Account account = accountRepository.find(accountNumber);
        return account.getBalance();
    }

    @Override
    public boolean transfer(Long fromAccountNumber, Long toAccountNumber, BigDecimal transferAmount)
            throws UserException {
        if (fromAccountNumber.equals(toAccountNumber)) {
            throw new UserException("Entered account numbers cannot be same");
        }
        Account fromAccount = accountRepository.find(fromAccountNumber);
        Account toAccount = accountRepository.find(toAccountNumber);
        Validator.verifyAmount(transferAmount);
        Logger.info(this.getClass().getName(),
            "Transferring : " + transferAmount.toPlainString() + ", to Account : " + toAccount.toString());

        Object lastLock, firstLock;
        if (Comparator.comparing(Account::getAccountNumber).compare(fromAccount, toAccount) < 0) {
            firstLock = fromAccount;
            lastLock = toAccount;
        } else {
            firstLock = toAccount;
            lastLock = fromAccount;
        }
        synchronized (firstLock) {
            synchronized (lastLock) {
                accountHasSufficientBalance(fromAccount, transferAmount);
                this.withdraw(fromAccountNumber, transferAmount);
                this.deposit(toAccountNumber, transferAmount);
            }
        }
        return true;
    }

    @Override
    public boolean deposit(long accountNumber, BigDecimal depositAmount) throws UserException {
        Account account = accountRepository.find(accountNumber);
        Validator.verifyAmount(depositAmount);
        Logger.info(this.getClass().getName(), " Depositing :" + depositAmount + " to account : " + this.toString());
        synchronized (account) {
            account.addToBalance(depositAmount);
            TransactionDetail entry = new TransactionDetail(EntryType.CREDIT, transactionIdGenerator.generate(),
                    "Cash Deposit", depositAmount, account.getBalance());
            transactionRepository.save(accountNumber, entry);
        }
        return true;
    }

    @Override
    public boolean withdraw(long accountNumber, BigDecimal withdrawalAmount) throws UserException {
        Account account = accountRepository.find(accountNumber);
        Validator.verifyAmount(withdrawalAmount);
        accountHasSufficientBalance(account, withdrawalAmount);
        Logger.info(this.getClass().getName(),
            " Withdrawing :" + withdrawalAmount + " from account : " + this.toString());
        synchronized (account) {
            account.subtractFromBalance(withdrawalAmount);
            TransactionDetail entry = new TransactionDetail(EntryType.DEBIT, transactionIdGenerator.generate(),
                    "Cash Withdrawal", withdrawalAmount, account.getBalance());
            transactionRepository.save(accountNumber, entry);
        }
        return true;
    }

    private void accountHasSufficientBalance(Account account, BigDecimal amount) throws UserException {
        if (account.getBalance().compareTo(amount) < 0) {
            throw new UserException("Insufficient funds, cannot process txn of : " + amount.toPlainString());
        }
    }
}
