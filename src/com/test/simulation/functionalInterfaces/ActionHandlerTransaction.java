package com.test.simulation.functionalInterfaces;

import java.util.List;

import com.bank.exception.UserException;
import com.bank.model.TransactionDetail;

@FunctionalInterface
public interface ActionHandlerTransaction {
    List<TransactionDetail> execute() throws UserException;
}
