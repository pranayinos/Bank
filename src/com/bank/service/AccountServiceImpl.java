package com.bank.service;

import java.math.BigDecimal;

import com.bank.exception.UserException;
import com.bank.generator.AccountNumberGenerator;
import com.bank.generator.TransactionIdGenerator;
import com.bank.model.Account;
import com.bank.model.EntryType;
import com.bank.model.SavingsAccount;
import com.bank.model.TransactionDetail;
import com.bank.persistance.AccountRepository;
import com.bank.persistance.TransactionRepository;

public class AccountServiceImpl implements AccountService {

    private final TransactionService transactionService;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final AccountNumberGenerator accountNumberGenerator;
    private final TransactionIdGenerator transactionIdGenerator;

    public AccountServiceImpl(TransactionService transactionService, AccountRepository accountRepository,
            TransactionRepository transactionRepository, AccountNumberGenerator accountNumberGenerator,
            TransactionIdGenerator transactionIdGenerator) {
        this.transactionService = transactionService;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.accountNumberGenerator = accountNumberGenerator;
        this.transactionIdGenerator = transactionIdGenerator;
    }

    @Override
    public long createAccount(String name, BigDecimal openingBalance) {
        Account newAccount = new SavingsAccount(accountNumberGenerator.generate(), name, openingBalance);
        TransactionDetail firstTransactionDetail = new TransactionDetail(EntryType.CREDIT,
                transactionIdGenerator.generate().toString(),
                "OPENING-INITIAL-DEPOSIT", openingBalance, openingBalance);
        accountRepository.save(newAccount);
        transactionRepository.save(newAccount.getAccountNumber(), firstTransactionDetail);
        return newAccount.getAccountNumber();
    }

    @Override
    public BigDecimal deleteAccount(long accountNumber) throws UserException {
        BigDecimal accountBalance = accountRepository.find(accountNumber).getBalance();
        transactionService.withdraw(accountNumber, accountBalance);
        accountRepository.delete(accountNumber);
        return accountBalance;
    }

}
