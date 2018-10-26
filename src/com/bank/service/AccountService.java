package com.bank.service;

import java.math.BigDecimal;

import com.bank.exception.UserException;

public interface AccountService {

    long createAccount(String name, BigDecimal openingBalance);

    BigDecimal deleteAccount(long accountNumber) throws UserException;

}
