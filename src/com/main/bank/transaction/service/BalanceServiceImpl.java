package com.main.bank.transaction.service;

import com.main.bank.account.model.Account;
import com.main.bank.account.persistance.AccountRepository;
import com.main.bank.common.exception.UserException;
import com.main.bank.statement.persistance.TransactionRepository;
import com.main.bank.transaction.service.helper.LockProvider;

import java.math.BigDecimal;

public class BalanceServiceImpl implements BalanceService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final LockProvider lockProvider;

    public BalanceServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository, LockProvider lockProvider) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.lockProvider = lockProvider;
    }

    @Override
    public BigDecimal getBalance(long accountNumber) throws UserException {
        Account account = accountRepository.find(accountNumber);
        return account.getBalance();
    }

}
