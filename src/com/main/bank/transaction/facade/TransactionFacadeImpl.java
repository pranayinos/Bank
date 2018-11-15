package com.main.bank.transaction.facade;

import com.main.bank.common.exception.UserException;
import com.main.bank.transaction.service.BalanceService;
import com.main.bank.transaction.service.DepositService;
import com.main.bank.transaction.service.TransferService;
import com.main.bank.transaction.service.WithdrawlService;

import java.math.BigDecimal;

public class TransactionFacadeImpl implements TransactionFacade {

    private final BalanceService balanceService;
    private final DepositService depositService;
    private final WithdrawlService withdrawlService;
    private final TransferService transferService;

    public TransactionFacadeImpl(BalanceService balanceService, DepositService depositService, WithdrawlService withdrawlService, TransferService transferService) {
        this.balanceService = balanceService;
        this.depositService = depositService;
        this.withdrawlService = withdrawlService;
        this.transferService = transferService;
    }

    @Override
    public BigDecimal getBalance(long accountNumber) throws UserException {
        return balanceService.getBalance(accountNumber);
    }

    @Override
    public boolean transfer(Long fromAccountNumber, Long toAccountNumber, BigDecimal transferAmount)
            throws UserException {
        return transferService.transfer(fromAccountNumber, toAccountNumber, transferAmount);

    }

    @Override
    public boolean deposit(long accountNumber, BigDecimal transferAmount) throws UserException {
        return depositService.deposit(accountNumber, transferAmount);
    }

    @Override
    public boolean withdraw(long accountNumber, BigDecimal transferAmount) throws UserException {
        return withdrawlService.withdraw(accountNumber, transferAmount);
    }

}
