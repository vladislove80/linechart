/*package com.example.testchart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.testchart.chart.components.XAxis;
import com.example.testchart.chart.components.YAxis;
import com.example.testchart.chart.data.Entry;
import com.example.testchart.chart.data.LineData;
import com.example.testchart.chart.data.LineDataSet;
import com.example.testchart.chart.formatter.ValueFormatter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ArrayList<Entry> values = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LineChart chart = findViewById(R.id.chart1);
        LineData data = getData(5, 100);
        setupChart(chart, data, Color.rgb(137, 230, 81));
    }

    private LineData getData(int count, float range) {


        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range) + 3;
            Log.d(TAG, "getData: i = " + i + ", val = " + val );
            values.add(new Entry(i*10, val));
        }

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(values, "");
        set1.setFillAlpha(110);
        set1.setFillColor(Color.RED);
        set1.setLineWidth(1.75f);
        set1.setCircleRadius(5f);
        set1.setCircleHoleRadius(2.5f);
        set1.setColor(Color.WHITE);
        set1.setCircleColor(Color.WHITE);
        set1.setHighLightColor(Color.WHITE);
        set1.setDrawValues(true);
        set1.setValueTextSize(9f);
        return new LineData(set1);
    }

    private void setupChart(LineChart chart, LineData data, int color) {

        ((LineDataSet) data.getDataSetByIndex(0)).setCircleHoleColor(color);

        // no description text
        chart.getDescription().setEnabled(false);

        // chart.setDrawHorizontalGrid(false);
        //
        // enable / disable grid background
        chart.setDrawGridBackground(false);
//        chart.getRenderer().getGridPaint().setGridColor(Color.WHITE & 0x70FFFFFF);

        // enable touch gestures
        chart.setTouchEnabled(true);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and yAxis-axis separately
        chart.setPinchZoom(false);
        chart.setBackgroundColor(color);

        // add data
        chart.setData(data);

        // get the legend (only possible after setting data)
        chart.getLegend().setEnabled(false);

        YAxis axisRight = chart.getAxisRight();
        axisRight.setEnabled(false);

        YAxis yAxis = chart.getAxisLeft();
        yAxis.setLabelCount(8, false);
        yAxis.setTextColor(Color.WHITE);
        yAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        yAxis.setDrawGridLines(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setLabelCount(6, false);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                Log.d("MainActivity", "getFormattedValue: " + value);
                return super.getFormattedValue(value);
            }
        });

        // animate calls invalidate()...
        chart.animateX(2500);
    }

}*/


package com.example.testchart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;

import com.example.testchart.chart.components.XAxis;
import com.example.testchart.chart.components.YAxis;
import com.example.testchart.chart.data.Entry;
import com.example.testchart.chart.data.LineData;
import com.example.testchart.chart.data.LineDataSet;
import com.example.testchart.chart.formatter.HourAxisValueFormatter;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LineChart chart = findViewById(R.id.chart1);
        LineData data = getData();
        setupChart(chart, data, Color.rgb(137, 230, 81));
    }

    private LineData getData() {

        ArrayList<Entry> values = new ArrayList<>();
        values.add(new Entry(0, 72));
        values.add(new Entry(25200, 76));
        values.add(new Entry(61200, 72));
        values.add(new Entry(82800, 65));
        values.add(new Entry(100800, 78));
        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(values, "");
        set1.setFillAlpha(110);
        set1.setFillColor(Color.RED);
        set1.setLineWidth(1.75f);
        set1.setCircleRadius(5f);
        set1.setCircleHoleRadius(2.5f);
        set1.setColor(Color.WHITE);
        set1.setCircleColor(Color.WHITE);
        set1.setHighLightColor(Color.WHITE);
        set1.setDrawValues(true);
        set1.setValueTextSize(9f);
        return new LineData(set1);
    }

    private void setupChart(LineChart chart, LineData data, int color) {

        ((LineDataSet) data.getDataSetByIndex(0)).setCircleHoleColor(color);

        // no description text
        chart.getDescription().setEnabled(false);

        // chart.setDrawHorizontalGrid(false);
        //
        // enable / disable grid background
        chart.setDrawGridBackground(false);
//        chart.getRenderer().getGridPaint().setGridColor(Color.WHITE & 0x70FFFFFF);

        // enable touch gestures
        chart.setTouchEnabled(true);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and yAxis-axis separately
        chart.setPinchZoom(false);
        chart.setBackgroundColor(color);

        // add data
        chart.setData(data);

        // get the legend (only possible after setting data)
        chart.getLegend().setEnabled(false);

        YAxis axisRight = chart.getAxisRight();
        axisRight.setEnabled(false);

        YAxis yAxis = chart.getAxisLeft();
        yAxis.setLabelCount(10, false);
        yAxis.setTextColor(Color.WHITE);
        yAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        yAxis.setDrawGridLines(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setEnabled(true);
//        xAxis.setLabelCount(6, false);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);
        xAxis.setValueFormatter(new HourAxisValueFormatter(1451660400));

        // animate calls invalidate()...
        chart.animateX(2500);
    }

    private Pair<Long, Long> getDateRange() {
        long today = new Date().getTime();
        return new Pair<>(today, today - 7 * 24 * 60 * 60 * 1000);
    }
}
