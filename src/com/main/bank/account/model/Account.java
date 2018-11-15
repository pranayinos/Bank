/*
 * *
 *   * Copyright (c) ION Trading UK Limited 2015
 *   * All Rights reserved.
 *
 */

package com.main.bank.account.model;

import java.math.BigDecimal;

public interface Account {

    Long getAccountNumber();

    BigDecimal getBalance();

    void addToBalance(BigDecimal amount);

    void subtractFromBalance(BigDecimal amount);

    String getName();

}
