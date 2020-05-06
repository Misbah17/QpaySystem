package com.microhybrid.transactionsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

public class UserHistory extends AppCompatActivity {

    private static final String TAG = "";
    public static TextView name ,email, amount, date;
    public Button Back;
    public static int money = 1000;
    public TextView Bal,value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_history);

        name = findViewById(R.id.tvname);
        email = findViewById(R.id.tvemail);
        amount = findViewById(R.id.tvamoutn);
        date= findViewById(R.id.textView11);
        Bal =   findViewById(R.id.tvamoutn);
        value = findViewById(R.id.value);


//        name.setVisibility(View.INVISIBLE);
//        email.setVisibility(View.INVISIBLE);
//        amount.setVisibility(View.INVISIBLE);
//        date.setVisibility(View.INVISIBLE);

        Intent i = getIntent();
        String x =i.getStringExtra("Name");
        String y =i.getStringExtra("Email");
        String z =i.getStringExtra("Amount");

        name.setText(x);
        email.setText(y);
        amount.setText(z);
//        name.setVisibility(View.INVISIBLE);
//        email.setVisibility(View.INVISIBLE);
//        amount.setVisibility(View.INVISIBLE);

//        Bal.setText(money);
            // String f = String.valueOf(money);

//        String a = GenerateQR.amount;
try {

    int b = Integer.parseInt(z);

//        value.setText(b);

    if (money > b) {
        Toast.makeText(UserHistory.this, +b + " Money has been added to your account", +b + Toast.LENGTH_SHORT).show();
        // money = money-b;
        value.setText("Your Transfer Amount is: " + b);
       // date.setVisibility(View.VISIBLE);

    }
}
catch (NumberFormatException e){
    Log.d(TAG,"Handle Exception" );
}
//        else{
//            Toast.makeText(UserHistory.this,"Your account does not have enough Money to do Transaction",Toast.LENGTH_SHORT).show();
//        }
    }

//    public void GoBack(View view) {
//         Intent prev= new Intent(UserHistory.this,Homepage.class);
//         startActivity(prev);
//         finish();
//    }
}