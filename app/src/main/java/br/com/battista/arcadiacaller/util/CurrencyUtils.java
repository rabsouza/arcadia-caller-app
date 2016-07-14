package br.com.battista.arcadiacaller.util;

import android.util.Log;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyUtils {

    private static final String TAG = CurrencyUtils.class.getSimpleName();

    private static Locale locale = Locale.getDefault();
    private static NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);

    public static String format(BigDecimal value) {
        Log.d(TAG, "format: Format the value for string with formatting: $xxx.xxx,xx!");
        if (value == null) {
            return "";
        }

        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);

        return numberFormat.format(value);
    }
}
