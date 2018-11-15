package com.main.bank.transaction.service;

import com.main.bank.account.model.Account;
import com.main.bank.account.persistance.AccountRepository;
import com.main.bank.common.exception.SystemException;
import com.main.bank.common.exception.UserException;
import com.main.bank.common.utilities.Logger;
import com.main.bank.statement.model.EntryType;
import com.main.bank.statement.model.TransactionDetail;
import com.main.bank.statement.persistance.TransactionRepository;
import com.main.bank.statement.service.TransactionIdGenerator;
import com.main.bank.transaction.service.helper.LockProvider;
import com.main.bank.transaction.service.helper.ValidatorService;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class WithdrawlServiceImpl implements WithdrawlService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionIdGenerator transactionIdGenerator;
    private final LockProvider lockProvider;
    private final ValidatorService validatorService;


    public WithdrawlServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository,
                                TransactionIdGenerator transactionIdGenerator, LockProvider lockProvider, ValidatorService validatorService) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.transactionIdGenerator = transactionIdGenerator;
        this.lockProvider = lockProvider;
        this.validatorService = validatorService;
    }

    @Override
    public boolean withdraw(long accountNumber, BigDecimal withdrawalAmount) throws UserException {
        Account account = accountRepository.find(accountNumber);
        validatorService.validateAmount(withdrawalAmount);
        accountHasSufficientBalance(account, withdrawalAmount);
        Logger.info(this.getClass().getName(),
                " Withdrawing :" + withdrawalAmount + " from account : " + this.toString());
        ReentrantLock accountLock = lockProvider.getReentrantLock(accountNumber);
        try {
            accountLock.tryLock(30, TimeUnit.SECONDS);
            account.subtractFromBalance(withdrawalAmount);
            TransactionDetail entry = new TransactionDetail(EntryType.DEBIT, transactionIdGenerator.generate(),
                    "Cash Withdrawal", withdrawalAmount, account.getBalance());
            transactionRepository.save(accountNumber, entry);
        } catch (InterruptedException e) {
            throw new SystemException("Request timed out! Cannot process tranasction please try again");
        } finally {
            accountLock.unlock();
        }
        return true;
    }

    private void accountHasSufficientBalance(Account account, BigDecimal amount) throws UserException {
        if (account.getBalance().compareTo(amount) < 0) {
            throw new UserException("Insufficient funds, cannot process txn of : " + amount.toPlainString());
        }
    }

}
