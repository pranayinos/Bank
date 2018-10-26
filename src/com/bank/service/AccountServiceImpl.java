package com.bank.service;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import com.bank.exception.UserException;
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
    private final AtomicLong lastGeneratedNumber = new AtomicLong(10000000000L);

    public AccountServiceImpl(AccountRepository accountRepository, TransactionService transactionService,
            TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public long createAccount(String name, BigDecimal openingBalance) {
        Account newAccount = new SavingsAccount(lastGeneratedNumber.incrementAndGet(), name, openingBalance);
        TransactionDetail firstTransactionDetail = new TransactionDetail(EntryType.CREDIT, UUID.randomUUID().toString(),
                "OPENING-INITIAL-DEPOSIT", openingBalance, openingBalance);
        accountRepository.save(newAccount);
        transactionRepository.save(newAccount.getAccountNumber(), firstTransactionDetail);
        return newAccount.getAccountNumber();
    }

    @Override
    public BigDecimal deleteAccount(long accountNumber) throws UserException {
        Account account = accountRepository.find(accountNumber);
        transactionService.withdraw(accountNumber, account.getBalance());
        accountRepository.delete(accountNumber);
        return account.getBalance();
    }

}
