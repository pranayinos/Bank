/*
 * *
 *   * Copyright (c) ION Trading UK Limited 2015
 *   * All Rights reserved.
 *
 */

package com.bank.generator;

import java.util.UUID;

public class TransactionIdGeneratorImpl implements TransactionIdGenerator {

    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
