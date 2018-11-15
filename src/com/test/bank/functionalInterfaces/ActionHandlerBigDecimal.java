package com.test.bank.functionalInterfaces;

import com.main.bank.common.exception.UserException;

import java.math.BigDecimal;

@FunctionalInterface
public interface ActionHandlerBigDecimal {
    BigDecimal execute() throws UserException;
}
