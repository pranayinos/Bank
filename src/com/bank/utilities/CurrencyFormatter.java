package com.bank.utilities;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyFormatter {

    public static String formatNumberToCurrency(BigDecimal number) {
        NumberFormat n = NumberFormat.getCurrencyInstance(Locale.UK);
        double money = number.doubleValue();
        return n.format(money);
    }
}
