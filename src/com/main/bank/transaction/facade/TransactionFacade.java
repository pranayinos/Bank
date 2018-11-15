package com.main.bank.transaction.facade;

import com.main.bank.common.exception.UserException;

import java.math.BigDecimal;

public interface TransactionFacade {

    BigDecimal getBalance(long accountNumber) throws UserException;

    boolean transfer(Long fromAccount, Long toAccount, BigDecimal transferAmount) throws UserException;

    boolean deposit(long accountNumber, BigDecimal transferAmount) throws UserException;

    boolean withdraw(long accountNumber, BigDecimal transferAmount) throws UserException;

}
