package com.main.bank.transaction.service;

import com.main.bank.common.exception.UserException;

import java.math.BigDecimal;

public interface TransferService {

    boolean transfer(Long fromAccount, Long toAccount, BigDecimal transferAmount) throws UserException;

}
