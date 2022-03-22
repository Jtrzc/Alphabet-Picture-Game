package com.example.cwp1;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.VisionRequestInitializer;
import com.google.api.services.vision.v1.model.AnnotateImageRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesResponse;
import com.google.api.services.vision.v1.model.Feature;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.VisionRequestInitializer;
import com.google.api.services.vision.v1.model.AnnotateImageRequest;
import com.google.api.services.vision.v1.model.AnnotateImageResponse;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesResponse;
import com.google.api.services.vision.v1.model.Feature;
import com.google.api.services.vision.v1.model.Image;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Screen2 extends AppCompatActivity {
    Button[][] btns = new Button[9][3];
    LinearLayout ll = null;
    String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String[] lett = {"A", "B", "C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    int counter=0;
    MediaPlayer mp,click;
    GridLayout grid;
    int rRow, rCol;
    Animation anim, anim2, anim3;
    Bitmap bm, holder;
    TextView tv;
    BitmapDrawable bdrawable;
    String hold, name;
    String[] concat;
    Boolean checker;
    Boolean startUP;
    SQLiteDatabase db = MainActivity.db4;
    SQLiteDatabase history = MainActivity.history4;
    Boolean[][] correct = new Boolean[9][3];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<9;i++){
                    for(int j=0; j<3;j++){
                        correct[i][j] = false;
                    }
                }
                correct[8][2] = true;
            }
        }).start();
        mp = MediaPlayer.create(this, R.raw.backgroundmusic);
        mp.setVolume(.15f, .15f);
        mp.setLooping(true);
        mp.start();
        ll = findViewById(R.id.ll);
        grid = findViewById(R.id.grid);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        grid.setMinimumHeight(width);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                int holder = i;
                int holder2= j;
                Button btn = new Button(this);
                btn.setLayoutParams(new ViewGroup.LayoutParams(width / 3, width / 7));
                btn.setText(lett[counter]);
                //btn.set
                btns[i][j] = btn;
                grid.addView(btn);
                counter++;
            }
        }
        Button btn = new Button(this);
        btn.setLayoutParams(new ViewGroup.LayoutParams(width / 3, width / 7));
        btns[8][0] = btn;
        btns[8][0].setX((width/2)-width/3);
        btn.setText(lett[24]);
        grid.addView(btn);
        Button btn2 = new Button(this);
        btn2.setLayoutParams(new ViewGroup.LayoutParams(width / 3, width / 7));
        btns[8][1] = btn2;
        btns[8][1].setX((width/2)-width/3);
        btn2.setText(lett[25]);
        grid.addView(btn2);
        checker = null;
        click = MediaPlayer.create(this, R.raw.buttonclick);
        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation);
        anim2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim2);
        anim3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotation);

        if(startUP==null) {
            randomNumber();
            if (rRow == 8 && rCol == 2) {
                rCol = rCol - 1;
            }
                btns[rRow][rCol].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent x = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(x, 1);
                        Log.v("MYTAG", "test");
                    }
                });

            btns[rRow][rCol].setTextColor(Color.parseColor("#ff0000"));
            btns[rRow][rCol].startAnimation(anim);
            String hold = null;
            hold = btns[rRow][rCol].getText().toString().toLowerCase();
            Resources res = getResources();
            int sound = res.getIdentifier(hold, "raw", getPackageName());
            MediaPlayer mp2 = MediaPlayer.create(this, sound);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        Thread.sleep(750);
                        mp2.start();
                        Log.v("MYTAG", "ON CREATE");
                    }catch(InterruptedException e) {
                        e.printStackTrace();
                    } {

                    }
                }
            }).start();
            ((Chronometer) findViewById(R.id.chronometer1)).setBase(SystemClock.elapsedRealtime());
            ((Chronometer) findViewById(R.id.chronometer1)).start();
        }

        startUP = true;
    }
    public void home(View v){
        mp.stop();
        click.start();
        startActivity(new Intent(this, MainActivity.class));
    }

    public void newLetter(){
        click.start();
        int rh = Integer.valueOf(rRow);
        int ch = Integer.valueOf(rCol);
        btns[rRow][rCol].setTextColor(Color.parseColor("#ffffff"));
        btns[rRow][rCol].clearAnimation();
        randomNumber();
        while(rh==rRow && ch==rCol){
            randomNumber();
        }
        if(rRow == 8 && rCol ==2){
            rCol = rCol-1;
        }
        String hold = null;
        hold = btns[rRow][rCol].getText().toString().toLowerCase();
        Resources res = getResources();
        int sound = res.getIdentifier(hold, "raw", getPackageName());
        MediaPlayer mp2 = MediaPlayer.create(this, sound);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(1500);
                    mp2.start();
                    Log.v("MYTAG", "New Letter");
                }catch(InterruptedException e) {
                    e.printStackTrace();
                } {

                }
            }
        }).start();
        if(rRow == 8 && rCol ==2){
            rCol = rCol-1;
        }
        btns[rRow][rCol].setTextColor(Color.parseColor("#ff0000"));
        btns[rRow][rCol].startAnimation(anim);
        btns[rRow][rCol].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(x, 1);
                Log.v("MYTAG", "test");
            }
        });

    }
    public void randomNumber(){
        Random r = new Random();
        int randIntCol = r.nextInt(3);
        rCol = randIntCol;
        int randIntRow = r.nextInt(9);
        rRow = randIntRow;
        if(correct[rRow][rCol] == true){
            randomNumber();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int rc, int resc, Intent data) {
        super.onActivityResult(rc, resc, data);
        bm = (Bitmap) data.getExtras().get("data");
        holder = bm;
        bdrawable = new BitmapDrawable(bm);
        //checker = true;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    myVisionTester();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        /**
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         */

        while(checker == null) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (checker) {
            ContentValues cv = new ContentValues();
            ContentValues cv2 = new ContentValues();
            cv.put("letter", (String) btns[rRow][rCol].getText());
            cv2.put("letter", (String) btns[rRow][rCol].getText());
            cv2.put("title", name);
            ((Chronometer) findViewById(R.id.chronometer1)).stop();
            long elapsedMillis = SystemClock.elapsedRealtime() -  ((Chronometer) findViewById(R.id.chronometer1)).getBase();
            cv.put("times", elapsedMillis);
            cv2.put("times", elapsedMillis);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            holder.compress(Bitmap.CompressFormat.PNG,100,stream);
            byte[] ba = stream.toByteArray();
            cv2.put("photo", ba);
            Date current = Calendar.getInstance().getTime();
            cv2.put("date", String.valueOf(current));
            db.insert("Time",null,cv);
            history.insert("Photos",null,cv2);

            setDraw();
            ImageView im = findViewById(R.id.imageView);
            //im.animate().alpha(1.0f);
            im.setBackgroundResource(0);
            im.setImageResource(R.drawable.correct);
            im.startAnimation(anim2);
            im.setAlpha(0f);
            im.setVisibility(View.VISIBLE);
            im.animate()
                    .alpha(0.5f)
                    .setDuration(2000)
                    .setListener(null);

//Out transition: (alpha from 0.5 to 0)
            im.setAlpha(0.5f);
            im.animate()
                    .alpha(0f)
                    .setDuration(2000)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            im.setVisibility(View.GONE);
                        }
                    });
            correct[rRow][rCol] = true;
            newLetter();
            ((Chronometer) findViewById(R.id.chronometer1)).setBase(SystemClock.elapsedRealtime());
            ((Chronometer) findViewById(R.id.chronometer1)).start();
            //im.setVisibility(View.GONE);
        } else {
            //Log.v("MYTAG", "THIS IS WRONG");
            ImageView im = findViewById(R.id.imageView);
            //im.animate().alpha(1.0f);
            im.setBackgroundResource(0);
            im.setImageResource(R.drawable.wrong);
            im.startAnimation(anim2);
            im.setAlpha(0f);
            im.setVisibility(View.VISIBLE);
            im.animate()
                    .alpha(0.5f)
                    .setDuration(2000)
                    .setListener(null);

//Out transition: (alpha from 0.5 to 0)
            im.setAlpha(0.5f);
            im.animate()
                    .alpha(0f)
                    .setDuration(2000)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            im.setVisibility(View.GONE);
                        }
                    });
        }
        checker = null;
        bm = null;
        bdrawable = null;
    }
    void myVisionTester() throws IOException {

            //1. ENCODE image.
            Bitmap bitmap = bm;
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bout);
            Image myimage = new Image();
            myimage.encodeContent(bout.toByteArray());

            //2. PREPARE AnnotateImageRequest
            AnnotateImageRequest annotateImageRequest = new AnnotateImageRequest();
            annotateImageRequest.setImage(myimage);
            Feature f = new Feature();
            f.setType("LABEL_DETECTION");
            f.setMaxResults(5);
            List<Feature> lf = new ArrayList<Feature>();
            lf.add(f);
            annotateImageRequest.setFeatures(lf);

            //3.BUILD the Vision
            HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
            GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
            Vision.Builder builder = new Vision.Builder(httpTransport, jsonFactory, null);
            builder.setVisionRequestInitializer(new VisionRequestInitializer("")); #API Key Here
            Vision vision = builder.build();

            //4. CALL Vision.Images.Annotate
            BatchAnnotateImagesRequest batchAnnotateImagesRequest = new BatchAnnotateImagesRequest();
            List<AnnotateImageRequest> list = new ArrayList<AnnotateImageRequest>();
            list.add(annotateImageRequest);
            batchAnnotateImagesRequest.setRequests(list);
            Vision.Images.Annotate task = vision.images().annotate(batchAnnotateImagesRequest);
            BatchAnnotateImagesResponse response = task.execute();

            //Log.v("MYTAG", response.toPrettyString());
            String str = response.toPrettyString();
            concat = str.split("\"", 60);
            //Log.v("MYTAG", concat[7] + " " + concat[19] + " " + concat[31] + " " + concat[43] + " " + concat[55]);
            //Log.v("MYTAG", (String) btns[row][col].getText());
            //Log.v("MYTAG", concat[7].substring(0,1));
            hold = (String) btns[rRow][rCol].getText();
            if(hold.equalsIgnoreCase(concat[7].substring(0,1)) || hold.equalsIgnoreCase(concat[19].substring(0,1)) || hold.equalsIgnoreCase(concat[31].substring(0,1)) ){
                  if(hold.equalsIgnoreCase(concat[7].substring(0, 1))) {
                      name = concat[7];
                      checker = true;
                      return;
                  }else if(hold.equalsIgnoreCase(concat[19].substring(0, 1))){
                      name = concat[19];
                      checker = true;
                      return;
                  }else if (hold.equalsIgnoreCase(concat[31].substring(0, 1))){
                      name = concat[31];
                      checker = true;
                      return;
                  }
            }else{
                checker = false;
            }
    }

    public void setDraw(){
        btns[rRow][rCol].setBackground(bdrawable);
        btns[rRow][rCol].startAnimation(anim3);
    }

    public void exit(View v){
        mp.stop();
        click.start();
        startActivity(new Intent(this, MainActivity.class));
    }




}