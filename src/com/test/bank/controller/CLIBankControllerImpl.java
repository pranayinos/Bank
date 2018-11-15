package com.test.bank.controller;

import com.main.bank.common.facade.BankFacade;
import com.test.bank.utilities.CurrencyFormatter;
import com.test.bank.utilities.ResultPrinter;

import java.math.BigDecimal;

/*
FOR Simulation Purpose only
 */
public class CLIBankControllerImpl implements CLIBankController {

    private final static String FAILED_TXN_MESSAGE = "Sorry! Couldn't process your transaction";
    private final BankFacade bankFacade;

    public CLIBankControllerImpl(BankFacade bankFacade) {
        this.bankFacade = bankFacade;
    }

    @Override
    public long createAccount(String name, BigDecimal initialDeposit) {
        return bankFacade.createAccount(name, initialDeposit);
    }

    @Override
    public String deleteAccount(long accountNumber) {
        return ResultPrinter.executeAndHandleExceptions(() -> bankFacade.deleteAccount(accountNumber),
                "Account #" + accountNumber + " deleted Successfully!! \n \tFinal withdrawal amount :");
    }

    @Override
    public String transfer(long fromAccountNumber, long toAccountNumber, BigDecimal transferAmount) {
        return ResultPrinter.executeAndHandleExceptions(
                () -> bankFacade.transfer(fromAccountNumber, toAccountNumber, transferAmount),
                "Funds transfer of " + CurrencyFormatter.formatNumberToCurrency(transferAmount) + " Successful!",
                FAILED_TXN_MESSAGE);
    }

    @Override
    public String deposit(long accountNumber, BigDecimal transferAmount) {
        return ResultPrinter.executeAndHandleExceptions(() -> bankFacade.deposit(accountNumber, transferAmount),
                "Deposit Successful", FAILED_TXN_MESSAGE);
    }

    @Override
    public String withdraw(long accountNumber, BigDecimal transferAmount) {
        return ResultPrinter.executeAndHandleExceptions(() -> bankFacade.withdraw(accountNumber, transferAmount),
                "Withdrawl Successful. ", FAILED_TXN_MESSAGE);
    }

    @Override
    public String getBalance(long accountNumber) {
        return ResultPrinter.executeAndHandleExceptions(() -> bankFacade.getBalance(accountNumber),
                "Your current balance is : ");
    }

    @Override
    public String getLast10Transactions(long accountNumber) {
        return ResultPrinter.executeAndHandleExceptions(() -> bankFacade.getLast10Transactions(accountNumber));
    }
}
