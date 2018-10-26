package com.bank.service;

import java.util.List;

import com.bank.exception.UserException;
import com.bank.model.TransactionDetail;
import com.bank.persistance.TransactionRepository;

public class StatementServiceImpl implements StatementService {

    private final TransactionRepository transactionRepository;

    public StatementServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<TransactionDetail> getLast10Transactions(long accountNumber) throws UserException {
        return transactionRepository.findLast10Transaction(accountNumber);

    }

}
