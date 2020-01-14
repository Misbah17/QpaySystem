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
    private String userID;
    FirebaseAuth.AuthStateListener mAuthListener;
    EditText UserName, password;
    Button login;
    TextView entername, Pass, attempts;
    ProgressBar PGL;
    FirebaseDatabase databse;
    private DatabaseReference ref;
    int counter = 5;
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


        Intent i = getIntent();
        tvload.setText(i.getStringExtra("Values"));


//        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        userID = user.getUid();


        mAuth = FirebaseAuth.getInstance();

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
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = database.getReference().child("userInformation");
        final   String Username = UserName.getText().toString();
        final String Pasword = password.getText().toString();
//
        final String  id = databaseReference.push().getKey();

        ///////Data retrive code for Login /////


        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> userInformations = new ArrayList<>();
              //  UserInformation users = dataSnapshot.getValue(UserInformation.class);
                userInformations.clear();

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {

                //  UserInformation userInformation= new UserInformation();
                    UserInformation userInformation = userSnapshot.getValue(UserInformation.class);
                  // userInformation.setName(userSnapshot.getValue(UserInformation.class).getName());
//                    userInformation.setEmail(userSnapshot.child(userID).getValue(UserInformation.class).getEmail());
//                    Log.d(TAG,"Showname:" +userInformation.getName());
//                    Log.d(TAG,"showEmail:" +userInformation.getEmail());
                  //  userinfo.add(userSnapshot.getKey());


                  //  adapter = new ArrayAdapter<UserInformation>(this,R.layout.loan_req,R.id.textView2,userinfo);
                  ///  list.setAdapter(adapter);
                   // userInformation.setPkgDetail(userSnap
                    // shot.child(Username).getValue(UserInformation.class).getPkgDetail());
                    // userInformations.add(userInformation);
                    //UserInformation userInformation= dataSnapshot.getValue(UserInformation.class);

                        if (Username.equals(userInformation.getName()) && Pasword.equals(userInformation.getPassword())) {

                            Log.d(TAG,"Showname:" +Username);
                            Log.d(TAG,"showEmail:" +userInformation.getEmail());
                            Log.d(TAG,"showContactNo:" +userInformation.getPhoneNo());
                            Log.d(TAG,"showAddress:" +userInformation.getAddress());
                            ArrayList<String> array = new ArrayList();
                             /// adapter = new ArrayAdapter<String>(this,R.layout.loan_req,R.id.textView2,array);
//                             list.setAdapter(adapter);

                            Toast.makeText(LoginActivity.this, "You are Successfully Logged In", Toast.LENGTH_LONG).show();
                            Intent a = new Intent(LoginActivity.this,Homepage.class);
                            a.putExtra("Value",Username);
                            a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(a);
                           finish();

                        }
//                        if(!userSnapshot.exists()){
//                            databaseReference.setValue(new UserInformation(Username, Pasword));
//                        }

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
