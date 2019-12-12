package com.microhybrid.transactionsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class LoginActivity extends AppCompatActivity  {


    private FirebaseAuth mAuth;

    FirebaseAuth.AuthStateListener mAuthListener;
    EditText UserName, password;
    Button login;
    TextView entername, Pass, attempts;
    ProgressBar PGL;
    FirebaseDatabase databse;
    private DatabaseReference ref;
    int counter = 5;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static final String TAG = "YOUR-TAG-NAME";
    private List<UserInformation> userInformations = new ArrayList<>();
   TextView tvload;

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

        mAuth = FirebaseAuth.getInstance();

        UserName = findViewById(R.id.etname);
        password = findViewById(R.id.etpass);
        PGL = findViewById(R.id.PGLogin);
        attempts = findViewById(R.id.tvSignin);
        login = findViewById(R.id.btLogin);
        tvload = findViewById(R.id.tvtoast);

        //  ref= FirebaseDatabase.getInstance().getReference().child("UserInformation");


        Intent i = getIntent();
        tvload.setText(i.getStringExtra("Values"));

    }


    public void reg(View view) {

         ////Read data after clicking the Button Login////
        PGL.setVisibility(View.VISIBLE);
        Logindata();
    //    handleResult();


//             if (TextUtils.isEmpty(Email) && TextUtils.isEmpty(Pasword)){
//                 Toast.makeText(LoginActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
//             }



//        if(!(Email.isEmpty()&& Pasword.isEmpty()))
//            mAuth.createUserWithEmailAndPassword(Email, Pasword).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    PGL.setVisibility(View.GONE);
//                    if (task.isSuccessful()) {
//                        Toast.makeText(LoginActivity.this, " You are Registered Succesfully", Toast.LENGTH_SHORT).show();
//                    }
//
//                    else {
//                        Toast.makeText(LoginActivity.this, "SignUp Unsuccessful, Try Again", Toast.LENGTH_SHORT).show();
//                        //startActivity(new Intent(FirstActivity.this, HomeActivity.class));
//
//                    }
//
//                    // else{
//                    //  Toast.makeText(FirstActivity.this, " Login in Successful,", Toast.LENGTH_SHORT).show();
//
//                    // }
//
//
//
//
//
//                }
//            });
//
//
//
//
//
//        else{
//            Toast.makeText(LoginActivity.this, "Error Occured", Toast.LENGTH_SHORT).show();
//
//
//        }
//
//

    }
    //////Read Database From Firebase

    private void Logindata() {
        final DatabaseReference databaseReference = database.getReference().child("userInformation");

      final   String Username = UserName.getText().toString();
        final String Pasword = password.getText().toString();
//
        final String  id = databaseReference.push().getKey();

        ///////Data retrive code for Login /////


        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              //  UserInformation users = dataSnapshot.getValue(UserInformation.class);
                userInformations.clear();
                List<String> userinfo = new ArrayList<>();
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {

                    userinfo.add(userSnapshot.getKey());
                    UserInformation userInformation = userSnapshot.getValue(UserInformation.class);
                   // userInformation.setPkgDetail(userSnapshot.child(Username).getValue(UserInformation.class).getPkgDetail());
                    // userInformations.add(userInformation);
                    //UserInformation userInformation= dataSnapshot.getValue(UserInformation.class);

                        if (Username.equals(userInformation.getName()) && Pasword.equals(userInformation.getPassword())) {
//
                            Toast.makeText(LoginActivity.this, "You are Successfully Logged In", Toast.LENGTH_LONG).show();
                            Intent a = new Intent(LoginActivity.this, Homepage.class);
                            a.putExtra("Value",Username);
                            a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(a);
                           finish();
                        }
//
//
//                       else if (!( Username.equals(userInformation.getName()) && Pasword.equals(userInformation.getPassword()))) {
//                            Toast.makeText(LoginActivity.this, "User Does not Exist, Please Try Again", Toast.LENGTH_SHORT).show();
//                        }








                }

//               if (!( Username.equals(users.getName()))){
//                    Toast.makeText(LoginActivity.this, "Your Name is InCorrect", Toast.LENGTH_SHORT).show();
//
//                }
//
//               if (!( Pasword.equals(users.getName()))){
//                    Toast.makeText(LoginActivity.this, "Your Password is InCorrect", Toast.LENGTH_SHORT).show();
//
//                }
//

            }






            // DataStatus.DataIsLoaded(UserInformation, userinfo);


                  //   UserInformation customer = dataSnapshot.getValue(UserInformation.class);
                    // Log.d(TAG, Email + " / " +Pasword);
//                     if (Email.equals(customer.getEtmail()) && Pasword.equals(customer.getEtpass())) {
//
//                    Toast.makeText(LoginActivity.this, "You are Successfully Login", Toast.LENGTH_LONG).show();
//                    Intent a = new Intent(LoginActivity.this, Homepage.class);
//                    startActivity(a);
//                    finish();
//                }
////
//                else {
//
//                    Toast.makeText(LoginActivity.this, "User Does not Exits, Please Try Again", Toast.LENGTH_LONG).show();
//                }
//


             //   UserInformation customer = dataSnapshot.getValue(UserInformation.class);
//
////                String value = dataSnapshot.getValue(String.class);
////                Log.d(TAG,"Value is: " + customer.getEtmail()+ "password:" +customer.getEtpass());
//

                /////Validations on Email and password and the counter





            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(LoginActivity.this,"User Does not Exits, Please Try Again" ,Toast.LENGTH_LONG).show();
                //      Log.w(TAG, "Failed to read value.", error.toException());

                //  UserInformation users = databaseError.toString(UserInformation.class);

            }
        });



        if (TextUtils.isEmpty(Username) && TextUtils.isEmpty(Pasword)) {
            Toast.makeText(LoginActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();

        }

        else if (TextUtils.isEmpty(Pasword)) {
            password.setError("Required");
            Toast.makeText(LoginActivity.this, "Fill your Password Field, and Try Again", Toast.LENGTH_LONG).show();
        }

        else if (TextUtils.isEmpty(Username)) {
           UserName.setError("Required");
            Toast.makeText(LoginActivity.this, "Fill your UserName Field, and Try Again", Toast.LENGTH_LONG).show();
        }

//        else if (!(Username.equals(userInformation.getName()) && Pasword.equals(userInformation.getPassword()))) {
//            Toast.makeText(LoginActivity.this, "User Does not Exits, Please Try Again", Toast.LENGTH_LONG).show();
//        }



        else {
            //Toast.makeText(LoginActivity.this, "User Does not Exist, Try Again ", Toast.LENGTH_SHORT).show();

            counter--;
            attempts.setText("No of attempts remaining::" + counter);
            if (counter == 0) {
                login.setEnabled(false);
            }


        }
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
