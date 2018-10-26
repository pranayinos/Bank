package com.bank.service;

import java.math.BigDecimal;

import com.bank.exception.UserException;

public interface TransactionService {

    BigDecimal getBalance(long accountNumber) throws UserException;

    boolean transfer(Long fromAccount, Long toAccount, BigDecimal transferAmount) throws UserException;

    boolean deposit(long accountNumber, BigDecimal transferAmount) throws UserException;

    boolean withdraw(long accountNumber, BigDecimal transferAmount) throws UserException;

}
