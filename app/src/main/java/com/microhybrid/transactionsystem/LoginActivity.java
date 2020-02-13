package com.microhybrid.transactionsystem;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity  {


    private FirebaseAuth mAuth;
  //  private String userID;
    FirebaseAuth.AuthStateListener mAuthListener;
    public static EditText UserName, password,email;
    Button login;
    TextView entername, Pass, attempts;
    ProgressBar PGL;
    FirebaseDatabase databse;
    private DatabaseReference ref;

    public static final String TAG = "YOUR-TAG-NAME";

    TextView tvload;
    ListView list;
    ArrayAdapter<String> adapter;

//    public  interface DataStatus{
//
//        void DataIsLoaded(List<UserInformation> userInformations, List<String> keys);
//        void DataIsDeleted();
//        void DataIsUpdated();
//        void DataIsInserted();
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


         UserName = findViewById(R.id.etname);
         password = findViewById(R.id.etpass);
        PGL = findViewById(R.id.PGLogin);
        attempts = findViewById(R.id.tvSignin);
        login = findViewById(R.id.btLogin);
        tvload = findViewById(R.id.tvtoast);
        list = findViewById(R.id.mListview);




        //  ref= FirebaseDatabase.getInstance().getReference().child("UserInformation");



        mAuth = FirebaseAuth.getInstance();

    }


    public void reg(View view) {

         ////Read data after clicking the Button Login////
        PGL.setVisibility(View.VISIBLE);
        Logindata();
    //    handleResult();


//

    }
    //////Read Database From Firebase

    private void Logindata() {


        //String auth= FirebaseAuth.getInstance().getCurrentUser().getUid();
        //String uid = mauth.getCurrentUser().getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = database.getReference().child("userInformation");
        final String  Username = UserName.getText().toString();
        final String Pasword = password.getText().toString();
//        final String Email= email.getText().toString();
//        final String  id = databaseReference.push().getKey();
        databaseReference.keepSynced(true);
        ///////Data retrive code for Login /////

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> userInformations = new ArrayList<>();
                //  UserInformation users = dataSnapshot.getValue(UserInformation.class);
                userInformations.clear();

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {

                    UserInformation userInformation = userSnapshot.getValue(UserInformation.class);

                    ArrayList<String> array = new ArrayList();
//                    ArrayAdapter adapter = new ArrayAdapter(LoginActivity.this,R.layout.loan_req, array);
//                    list.setAdapter(adapter);


                    if (Username.equals(userInformation.getName()) && Pasword.equals(userInformation.getPassword())) {


                        Log.d(TAG, "Showname:" + Username);
//                      profileActivity.name.setText(Username);
                        Log.d(TAG, "showEmail:" + userInformation.getEmail());
                        Log.d(TAG, "showContactNo:" + userInformation.getPhoneNo());
                        Log.d(TAG, "showAddress:" + userInformation.getAddress());

                        Toast.makeText(LoginActivity.this, "You are Successfully Logged In", Toast.LENGTH_LONG).show();
                        Intent a = new Intent(LoginActivity.this, Homepage.class);
                        a.putExtra("Name", userInformation.getName());
                        a.putExtra("Email", userInformation.getEmail());
                        a.putExtra("Num", userInformation.getPhoneNo());
                        a.putExtra("company", userInformation.getCompanyName());
                        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(a);
                        finish();

                    } else {
                        int counter = 5;

                        counter--;
                        attempts.setText("No of attempts remaining: " + counter);
                        if (counter == 0) {
                            login.setEnabled(false);
                        }
                        Toast.makeText(LoginActivity.this, "User Does not Exist", Toast.LENGTH_SHORT).show();
                    }
                }
                /////Validations on Email and password and the counter

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(LoginActivity.this, "User Does not Exits, Please Try Again", Toast.LENGTH_LONG).show();
                //      Log.w(TAG, "Failed to read value.", error.toException());

                //  UserInformation users = databaseError.toString(UserInformation.class);

            }
        });

        if (TextUtils.isEmpty(Username) && TextUtils.isEmpty(Pasword)) {
            Toast.makeText(LoginActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(Pasword)) {
            password.setError("Required");
            Toast.makeText(LoginActivity.this, "Fill your Password Field, and Try Again", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(Username)) {
            UserName.setError("Required");
            Toast.makeText(LoginActivity.this, "Fill your UserName Field, and Try Again", Toast.LENGTH_LONG).show();
        }

//        else if (!(Username.equals(userInformation.getName()) && Pasword.equals(userInformation.getPassword()))) {
//            Toast.makeText(LoginActivity.this, "User Does not Exits, Please Try Again", Toast.LENGTH_LONG).show();
//        }



    }


    ////To get Register yourself
    public void AccountRegister(View view) {
        Intent  btnreg = new Intent(LoginActivity.this,ActivitySignup.class);
       // Intent  btnreg = new Intent(LoginActivity.this,ScanCode.class);
        startActivity(btnreg);

    }

//    public void handleResult(Result rawResult){
//        Homepage.TVusername.setText(rawResult.getClass().getName());
//        onBackPressed();
//    }

}
