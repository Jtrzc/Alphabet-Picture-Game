package com.example.cwp1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Screen3 extends AppCompatActivity {

    SQLiteDatabase db = MainActivity.db4;
    SQLiteDatabase db2 = MainActivity.history4;
    Cursor t,c;
    Float f;
    ListView lv;
    Bitmap noImage;
    Bitmap[] pics = new Bitmap[78];
    String[] names = new String[78];
    String[] duration = new String[78];
    String[] datetime = new String[78];
    String[] letters = new String[26];


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen3);

        letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        noImage = BitmapFactory.decodeResource(this.getResources(), R.drawable.noimage);

        f = 0f;
        //myPrintTable("Time");

        BarChart barChart = (BarChart) findViewById(R.id.chart);

        BarData data = barData();

        barChart.setData(data);
        //barChart.setFitBars(true);
        barChart.setVisibleXRangeMaximum(8f);

        barChart.setBackgroundColor(Color.WHITE);
        // xAxis customization
        XAxis xAxis = barChart.getXAxis();

        // Following code have no effect but you can change it if required
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setCenterAxisLabels(false);

        // Setting position of xAxis
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);

        // Setting labels to xAxis
        xAxis.setValueFormatter(new IndexAxisValueFormatter(getXAxisValues()));
        barChart.getDescription().setText("Seconds");
        barChart.getLegend().setEnabled(false);
        barChart.invalidate();

        fillHistory();

        lv = findViewById(R.id.lv);
        lv.setAdapter(new MyListAdapter(this));
        //Log.v("MYTAG", String.valueOf(lv.getCount()));

    }



    // creating list of x-axis values
    private ArrayList<String> getXAxisValues()
    {
        ArrayList<String> xAxisLabel = new ArrayList<String> ();

        xAxisLabel.add("a");
        xAxisLabel.add("b");
        xAxisLabel.add("c");
        xAxisLabel.add("d");
        xAxisLabel.add("e");
        xAxisLabel.add("f");
        xAxisLabel.add("g");
        xAxisLabel.add("h");
        xAxisLabel.add("i");
        xAxisLabel.add("j");
        xAxisLabel.add("k");
        xAxisLabel.add("l");
        xAxisLabel.add("m");
        xAxisLabel.add("n");
        xAxisLabel.add("o");
        xAxisLabel.add("p");
        xAxisLabel.add("q");
        xAxisLabel.add("r");
        xAxisLabel.add("s");
        xAxisLabel.add("t");
        xAxisLabel.add("u");
        xAxisLabel.add("v");
        xAxisLabel.add("w");
        xAxisLabel.add("x");
        xAxisLabel.add("y");
        xAxisLabel.add("z");
        return xAxisLabel;
    }

    // this method is used to create data for Bar graph
    @RequiresApi(api = Build.VERSION_CODES.N)
    public BarData barData() {
        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

        String query = "SELECT times from Time WHERE Time.letter = 'A' ;" ;
        t = db.rawQuery(query, null);
        t.moveToFirst();
        f = 0f;
        if(t.getCount()>0) {
            for (int j = 0; j < t.getCount(); j++) {
                f = Float.sum(f,t.getFloat(0)/1000);
                if(j+1<t.getCount()){
                    t.moveToNext();
                }
            }
            f = f / t.getCount();
        }else{
            f = 0f;
        }

        entries.add(new BarEntry(0, f));

        query = "SELECT times from Time WHERE Time.letter = 'B' ;" ;
        t = db.rawQuery(query, null);
        t.moveToFirst();
        f = 0f;
        if(t.getCount()>0) {
            for (int j = 0; j < t.getCount(); j++) {
                f = Float.sum(f,t.getFloat(0)/1000);
                if(j+1<t.getCount()){
                    t.moveToNext();
                }
            }
            f = f / t.getCount();
        }else{
            f = 0f;
        }
        entries.add(new BarEntry(1, f));

        query = "SELECT times from Time WHERE Time.letter = 'C' ;" ;
        t = db.rawQuery(query, null);
        t.moveToFirst();
        f = 0f;
        if(t.getCount()>0) {
            for (int j = 0; j < t.getCount(); j++) {
                f = Float.sum(f,t.getFloat(0)/1000);
                if(j+1<t.getCount()){
                    t.moveToNext();
                }
            }
            f = f / t.getCount();
        }else{
            f = 0f;
        }

        entries.add(new BarEntry(2, f));

        query = "SELECT times from Time WHERE Time.letter = 'D' ;" ;
        t = db.rawQuery(query, null);
        t.moveToFirst();
        f = 0f;
        if(t.getCount()>0) {
            for (int j = 0; j < t.getCount(); j++) {
                f = Float.sum(f,t.getFloat(0)/1000);
                if(j+1<t.getCount()){
                    t.moveToNext();
                }
            }
            f = f / t.getCount();
        }else{
            f = 0f;
        }
        entries.add(new BarEntry(3, f));
        query = "SELECT times from Time WHERE Time.letter = 'E' ;" ;
        t = db.rawQuery(query, null);
        t.moveToFirst();
        f = 0f;
        if(t.getCount()>0) {
            for (int j = 0; j < t.getCount(); j++) {
                f = Float.sum(f,t.getFloat(0)/1000);
                if(j+1<t.getCount()){
                    t.moveToNext();
                }
            }
            f = f / t.getCount();
        }else{
            f = 0f;
        }
        entries.add(new BarEntry(4, f));
        query = "SELECT times from Time WHERE Time.letter = 'F' ;" ;
        t = db.rawQuery(query, null);
        t.moveToFirst();
        f = 0f;
        if(t.getCount()>0) {
            for (int j = 0; j < t.getCount(); j++) {
                f = Float.sum(f,t.getFloat(0)/1000);
                if(j+1<t.getCount()){
                    t.moveToNext();
                }
            }
            f = f / t.getCount();
        }else{
            f = 0f;
        }
        entries.add(new BarEntry(5, f));
        query = "SELECT times from Time WHERE Time.letter = 'G' ;" ;
        t = db.rawQuery(query, null);
        t.moveToFirst();
        f = 0f;
        if(t.getCount()>0) {
            for (int j = 0; j < t.getCount(); j++) {
                f = Float.sum(f,t.getFloat(0)/1000);
                if(j+1<t.getCount()){
                    t.moveToNext();
                }
            }
            f = f / t.getCount();
        }else{
            f = 0f;
        }
        entries.add(new BarEntry(6, f));
        query = "SELECT times from Time WHERE Time.letter = 'H' ;" ;
        t = db.rawQuery(query, null);
        t.moveToFirst();
        f = 0f;
        if(t.getCount()>0) {
            for (int j = 0; j < t.getCount(); j++) {
                f = Float.sum(f,t.getFloat(0)/1000);
                if(j+1<t.getCount()){
                    t.moveToNext();
                }
            }
            f = f / t.getCount();
        }else{
            f = 0f;
        }
        entries.add(new BarEntry(7, f));
        query = "SELECT times from Time WHERE Time.letter = 'I' ;" ;
        t = db.rawQuery(query, null);
        t.moveToFirst();
        f = 0f;
        if(t.getCount()>0) {
            for (int j = 0; j < t.getCount(); j++) {
                f = Float.sum(f,t.getFloat(0)/1000);
                if(j+1<t.getCount()){
                    t.moveToNext();
                }
            }
            f = f / t.getCount();
        }else{
            f = 0f;
        }
        entries.add(new BarEntry(8, f));
        query = "SELECT times from Time WHERE Time.letter = 'J' ;" ;
        t = db.rawQuery(query, null);
        t.moveToFirst();
        f = 0f;
        if(t.getCount()>0) {
            for (int j = 0; j < t.getCount(); j++) {
                f = Float.sum(f,t.getFloat(0)/1000);
                if(j+1<t.getCount()){
                    t.moveToNext();
                }
            }
            f = f / t.getCount();
        }else{
            f = 0f;
        }
        entries.add(new BarEntry(9, f));
        query = "SELECT times from Time WHERE Time.letter = 'K' ;" ;
        t = db.rawQuery(query, null);
        t.moveToFirst();
        f = 0f;
        if(t.getCount()>0) {
            for (int j = 0; j < t.getCount(); j++) {
                f = Float.sum(f,t.getFloat(0)/1000);
                if(j+1<t.getCount()){
                    t.moveToNext();
                }
            }
            f = f / t.getCount();
        }else{
            f = 0f;
        }
        entries.add(new BarEntry(10, f));
        query = "SELECT times from Time WHERE Time.letter = 'L' ;" ;
        t = db.rawQuery(query, null);
        t.moveToFirst();
        f = 0f;
        if(t.getCount()>0) {
            for (int j = 0; j < t.getCount(); j++) {
                f = Float.sum(f,t.getFloat(0)/1000);
                if(j+1<t.getCount()){
                    t.moveToNext();
                }
            }
            f = f / t.getCount();
        }else{
            f = 0f;
        }
        entries.add(new BarEntry(11, f));
        query = "SELECT times from Time WHERE Time.letter = 'M' ;" ;
        t = db.rawQuery(query, null);
        t.moveToFirst();
        f = 0f;
        if(t.getCount()>0) {
            for (int j = 0; j < t.getCount(); j++) {
                f = Float.sum(f,t.getFloat(0)/1000);
                if(j+1<t.getCount()){
                    t.moveToNext();
                }
            }
            f = f / t.getCount();
        }else{
            f = 0f;
        }
        entries.add(new BarEntry(12, f));
        query = "SELECT times from Time WHERE Time.letter = 'N' ;" ;
        t = db.rawQuery(query, null);
        t.moveToFirst();
        f = 0f;
        if(t.getCount()>0) {
            for (int j = 0; j < t.getCount(); j++) {
                f = Float.sum(f,t.getFloat(0)/1000);
                if(j+1<t.getCount()){
                    t.moveToNext();
                }
            }
            f = f / t.getCount();
        }else{
            f = 0f;
        }
        entries.add(new BarEntry(13, f));
        query = "SELECT times from Time WHERE Time.letter = 'O' ;" ;
        t = db.rawQuery(query, null);
        t.moveToFirst();
        f = 0f;
        if(t.getCount()>0) {
            for (int j = 0; j < t.getCount(); j++) {
                f = Float.sum(f,t.getFloat(0)/1000);
                if(j+1<t.getCount()){
                    t.moveToNext();
                }
            }
            f = f / t.getCount();
        }else{
            f = 0f;
        }
        entries.add(new BarEntry(14, f));
        query = "SELECT times from Time WHERE Time.letter = 'P' ;" ;
        t = db.rawQuery(query, null);
        t.moveToFirst();
        f = 0f;
        if(t.getCount()>0) {
            for (int j = 0; j < t.getCount(); j++) {
                f = Float.sum(f,t.getFloat(0)/1000);
                if(j+1<t.getCount()){
                    t.moveToNext();
                }
            }
            f = f / t.getCount();
        }else{
            f = 0f;
        }
        entries.add(new BarEntry(15, f));
        query = "SELECT times from Time WHERE Time.letter = 'Q' ;" ;
        t = db.rawQuery(query, null);
        t.moveToFirst();
        f = 0f;
        if(t.getCount()>0) {
            for (int j = 0; j < t.getCount(); j++) {
                f = Float.sum(f,t.getFloat(0)/1000);
                if(j+1<t.getCount()){
                    t.moveToNext();
                }
            }
            f = f / t.getCount();
        }else{
            f = 0f;
        }
        entries.add(new BarEntry(16, f));
        query = "SELECT times from Time WHERE Time.letter = 'R' ;" ;
        t = db.rawQuery(query, null);
        t.moveToFirst();
        f = 0f;
        if(t.getCount()>0) {
            for (int j = 0; j < t.getCount(); j++) {
                f = Float.sum(f,t.getFloat(0)/1000);
                if(j+1<t.getCount()){
                    t.moveToNext();
                }
            }
            f = f / t.getCount();
        }else{
            f = 0f;
        }
        entries.add(new BarEntry(17, f));
        query = "SELECT times from Time WHERE Time.letter = 'S' ;" ;
        t = db.rawQuery(query, null);
        t.moveToFirst();
        f = 0f;
        if(t.getCount()>0) {
            for (int j = 0; j < t.getCount(); j++) {
                f = Float.sum(f,t.getFloat(0)/1000);
                if(j+1<t.getCount()){
                    t.moveToNext();
                }
            }
            f = f / t.getCount();
        }else{
            f = 0f;
        }
        entries.add(new BarEntry(18, f));
        query = "SELECT times from Time WHERE Time.letter = 'T' ;" ;
        t = db.rawQuery(query, null);
        t.moveToFirst();
        f = 0f;
        if(t.getCount()>0) {
            for (int j = 0; j < t.getCount(); j++) {
                f = Float.sum(f,t.getFloat(0)/1000);
                if(j+1<t.getCount()){
                    t.moveToNext();
                }
            }
            f = f / t.getCount();
        }else{
            f = 0f;
        }
        entries.add(new BarEntry(19, f));
        query = "SELECT times from Time WHERE Time.letter = 'U' ;" ;
        t = db.rawQuery(query, null);
        t.moveToFirst();
        f = 0f;
        if(t.getCount()>0) {
            for (int j = 0; j < t.getCount(); j++) {
                f = Float.sum(f,t.getFloat(0)/1000);
                if(j+1<t.getCount()){
                    t.moveToNext();
                }
            }
            f = f / t.getCount();
        }else{
            f = 0f;
        }
        entries.add(new BarEntry(20, f));
        query = "SELECT times from Time WHERE Time.letter = 'V' ;" ;
        t = db.rawQuery(query, null);
        t.moveToFirst();
        f = 0f;
        if(t.getCount()>0) {
            for (int j = 0; j < t.getCount(); j++) {
                f = Float.sum(f,t.getFloat(0)/1000);
                if(j+1<t.getCount()){
                    t.moveToNext();
                }
            }
            f = f / t.getCount();
        }else{
            f = 0f;
        }
        entries.add(new BarEntry(21, f));
        query = "SELECT times from Time WHERE Time.letter = 'W' ;" ;
        t = db.rawQuery(query, null);
        t.moveToFirst();
        f = 0f;
        if(t.getCount()>0) {
            for (int j = 0; j < t.getCount(); j++) {
                f = Float.sum(f,t.getFloat(0)/1000);
                if(j+1<t.getCount()){
                    t.moveToNext();
                }
            }
            f = f / t.getCount();
        }else{
            f = 0f;
        }
        entries.add(new BarEntry(22, f));
        query = "SELECT times from Time WHERE Time.letter = 'X' ;" ;
        t = db.rawQuery(query, null);
        t.moveToFirst();
        f = 0f;
        if(t.getCount()>0) {
            for (int j = 0; j < t.getCount(); j++) {
                f = Float.sum(f,t.getFloat(0)/1000);
                if(j+1<t.getCount()){
                    t.moveToNext();
                }
            }
            f = f / t.getCount();
        }else{
            f = 0f;
        }
        entries.add(new BarEntry(23, f));
        query = "SELECT times from Time WHERE Time.letter = 'Y' ;" ;
        t = db.rawQuery(query, null);
        t.moveToFirst();
        f = 0f;
        if(t.getCount()>0) {
            for (int j = 0; j < t.getCount(); j++) {
                f = Float.sum(f,t.getFloat(0)/1000);
                if(j+1<t.getCount()){
                    t.moveToNext();
                }
            }
            f = f / t.getCount();
        }else{
            f = 0f;
        }
        entries.add(new BarEntry(24, f));
        query = "SELECT times from Time WHERE Time.letter = 'Z' ;" ;
        t = db.rawQuery(query, null);
        t.moveToFirst();
        f = 0f;
        if(t.getCount()>0) {
            for (int j = 0; j < t.getCount(); j++) {
                f = Float.sum(f,t.getFloat(0)/1000);
                if(j+1<t.getCount()){
                    t.moveToNext();
                }
            }
            f = f / t.getCount();
        }else{
            f = 0f;
        }
        entries.add(new BarEntry(25, f));

        BarDataSet barDataSet = new BarDataSet(entries, "Letters");
        barDataSet.setAxisDependency(YAxis.AxisDependency.RIGHT);
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData barData = new BarData(barDataSet);


        return barData;
    }

    public void fillHistory(){
        for(int i =0; i<26;i++) {
            String query = "SELECT title, times, photo, date from Photos WHERE Photos.letter = '" + letters[i] + "';";
            t = db2.rawQuery(query, null);
            t.moveToLast();
            if(t.getCount() == 0){
               pics[i*3] = noImage;
               pics[(i*3)+1] = noImage;
               pics[(i*3)+2] = noImage;
               names[(i*3)] = "NO IMAGE";
               names[(i*3)+1] = "NO IMAGE";
               names[(i*3)+2] = "NO IMAGE";
               duration[(i*3)] = "TAKE PICTURE";
               duration[(i*3)+1] = "TAKE PICTURE";
               duration[(i*3)+2] = "TAKE PICTURE";
               datetime[(i*3)] = "IN GAME";
               datetime[(i*3)+1] = "IN GAME";
               datetime[(i*3)+2] = "IN GAME";

            }else{
                pics[i*3] = BitmapFactory.decodeByteArray(t.getBlob(2),0,t.getBlob(2).length);
                names[(i*3)] = t.getString(0);
                duration[(i*3)] = String.valueOf(Float.parseFloat(t.getString(1))/1000) + " seconds";
                datetime[(i*3)] = t.getString(3);

                if(t.moveToPrevious()){
                    pics[(i*3)+1] = BitmapFactory.decodeByteArray(t.getBlob(2),0,t.getBlob(2).length);
                    names[(i*3)+1] = t.getString(0);
                    duration[(i*3)+1] = String.valueOf(Float.parseFloat(t.getString(1))/1000) + " seconds";
                    datetime[(i*3)+1] = t.getString(3);;
                }else{
                    pics[(i*3)+1] = noImage;
                    pics[(i*3)+2] = noImage;
                    names[(i*3)+1] = "NO IMAGE";
                    names[(i*3)+2] = "NO IMAGE";
                    duration[(i*3)+1] = "TAKE PICTURE";
                    duration[(i*3)+2] = "TAKE PICTURE";
                    datetime[(i*3)+1] = "IN GAME";
                    datetime[(i*3)+2] = "IN GAME";
                }
                if(t.moveToPrevious()){
                    pics[(i*3)+2] = BitmapFactory.decodeByteArray(t.getBlob(2),0,t.getBlob(2).length);
                    names[(i*3)+2] = t.getString(0);
                    duration[(i*3)+2] = String.valueOf(Float.parseFloat(t.getString(1))/1000) + " seconds";
                    datetime[(i*3)+2] = t.getString(3);;
                }else{
                    pics[(i*3)+2] = noImage;
                    names[(i*3)+2] = "NO IMAGE";
                    duration[(i*3)+2] = "TAKE PICTURE";
                    datetime[(i*3)+2] = "IN GAME";
                }
            }
        }
    }


    public void myPrintTable(String tablename){
        String query = "SELECT * from " + tablename + ";";
        c = db.rawQuery(query, null);
        c.moveToFirst();
        if(tablename.equals("Time")) {
            for (int i = 0; i < c.getCount(); i++) {
                Log.v("MYTAG", c.getString(0) + " | " + c.getFloat(1));
                c.moveToNext();
            }
        }else{
            for (int i = 0; i < c.getCount(); i++) {
                Log.v("MYTAG", c.getString(0) + " | " + c.getString(1)+ " | " + c.getString(2));
                c.moveToNext();
            }
        }
    }


}