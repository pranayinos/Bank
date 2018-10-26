/*
 * *
 *   * Copyright (c) ION Trading UK Limited 2015
 *   * All Rights reserved.
 *
 */

package com.bank.facade;

import java.math.BigDecimal;
import java.util.List;

import com.bank.exception.UserException;
import com.bank.model.TransactionDetail;
import com.bank.persistance.AccountRepository;
import com.bank.persistance.AccountRepositoryImpl;
import com.bank.persistance.TransactionRepository;
import com.bank.persistance.TransactionRepositoryImpl;
import com.bank.service.*;

public class BankFacadeImpl implements BankFacade {

    private final TransactionService transactionService;
    private final AccountService accountService;
    private final StatementService statementService;

    public BankFacadeImpl() {
        AccountRepository accountRepository = new AccountRepositoryImpl();
        TransactionRepository transactionRepository = new TransactionRepositoryImpl();
        this.transactionService = new TransactionServiceImpl(accountRepository, transactionRepository);
        this.accountService = new AccountServiceImpl(accountRepository, this.transactionService, transactionRepository);
        this.statementService = new StatementServiceImpl(transactionRepository);
    }

    @Override
    public long createAccount(String name, BigDecimal initialDeposit) {
        return accountService.createAccount(name, initialDeposit);
    }

    @Override
    public BigDecimal deleteAccount(long accountNumber) throws UserException {
        return accountService.deleteAccount(accountNumber);
    }

    @Override
    public boolean transfer(long fromAccountNumber, long toAccountNumber, BigDecimal transferAmount)
            throws UserException {
        return transactionService.transfer(fromAccountNumber, toAccountNumber, transferAmount);
    }

    @Override
    public boolean deposit(long accountNumber, BigDecimal transferAmount) throws UserException {
        return transactionService.deposit(accountNumber, transferAmount);
    }

    @Override
    public boolean withdraw(long accountNumber, BigDecimal transferAmount) throws UserException {
        return transactionService.withdraw(accountNumber, transferAmount);
    }

    @Override
    public BigDecimal getBalance(long accountNumber) throws UserException {
        return transactionService.getBalance(accountNumber);
    }

    @Override
    public List<TransactionDetail> getLast10Transactions(long accountNumber) throws UserException {
        return statementService.getLast10Transactions(accountNumber);
    }
}
