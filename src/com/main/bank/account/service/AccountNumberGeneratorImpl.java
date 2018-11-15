/*
 * *
 *   * Copyright (c) ION Trading UK Limited 2015
 *   * All Rights reserved.
 *
 */

package com.main.bank.account.service;

import java.util.concurrent.atomic.AtomicLong;

public class AccountNumberGeneratorImpl implements AccountNumberGenerator {

    private final AtomicLong lastGeneratedNumber = new AtomicLong(10000000000L);

    @Override
    public long generate() {
        return lastGeneratedNumber.incrementAndGet();
    }
}
