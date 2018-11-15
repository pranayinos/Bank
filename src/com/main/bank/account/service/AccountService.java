package com.main.bank.account.service;

import com.main.bank.common.exception.UserException;

import java.math.BigDecimal;

public interface AccountService {

    long createAccount(String name, BigDecimal openingBalance);

    BigDecimal deleteAccount(long accountNumber) throws UserException;

}
