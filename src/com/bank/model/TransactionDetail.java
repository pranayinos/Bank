/*
 * *
 *   * Copyright (c) ION Trading UK Limited 2015
 *   * All Rights reserved.
 *
 */

package com.bank.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jdk.nashorn.internal.ir.annotations.Immutable;

@Immutable
public class TransactionDetail implements Comparable<TransactionDetail> {

    private final EntryType transactionType;
    private final String transactionId;
    private final LocalDateTime transactionDate;
    private final String narration;
    private final BigDecimal amount;
    private final BigDecimal closingBalance;

    public TransactionDetail(EntryType transactionType, String transactionId, String narration, BigDecimal amount,
            BigDecimal closingBalance) {
        this.transactionType = transactionType;
        this.transactionId = transactionId;
        this.transactionDate = LocalDateTime.now();
        this.narration = narration;
        this.amount = amount;
        this.closingBalance = closingBalance;
    }

    public TransactionDetail(TransactionDetail transactionDetail) {
        this.transactionType = transactionDetail.transactionType;
        this.transactionId = transactionDetail.transactionId;
        this.transactionDate = transactionDetail.transactionDate;
        this.narration = transactionDetail.narration;
        this.amount = transactionDetail.amount;
        this.closingBalance = transactionDetail.closingBalance;
    }

    public EntryType getTransactionType() {
        return transactionType;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public String getNarration() {
        return narration;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getClosingBalance() {
        return closingBalance;
    }

    @Override
    public String toString() {
        return "TransactionDetail{" +
                "transactionType=" + transactionType +
                ", transactionId=" + transactionId +
                ", transactionDate=" + transactionDate +
                ", narration='" + narration + '\'' +
                ", amount=" + amount +
                ", closingBalance=" + closingBalance +
                '}';
    }

    @Override
    public int compareTo(TransactionDetail o) {
        return this.transactionDate.compareTo(o.transactionDate);
    }
}
