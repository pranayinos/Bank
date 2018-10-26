package com.bank.simulation.functionalInterfaces;

import com.bank.exception.UserException;

@FunctionalInterface
public interface ActionHandler {
    boolean execute() throws UserException;
}
