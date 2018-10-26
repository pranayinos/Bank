package com.bank.exception;

@FunctionalInterface
public interface ActionHandler {
    void execute() throws UserException;
}
