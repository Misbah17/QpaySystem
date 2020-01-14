package com.microhybrid.transactionsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
//////import android.os.Handler;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.logging.Handler;
//import java.util.logging.Handler;


public class MainActivity extends AppCompatActivity {

    private static int Splashtimeout = 5000;
    private Handler handler ;
    ProgressBar PBM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // PBM=findViewById(R.id.progressmain);
        // PBM.setVisibility(View.VISIBLE);
        //handler = new Handler();


//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                startActivity(intent);
//                finish();
//                // PBM.setVisibility(View.GONE);
//            }
//        },Splashtimeout);

    }

    public void getstart(View view) {
        Intent startapp = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(startapp);
    }
}
