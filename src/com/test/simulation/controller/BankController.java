package com.test.simulation.controller;

import java.math.BigDecimal;

public interface BankController {

    long createAccount(String name, BigDecimal initialDeposit);

    String deleteAccount(long accountNumber);

    String transfer(long fromAccountNumber, long toAccountNumber, BigDecimal transferAmount);

    String deposit(long accountNumber, BigDecimal transferAmount);

    String withdraw(long accountNumber, BigDecimal transferAmount);

    String getBalance(long accountNumber);

    String getLast10Transactions(long accountNumber);
}
