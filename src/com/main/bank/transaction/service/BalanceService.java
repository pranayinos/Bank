package com.main.bank.transaction.service;

import com.main.bank.common.exception.UserException;

import java.math.BigDecimal;

public interface BalanceService {
    BigDecimal getBalance(long accountNumber) throws UserException;
}
