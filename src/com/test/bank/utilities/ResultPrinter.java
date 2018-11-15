package com.test.bank.utilities;

import com.main.bank.common.exception.SystemException;
import com.main.bank.common.exception.UserException;
import com.main.bank.statement.model.TransactionDetail;
import com.test.bank.functionalInterfaces.ActionHandler;
import com.test.bank.functionalInterfaces.ActionHandlerBigDecimal;
import com.test.bank.functionalInterfaces.ActionHandlerTransaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/*
FOR Simulation Purpose only
 */
public class ResultPrinter {

    public static String executeAndHandleExceptions(ActionHandler action, String successMessage,
                                                    String failureMessage) {
        try {
            if (action.execute()) {
                return ConsolePrinter.printUserMessage(successMessage);
            } else {
                return ConsolePrinter.printUserMessage(failureMessage);
            }
        } catch (UserException e) {
            return ConsolePrinter.printUserMessage(e.getMessage());
        }
    }

    public static String executeAndHandleExceptions(ActionHandlerBigDecimal action, String successMessage) {
        try {
            BigDecimal result = action.execute();
            if (Objects.nonNull(result)) {
                return ConsolePrinter
                        .printUserMessage(successMessage + CurrencyFormatter.formatNumberToCurrency(result));
            } else {
                throw new SystemException("Balanace cannot be null");
            }
        } catch (UserException e) {
            return ConsolePrinter.printUserMessage(e.getMessage());
        }
    }

    public static String executeAndHandleExceptions(ActionHandlerTransaction action) {
        try {
            List<TransactionDetail> result = action.execute();
            if (Objects.nonNull(result) && !result.isEmpty()) {
                StringBuilder stringBuilder = new StringBuilder(ConsolePrinter.printStatementHeader());
                AtomicInteger counter = new AtomicInteger(1);
                result.forEach(
                        transactionDetail -> stringBuilder
                                .append("\n" + ConsolePrinter.printEntry(counter.getAndIncrement(), transactionDetail)));
                stringBuilder.append(ConsolePrinter.printStatementFooter());
                return stringBuilder.toString();
            } else {
                return ConsolePrinter.printUserMessage("No transaction to display");
            }
        } catch (UserException e) {
            return ConsolePrinter.printUserMessage(e.getMessage());
        }
    }
}
