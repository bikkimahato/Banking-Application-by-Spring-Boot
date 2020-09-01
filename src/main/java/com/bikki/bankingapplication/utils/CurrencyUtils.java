package com.bikki.bankingapplication.utils;

import com.bikki.bankingapplication.domain.enums.Currency;

import static com.bikki.bankingapplication.domain.enums.Currency.DOLLAR;
import static com.bikki.bankingapplication.domain.enums.Currency.EURO;
import static com.bikki.bankingapplication.domain.enums.Currency.POUND;

public class CurrencyUtils {

    public static Currency convertStringToCurrency(String currency) {
        switch (currency.toLowerCase()) {
            case "euro":
                return EURO;
            case "dollar":
                return DOLLAR;
            case "pound":
                return POUND;

            default:
                return EURO;

        }
    }
}
