package com.main.bank.account.service;

import com.main.bank.account.model.Account;
import com.main.bank.account.model.AccountImpl;
import com.main.bank.account.persistance.AccountRepository;
import com.main.bank.common.exception.UserException;
import com.main.bank.statement.model.EntryType;
import com.main.bank.statement.model.TransactionDetail;
import com.main.bank.statement.persistance.TransactionRepository;
import com.main.bank.statement.service.TransactionIdGenerator;
import com.main.bank.transaction.facade.TransactionFacade;

import java.math.BigDecimal;

public class AccountServiceImpl implements AccountService {

    private final TransactionFacade transactionFacade;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final AccountNumberGenerator accountNumberGenerator;
    private final TransactionIdGenerator transactionIdGenerator;

    public AccountServiceImpl(TransactionFacade transactionFacade, AccountRepository accountRepository,
                              TransactionRepository transactionRepository, AccountNumberGenerator accountNumberGenerator,
                              TransactionIdGenerator transactionIdGenerator) {
        this.transactionFacade = transactionFacade;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.accountNumberGenerator = accountNumberGenerator;
        this.transactionIdGenerator = transactionIdGenerator;
    }

    @Override
    public long createAccount(String name, BigDecimal openingBalance) {
        Account newAccount = new AccountImpl(accountNumberGenerator.generate(), name, openingBalance);
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
        transactionFacade.withdraw(accountNumber, accountBalance);
        accountRepository.delete(accountNumber);
        return accountBalance;
    }

}
