package com.microhybrid.transactionsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class payment extends AppCompatActivity {

    private static final String TAG = "";
    public TextView totalamount;
    public static int money = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        totalamount = findViewById(R.id.totalaamount);
try {
    String s = GenerateQR.amount;
    int currentamount = Integer.parseInt(s);

    if (money > currentamount) {
        currentamount = money - currentamount;
        totalamount.setText("Your Current amount is" + currentamount);
    }
//        Intent a = getIntent();
//        String x = a.getStringExtra("Remain");
//        totalamount.setText(x);
}
catch (NumberFormatException e){

    Log.d(TAG,e.toString());
}
    }

    public void GoBack(View view) {
        Intent prev= new Intent(payment.this,Homepage.class);
        startActivity(prev);
        finish();
    }
}
