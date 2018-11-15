package com.test.bank.functionalInterfaces;

import com.main.bank.common.exception.UserException;
import com.main.bank.statement.model.TransactionDetail;

import java.util.List;

@FunctionalInterface
public interface ActionHandlerTransaction {
    List<TransactionDetail> execute() throws UserException;
}
