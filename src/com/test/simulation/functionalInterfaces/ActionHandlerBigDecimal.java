package com.test.simulation.functionalInterfaces;

import java.math.BigDecimal;

import com.bank.exception.UserException;

@FunctionalInterface
public interface ActionHandlerBigDecimal {
    BigDecimal execute() throws UserException;
}
