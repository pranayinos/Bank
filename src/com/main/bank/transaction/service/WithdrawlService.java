package com.main.bank.transaction.service;

import com.main.bank.common.exception.UserException;

import java.math.BigDecimal;

public interface WithdrawlService {
    boolean withdraw(long accountNumber, BigDecimal withdrawalAmount) throws UserException;
}
