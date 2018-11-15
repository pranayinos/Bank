/*
 * *
 *   * Copyright (c) ION Trading UK Limited 2015
 *   * All Rights reserved.
 *
 */

package com.main.bank.common.facade;

import com.main.bank.common.exception.UserException;
import com.main.bank.statement.model.TransactionDetail;

import java.math.BigDecimal;
import java.util.List;

public interface BankFacade {

    long createAccount(String name, BigDecimal initialDeposit);

    BigDecimal deleteAccount(long accountNumber) throws UserException;

    boolean transfer(long fromAccountNumber, long toAccountNumber, BigDecimal transferAmount) throws UserException;

    boolean deposit(long accountNumber, BigDecimal transferAmount) throws UserException;

    boolean withdraw(long accountNumber, BigDecimal transferAmount) throws UserException;

    BigDecimal getBalance(long accountNumber) throws UserException;

    List<TransactionDetail> getLast10Transactions(long accountNumber) throws UserException;

}
