package com.microhybrid.transactionsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
//import android.os.*;
//import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {

    private static int Splashtimeout = 3000;
    Handler handler;
    ProgressBar PBM;
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // PBM=findViewById(R.id.progressmain);
        // PBM.setVisibility(View.VISIBLE);
      //  handler=new Handler();

//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(MainActivity.this, Homepage.class);
//                startActivity(intent);
//                finish();
//                // PBM.setVisibility(View.GONE);
//            }
//        },Splashtimeout);

    }

    public void getstart(View view) {
        Intent startapp = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(startapp);

    }
}
