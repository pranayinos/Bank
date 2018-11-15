package com.main.bank.statement.service;

import com.main.bank.common.exception.UserException;
import com.main.bank.statement.model.TransactionDetail;
import com.main.bank.statement.persistance.TransactionRepository;

import java.util.List;

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
