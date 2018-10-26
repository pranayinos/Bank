/*
 * *
 *   * Copyright (c) ION Trading UK Limited 2015
 *   * All Rights reserved.
 *
 */

package com.bank.facade;

import java.math.BigDecimal;
import java.util.List;

import com.bank.exception.UserException;
import com.bank.model.TransactionDetail;

public interface BankFacade {

    long createAccount(String name, BigDecimal initialDeposit);

    BigDecimal deleteAccount(long accountNumber) throws UserException;

    boolean transfer(long fromAccountNumber, long toAccountNumber, BigDecimal transferAmount) throws UserException;

    boolean deposit(long accountNumber, BigDecimal transferAmount) throws UserException;

    boolean withdraw(long accountNumber, BigDecimal transferAmount) throws UserException;

    BigDecimal getBalance(long accountNumber) throws UserException;

    List<TransactionDetail> getLast10Transactions(long accountNumber) throws UserException;

}
