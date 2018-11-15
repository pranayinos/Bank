package com.main.bank.transaction.service.helper;

import java.util.concurrent.locks.ReentrantLock;

public interface LockProvider {
    ReentrantLock getReentrantLock(Long accountNumber);

    void clearLock(Long accountNumber);
}
