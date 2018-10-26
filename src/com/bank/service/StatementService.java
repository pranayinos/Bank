package com.bank.service;

import java.util.List;

import com.bank.exception.UserException;
import com.bank.model.TransactionDetail;

public interface StatementService {

    List<TransactionDetail> getLast10Transactions(long accountNumber) throws UserException;

}
