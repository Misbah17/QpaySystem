package com.microhybrid.transactionsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

 import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class profileActivity extends AppCompatActivity {

    TextView  name, email, Phn;
    private static final String TAG = "";
    FirebaseAuth mauth;
    String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);


        name=findViewById(R.id.profilename);
        email=findViewById(R.id.profileemail);
          Phn =findViewById(R.id.phoneno);
        Homepage hme = new Homepage();
        Intent b = getIntent();
        String s = b.getStringExtra("Name");
       // email.setText();
      email.setText(Homepage.TVuseremail.getText());
      name.setText(Homepage.TVusername.getText());
      Phn.setText(Homepage.TVNo.getText());
      Phn.setVisibility(View.VISIBLE);

      Intent q = new Intent(profileActivity.this,GenerateQR.class);
      q.putExtra("Name", Homepage.TVusername.getText());
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        String userid = user.getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = database.getReference().child("userInformation");
      //  DatabaseReference currentuser= databaseReference.child(uid);


        final   String Username = name.getText().toString();
        final String Pasword = email.getText().toString();
     //   mauth= FirebaseAuth.getInstance();

        String id = databaseReference.getKey();
      Log.d(TAG,"KEY" +id);

        databaseReference.child(id).addValueEventListener(new ValueEventListener() {



            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> userInformations = new ArrayList<>();
                //  UserInformation users = dataSnapshot.getValue(UserInformation.class);
                userInformations.clear();

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {

                    //  UserInformation userInformation= new UserInformation();
                    UserInformation userInformation = userSnapshot.getValue(UserInformation.class);
                   // Log.d(TAG, userInformation)
                 //   userSnapshot.getClass();

//                        if(userSnapshot.exists()){
//                            email.setText(userInformation.getPhoneNo());
//
//                    }
//                    else{
//                        Toast.makeText(profileActivity.this,"User is not accessible",Toast.LENGTH_SHORT).show();
//                    }

                    ArrayList<String> array = new ArrayList();





//                    userInformation.getName()


//                    ArrayAdapter adapter = new ArrayAdapter(LoginActivity.this,R.layout.loan_req, array);
//                    list.setAdapter(adapter);

                    //  adapter = new ArrayAdapter<UserInformation>(this,R.layout.loan_req,R.id.textView2,userinfo);
                    ///  list.setAdapter(adapter);
                    // userInformation.setPkgDetail(userSnap
                    // shot.child(Username).getValue(UserInformation.class).getPkgDetail());
                    // userInformations.add(userInformation);
                    //UserInformation userInformation= dataSnapshot.getValue(UserInformation.class);
//                  i
//  email.setText(LoginActivity.password.getText());


                }
            }









            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(profileActivity.this,"User Does not Exits, Please Try Again" ,Toast.LENGTH_LONG).show();
                //      Log.w(TAG, "Failed to read value.", error.toException());

                //  UserInformation users = databaseError.toString(UserInformation.class);

            }
        });


    }



}
