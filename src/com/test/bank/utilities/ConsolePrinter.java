package com.test.bank.utilities;

import com.main.bank.statement.model.TransactionDetail;

import java.math.BigDecimal;

/*
FOR Simulation Purpose only
 */
public class ConsolePrinter {

    public static String printEntry(Integer num, TransactionDetail transactionDetail) {
        return new String(
                "|" + num + "| " + transactionDetail.getTransactionDate() + " | " + transactionDetail.getNarration() +
                        " | " + roundAndFormatBigDecimal(transactionDetail.getAmount()) +
                        " | " + roundAndFormatBigDecimal(transactionDetail.getClosingBalance()) + " ||");
    }

    private static String roundAndFormatBigDecimal(BigDecimal amount) {
        return amount.setScale(2, BigDecimal.ROUND_HALF_EVEN).toPlainString();
    }

    public static String printUserMessage(String message) {
        if (message == null) {
            return "";
        }
        return new String("Message from Bank | " + message);
    }

    public static String printStatementHeader() {
        StringBuilder stringBuilder = new StringBuilder(
                "\n==================================== LAST 10 TRANSACTIONS =====================================");
        stringBuilder.append(
                "\n-------------------------------------------------------------------------------------------------");
        stringBuilder.append(
                "\n||\t\t\tDate\t\t\t|\tNarration\t|\tWithdrawal Amount\t|\tDeposit Amount\t| \tClosing Balance\t||");
        return stringBuilder.toString();
    }

    public static String printStatementFooter() {
        return "\n================================================================================================\n";
    }

}
