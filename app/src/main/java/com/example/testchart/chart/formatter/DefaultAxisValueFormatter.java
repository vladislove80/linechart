package com.example.testchart.chart.formatter;


import android.util.Log;

import java.text.DecimalFormat;

public class DefaultAxisValueFormatter extends ValueFormatter {
    private static final String TAG = "DefaultAxisValueFormatt";

    /**
     * decimalformat for formatting
     */
    private final DecimalFormat mFormat;

    /**
     * the number of decimal digits this formatter uses
     */
    private final int digits;

    /**
     * Constructor that specifies to how many digits the value should be
     * formatted.
     *
     * @param digits
     */
    public DefaultAxisValueFormatter(int digits) {
        this.digits = digits;

        Log.d(TAG, "DefaultAxisValueFormatter: ");
        StringBuffer b = new StringBuffer();
        for (int i = 0; i < digits; i++) {
            if (i == 0)
                b.append(".");
            b.append("0");
        }

        mFormat = new DecimalFormat("###,###,###,##0" + b.toString());
    }

    @Override
    public String getFormattedValue(float value) {
        // avoid memory allocations here (for performance)
        return mFormat.format(value);
    }

    /**
     * Returns the number of decimal digits this formatter uses or -1, if unspecified.
     *
     * @return
     */
    public int getDecimalDigits() {
        return digits;
    }
}
