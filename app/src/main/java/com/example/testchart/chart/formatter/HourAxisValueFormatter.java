package com.example.testchart.chart.formatter;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HourAxisValueFormatter extends ValueFormatter {
    private final long referenceTimestamp; // minimum timestamp in your data set
    private final DateFormat mDataFormat;
    private final Date mDate;

    public HourAxisValueFormatter(long referenceTimestamp) {
        this.referenceTimestamp = referenceTimestamp;
        this.mDataFormat = new SimpleDateFormat("dd/MM", Locale.ENGLISH);
        this.mDate = new Date();
    }

    /**
     * Called when a value from an axis is to be formatted
     * before being drawn. For performance reasons, avoid excessive calculations
     * and memory allocations inside this method.
     */
    @Override
    public String getFormattedValue(float value) {
        // convertedTimestamp = originalTimestamp - referenceTimestamp
        long convertedTimestamp = (long) value;
        Log.d("HourAxisValue", "getFormattedValue: " + value);

        // Retrieve original timestamp
        long originalTimestamp = referenceTimestamp + convertedTimestamp;

        // Convert timestamp to hour:minute
        return getDay(originalTimestamp);
    }

    private String getDay(long timestamp) {
        try {
            mDate.setTime(timestamp * 1000);
            return mDataFormat.format(mDate);
        } catch (Exception ex) {
            return "xx";
        }
    }
}
