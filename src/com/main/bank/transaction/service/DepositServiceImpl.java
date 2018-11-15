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

public class DepositServiceImpl implements DepositService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionIdGenerator transactionIdGenerator;
    private final LockProvider lockProvider;
    private final ValidatorService validatorService;

    public DepositServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository,
                              TransactionIdGenerator transactionIdGenerator, LockProvider lockProvider, ValidatorService validatorService) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.transactionIdGenerator = transactionIdGenerator;
        this.lockProvider = lockProvider;
        this.validatorService = validatorService;
    }

    @Override
    public boolean deposit(long accountNumber, BigDecimal depositAmount) throws UserException {
        Account account = accountRepository.find(accountNumber);
        validatorService.validateAmount(depositAmount);
        Logger.info(this.getClass().getName(), " Depositing :" + depositAmount + " to account : " + this.toString());
        ReentrantLock accountLock = lockProvider.getReentrantLock(accountNumber);
        try {
            accountLock.tryLock(30, TimeUnit.SECONDS);
            account.addToBalance(depositAmount);
            TransactionDetail entry = new TransactionDetail(EntryType.CREDIT, transactionIdGenerator.generate(),
                    "Cash Deposit", depositAmount, account.getBalance());
            transactionRepository.save(accountNumber, entry);
        } catch (InterruptedException e) {
            throw new SystemException("Request timed out! Cannot process tranasction please try again");
        } finally {
            accountLock.unlock();
        }
        return true;
    }
}
