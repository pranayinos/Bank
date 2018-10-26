/*
 * *
 *   * Copyright (c) ION Trading UK Limited 2015
 *   * All Rights reserved.
 *
 */

package com.test.simulation;

import java.math.BigDecimal;

import com.bank.common.Logger;
import com.bank.facade.BankFacade;
import com.bank.facade.BankFacadeImpl;
import com.test.simulation.controller.BankController;
import com.test.simulation.controller.BankControllerImpl;

public class BankSimulator {

    private final BankController bankController;

    public BankSimulator() {
        BankFacade bankController = new BankFacadeImpl();
        this.bankController = new BankControllerImpl(bankController);
    }

    public static void printToConsole(String output) {
        System.out.println(output);
    }

    public void runSimulation() {
        Logger.info(this.getClass().getName(), " Starting Simulation ");

        long accountOne = bankController.createAccount("Tester One", new BigDecimal(2000.00));
        long accountTwo = bankController.createAccount("Tester Two", new BigDecimal(4000.00));
        printToConsole(bankController.deposit(accountOne, new BigDecimal(1200.00)));
        printToConsole(bankController.deposit(accountTwo, new BigDecimal(200.00)));
        printToConsole(bankController.withdraw(accountOne, new BigDecimal(100.00)));
        printToConsole(bankController.withdraw(accountTwo, new BigDecimal(3000.00)));
        printToConsole(bankController.transfer(accountOne, accountTwo, new BigDecimal(111)));
        printToConsole(bankController.transfer(accountTwo, accountOne, new BigDecimal(1000)));
        printToConsole(bankController.getBalance(accountTwo));
        printToConsole(bankController.transfer(accountOne, accountTwo, new BigDecimal(1010)));
        printToConsole(bankController.getLast10Transactions(accountOne));
        printToConsole(bankController.getLast10Transactions(accountTwo));
        printToConsole(bankController.deposit(accountTwo, new BigDecimal(1200.00)));
        printToConsole(bankController.deposit(accountTwo, new BigDecimal(1200.00)));
        printToConsole(bankController.deposit(accountTwo, new BigDecimal(1200.00)));
        printToConsole(bankController.deposit(accountTwo, new BigDecimal(1200.00)));
        printToConsole(bankController.deposit(accountTwo, new BigDecimal(1200.00)));
        printToConsole(bankController.deposit(accountTwo, new BigDecimal(1200.00)));
        printToConsole(bankController.deposit(accountTwo, new BigDecimal(1200.00)));
        printToConsole(bankController.deposit(accountOne, new BigDecimal(1200.00)));
        printToConsole(bankController.deposit(accountOne, new BigDecimal(1200.00)));
        printToConsole(bankController.transfer(accountTwo, accountOne, new BigDecimal(2300)));
        printToConsole(bankController.getBalance(accountTwo));
        printToConsole(bankController.getLast10Transactions(accountOne));
        printToConsole(bankController.getLast10Transactions(accountTwo));
        printToConsole(bankController.deleteAccount(accountTwo));

        Logger.info(this.getClass().getName(), " Simulation Completed ");
    }

}
