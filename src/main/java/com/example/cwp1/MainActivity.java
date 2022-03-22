package com.example.cwp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
     MediaPlayer mp;
    public static SQLiteDatabase db4;
    public static SQLiteDatabase history4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!this.getDatabasePath("db4").exists()) {
            Toast.makeText(this, "ENTERED", Toast.LENGTH_LONG);
            db4 = this.openOrCreateDatabase("db4", Context.MODE_PRIVATE, null);
            db4.execSQL("DROP TABLE IF EXISTS Time;");
            db4.execSQL("CREATE TABLE Time (letter TEXT, times REAL);");
        }else{
            db4 = this.openOrCreateDatabase("db4", Context.MODE_PRIVATE, null);

        }
        if(!this.getDatabasePath("history4").exists()) {
            history4 = this.openOrCreateDatabase("history4", Context.MODE_PRIVATE, null);
            history4.execSQL("DROP TABLE IF EXISTS Photos;");
            history4.execSQL("CREATE TABLE Photos (letter TEXT, title TEXT, times REAL, photo BLOB, date TEXT);");
        }else{
            history4 = this.openOrCreateDatabase("history4", Context.MODE_PRIVATE, null);

        }

    }



    public void startGame(View v){
        mp = MediaPlayer.create(this, R.raw.buttonclick);
        mp.start();
        startActivity(new Intent(this, Screen2.class));
    }
    public void history(View v){
        mp = MediaPlayer.create(this, R.raw.buttonclick);
        mp.start();
        startActivity(new Intent(this, Screen3.class));
    }


}