package com.main.bank.account.model;

import java.math.BigDecimal;
import java.util.Objects;

public class AccountImpl implements Account {

    private final long accountNumber;
    private final String name;
    private BigDecimal balance;

    public AccountImpl(long accountNumber, String name, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    @Override
    public Long getAccountNumber() {
        return this.accountNumber;
    }

    @Override
    public BigDecimal getBalance() {
        return this.balance;
    }

    @Override
    public void addToBalance(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    @Override
    public void subtractFromBalance(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AccountImpl that = (AccountImpl) o;
        return accountNumber == that.accountNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }

    @Override
    public String toString() {
        return "AccountImpl{" +
                "accountNumber=" + accountNumber +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
