package com.bank.persistance;

import java.util.List;

import com.bank.exception.UserException;
import com.bank.model.TransactionDetail;

public interface TransactionRepository {

    List<TransactionDetail> findLast10Transaction(long accountNumber) throws UserException;

    void save(Long accountNumber, TransactionDetail transactionDetail);

}
