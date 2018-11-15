package com.main.bank.transaction.service;

import com.main.bank.account.persistance.AccountRepository;
import com.main.bank.common.exception.UserException;
import com.main.bank.transaction.service.helper.LockProvider;
import com.main.bank.transaction.service.helper.ValidatorService;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TransferServiceImpl implements TransferService {

    private final AccountRepository accountRepository;
    private final DepositService depositService;
    private final WithdrawlService withdrawlServicel;
    private final ValidatorService validatorService;
    private final LockProvider lockProvider;

    public TransferServiceImpl(AccountRepository accountRepository, DepositService depositService, WithdrawlService withdrawlServicel, LockProvider lockProvider, ValidatorService validatorService) {
        this.accountRepository = accountRepository;
        this.depositService = depositService;
        this.withdrawlServicel = withdrawlServicel;
        this.validatorService = validatorService;
        this.lockProvider = lockProvider;
    }

    @Override
    public boolean transfer(Long fromAccountNumber, Long toAccountNumber, BigDecimal transferAmount) throws UserException {
        if (fromAccountNumber.equals(toAccountNumber)) {
            throw new UserException("Account numbers cannot be same to a/c#: " + toAccountNumber + " from a/c#: " + fromAccountNumber);
        }
        validatorService.validateAmount(transferAmount);
        ReentrantLock toAccountLock = lockProvider.getReentrantLock(toAccountNumber);
        ReentrantLock fromAccountLock = lockProvider.getReentrantLock(fromAccountNumber);
        int retryCount = 0;
        while (retryCount < 4) {
            fromAccountLock.lock();
            try {
                if (toAccountLock.tryLock(30, TimeUnit.SECONDS)) {
                    withdrawlServicel.withdraw(fromAccountNumber, transferAmount);
                    depositService.deposit(toAccountNumber, transferAmount);
                    break;
                } else {
                    fromAccountLock.unlock();
                }
            } catch (InterruptedException e) {
                System.out.println("Cannot acquire lock retrying..");
            }
            retryCount++;
        }
        if (retryCount <= 4) {
            throw new UserException("Cannot process transaction. Please try after sometime");
        } else {
            return true;
        }
    }
}