package com.main.bank.transaction.service;

import com.main.bank.common.exception.UserException;

import java.math.BigDecimal;

public interface DepositService {
    boolean deposit(long accountNumber, BigDecimal depositAmount) throws UserException;
}
