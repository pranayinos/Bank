package com.main.bank.statement.service;

import com.main.bank.common.exception.UserException;
import com.main.bank.statement.model.TransactionDetail;

import java.util.List;

public interface StatementService {

    List<TransactionDetail> getLast10Transactions(long accountNumber) throws UserException;

}
