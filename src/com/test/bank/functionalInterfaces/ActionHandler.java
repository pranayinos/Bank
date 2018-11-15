package com.test.bank.functionalInterfaces;

import com.main.bank.common.exception.UserException;

@FunctionalInterface
public interface ActionHandler {
    boolean execute() throws UserException;
}
