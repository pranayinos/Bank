package com.main.bank.statement.persistance;

import com.main.bank.common.exception.UserException;
import com.main.bank.statement.model.TransactionDetail;

import java.util.List;

public interface TransactionRepository {

    List<TransactionDetail> findLast10Transaction(long accountNumber) throws UserException;

    void save(Long accountNumber, TransactionDetail transactionDetail);

}
