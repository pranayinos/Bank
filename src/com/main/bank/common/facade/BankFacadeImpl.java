/*
 * *
 *   * Copyright (c) ION Trading UK Limited 2015
 *   * All Rights reserved.
 *
 */

package com.main.bank.common.facade;

import com.main.bank.account.persistance.AccountRepository;
import com.main.bank.account.persistance.AccountRepositoryImpl;
import com.main.bank.account.service.AccountNumberGenerator;
import com.main.bank.account.service.AccountNumberGeneratorImpl;
import com.main.bank.account.service.AccountService;
import com.main.bank.account.service.AccountServiceImpl;
import com.main.bank.common.exception.UserException;
import com.main.bank.statement.model.TransactionDetail;
import com.main.bank.statement.persistance.TransactionRepository;
import com.main.bank.statement.persistance.TransactionRepositoryImpl;
import com.main.bank.statement.service.StatementService;
import com.main.bank.statement.service.StatementServiceImpl;
import com.main.bank.statement.service.TransactionIdGenerator;
import com.main.bank.statement.service.TransactionIdGeneratorImpl;
import com.main.bank.transaction.facade.TransactionFacade;
import com.main.bank.transaction.facade.TransactionFacadeImpl;
import com.main.bank.transaction.service.*;
import com.main.bank.transaction.service.helper.LockProvider;
import com.main.bank.transaction.service.helper.LockProviderImpl;
import com.main.bank.transaction.service.helper.ValidatorService;
import com.main.bank.transaction.service.helper.ValidatorServiceImpl;

import java.math.BigDecimal;
import java.util.List;

public class BankFacadeImpl implements BankFacade {

    private final TransactionFacade transactionFacade;
    private final AccountService accountService;
    private final StatementService statementService;

    public BankFacadeImpl() {
        final AccountRepository accountRepository = new AccountRepositoryImpl();
        final TransactionRepository transactionRepository = new TransactionRepositoryImpl();
        final AccountNumberGenerator accountNumberGenerator = new AccountNumberGeneratorImpl();
        final TransactionIdGenerator transactionIdGenerator = new TransactionIdGeneratorImpl();
        final LockProvider lockProvider = new LockProviderImpl();
        final ValidatorService validatorService = new ValidatorServiceImpl();
        final BalanceService balanceService = new BalanceServiceImpl(accountRepository, transactionRepository, lockProvider);
        final DepositService depositService = new DepositServiceImpl(accountRepository, transactionRepository, transactionIdGenerator, lockProvider, validatorService);
        final WithdrawlService withdrawlService = new WithdrawlServiceImpl(accountRepository, transactionRepository, transactionIdGenerator, lockProvider, validatorService);
        ;
        final TransferService transferService = new TransferServiceImpl(accountRepository, depositService, withdrawlService, lockProvider, validatorService);
        ;

        this.transactionFacade = new TransactionFacadeImpl(balanceService, depositService, withdrawlService, transferService);
        this.accountService = new AccountServiceImpl(this.transactionFacade, accountRepository, transactionRepository,
                accountNumberGenerator, transactionIdGenerator);
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
        return transactionFacade.transfer(fromAccountNumber, toAccountNumber, transferAmount);
    }

    @Override
    public boolean deposit(long accountNumber, BigDecimal transferAmount) throws UserException {
        return transactionFacade.deposit(accountNumber, transferAmount);
    }

    @Override
    public boolean withdraw(long accountNumber, BigDecimal transferAmount) throws UserException {
        return transactionFacade.withdraw(accountNumber, transferAmount);
    }

    @Override
    public BigDecimal getBalance(long accountNumber) throws UserException {
        return transactionFacade.getBalance(accountNumber);
    }

    @Override
    public List<TransactionDetail> getLast10Transactions(long accountNumber) throws UserException {
        return statementService.getLast10Transactions(accountNumber);
    }
}
