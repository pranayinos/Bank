package com.bank.model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

public class SavingsAccount implements Account {

    private final long accountNumber;
    private final String name;
    private BigDecimal balance;
    private final ReentrantLock lock;

    public SavingsAccount(long accountNumber, String name, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
        this.lock = new ReentrantLock();
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
        SavingsAccount that = (SavingsAccount) o;
        return accountNumber == that.accountNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }

    @Override
    public String toString() {
        return "SavingsAccount{" +
                "accountNumber=" + accountNumber +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
