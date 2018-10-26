/*
 * *
 *   * Copyright (c) ION Trading UK Limited 2015
 *   * All Rights reserved.
 *
 */

package com.bank.utilities;

import java.math.BigDecimal;

import com.bank.exception.UserException;

public class Validator {
    public static void verifyAmount(BigDecimal transferAmount) throws UserException {
        if (transferAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new UserException("Transfer amount cannot be null or negative : " + transferAmount);
        }
    }
}
