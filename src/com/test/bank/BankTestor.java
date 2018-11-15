/*
 * *
 *   * Copyright (c) ION Trading UK Limited 2015
 *   * All Rights reserved.
 *
 */

package com.test.bank;

import com.main.bank.common.facade.BankFacade;
import com.main.bank.common.facade.BankFacadeImpl;
import com.main.bank.common.utilities.Logger;
import com.test.bank.controller.CLIBankController;
import com.test.bank.controller.CLIBankControllerImpl;

import java.math.BigDecimal;

/*
FOR Simulation Purpose only
 */
public class BankTestor {

    private final CLIBankController bankController;

    public BankTestor() {
        BankFacade bankController = new BankFacadeImpl();
        this.bankController = new CLIBankControllerImpl(bankController);
    }

    public static void printToConsole(String output) {
        System.out.println(output);
    }

    public void runTest() {
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
