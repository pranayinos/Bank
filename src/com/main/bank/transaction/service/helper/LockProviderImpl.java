package com.main.bank.transaction.service.helper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class LockProviderImpl implements LockProvider {

    private final Map<Long, ReentrantLock> accountLockMap;

    public LockProviderImpl() {
        this.accountLockMap = new ConcurrentHashMap<>();
    }

    @Override
    public ReentrantLock getReentrantLock(Long accountNumber) {
        this.accountLockMap.computeIfAbsent(accountNumber, aLong -> new ReentrantLock());
        return accountLockMap.get(accountNumber);
    }

    @Override
    public void clearLock(Long accountNumber) {
        ReentrantLock lock = accountLockMap.get(accountNumber);
        lock.tryLock();
        this.accountLockMap.remove(accountNumber);
        lock.unlock();
        lock = null;
    }
}
