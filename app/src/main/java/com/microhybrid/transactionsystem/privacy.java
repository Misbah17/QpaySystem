package com.microhybrid.transactionsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class privacy extends AppCompatActivity {

    private static final String TAG = "" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);
    }


    public void GoBack(View view){
        try {
            Intent prev = new Intent(privacy.this, Homepage.class);
            startActivity(prev);
            finish();
        }
        catch (RuntimeException e){
            Log.d(TAG,e.toString());
        }
    }
}
