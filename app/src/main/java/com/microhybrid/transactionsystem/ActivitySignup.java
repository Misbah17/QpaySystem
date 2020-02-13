package com.microhybrid.transactionsystem;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ActivitySignup extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private View view;
    FirebaseAuth.AuthStateListener mAuthListener;
    ProgressBar ProgressBar;
    FirebaseDatabase database;
    DatabaseReference ref;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    EditText Name, Email, password, Cpassword, phone, Location;
    Button Register;
    private UserInformation userInformation;
    DatabaseReference databaseReference;
    private String category = "";
    RadioGroup radioGroup;
    RadioButton radBussiness;
    RadioButton radCustomer;
    EditText Companyname;
    TextView tvload;
    DataSnapshot snapshot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        Name = findViewById(R.id.etname);
        Email = findViewById(R.id.etmail);
        password = findViewById(R.id.etpass);
        Cpassword = findViewById(R.id.etconfirmpass);
        Register = findViewById(R.id.btnregister);
        phone = findViewById(R.id.edphone);
        Location = findViewById(R.id.address);
        ProgressBar = findViewById(R.id.Pbar);
        radioGroup = findViewById(R.id.radioGroup);
        radCustomer = findViewById(R.id.rbcustomer);
        radBussiness = findViewById(R.id.rbusiness);
        Companyname = findViewById(R.id.etCompanyname);
      tvload = findViewById(R.id.tvtoast);


        //////RadioButton Text Visibilty

        Companyname.setVisibility(View.INVISIBLE);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedId) {

                Companyname.setVisibility(View.INVISIBLE);
                if (checkedId == R.id.rbusiness) {
                    Companyname.setVisibility(View.VISIBLE);

                }
            }
        });

        /////Firebasedatabase data Save
        Intent i = getIntent();
        tvload.setText(i.getStringExtra("Value"));

        databaseReference = FirebaseDatabase.getInstance().getReference().child("userInformation");
        userInformation = new UserInformation();
    }


    private void addCustomer() {

        String RBC = radCustomer.getText().toString();
        String RBB = radBussiness.getText().toString();

        ////Strings of all UI

        String email = Email.getText().toString().trim();
        String Pasword = password.getText().toString();
        String name = Name.getText().toString();
        String phn = phone.getText().toString();
        String adress = Location.getText().toString();
        String confirmpwd = Cpassword.getText().toString();
        String rdcustomer = radCustomer.getText().toString();
        String rdbusiness = radBussiness.getText().toString();
        String companyname = Companyname.getText().toString();
        String load = tvload.getText().toString();
        //radioGroup.getCheckedRadioButtonId() == -1   ||(Cpassword.getText().toString()).matches(password.getText().toString())
        if ( !(TextUtils.isEmpty(email) || TextUtils.isEmpty(name) || TextUtils.isEmpty(phn)
                || TextUtils.isEmpty(adress) || TextUtils.isEmpty(Pasword) || TextUtils.isEmpty(confirmpwd)
                 ||radioGroup.getCheckedRadioButtonId() == -1)){



            String id = databaseReference.push().getKey();


            userInformation.setName(Name.getText().toString());
            userInformation.setEmail(Email.getText().toString());
            userInformation.setPhoneNo(phone.getText().toString());
            userInformation.setPassword(password.getText().toString());
            userInformation.setAddress(Location.getText().toString());


            databaseReference.child(id).setValue(userInformation);
            // databaseReference.child(Rbutton).setValue("UserInformation");

            Toast.makeText(ActivitySignup.this, "You Have Successfully Registered Yourself", Toast.LENGTH_SHORT).show();
            Intent a = new Intent(ActivitySignup.this, LoginActivity.class);
               //a.putExtra("Values" ,name);
               startActivity(a);
               finish();
            /////radio button selected item

            switch (radioGroup.getCheckedRadioButtonId()) {
                case R.id.rbcustomer: {
                    databaseReference.child(id).child("Category").setValue(RBC);
                    if (!radCustomer.isChecked()){
                        Toast.makeText(ActivitySignup.this, "Select the category  ", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

                case R.id.rbusiness:
                    userInformation.setCompanyName(Companyname.getText().toString());
                    databaseReference.child(id).child("Category").setValue(RBB);
                    databaseReference.child(id).child("Company Name").setValue(companyname);
                    break;


//

            }


        }

        else if (TextUtils.isEmpty(email)) {
            Toast.makeText(ActivitySignup.this, "Fill the Fields First ", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(name)) {
            Toast.makeText(ActivitySignup.this, "Fill the Name field ", Toast.LENGTH_SHORT).show();
            Name.setError("Required");
        } else if (TextUtils.isEmpty(email)) {
            Toast.makeText(ActivitySignup.this, "Fill the Email field ", Toast.LENGTH_SHORT).show();
            Email.setError("Required");

        } else if (TextUtils.isEmpty(phn)) {
            Toast.makeText(ActivitySignup.this, "Fill the PhoneNo field ", Toast.LENGTH_SHORT).show();
            phone.setError("Required");
        }
//        else if (radioGroup.getCheckedRadioButtonId() == -1) {
//            Toast.makeText(ActivitySignup.this, "Select the Category ", Toast.LENGTH_SHORT).show();
//            //radCustomer.setError("Required");
//        }
        else if (TextUtils.isEmpty(adress)) {
            Toast.makeText(ActivitySignup.this, "Fill the Address field ", Toast.LENGTH_SHORT).show();
            Location.setError("Required");
        } else if (TextUtils.isEmpty(Pasword)) {
            Toast.makeText(ActivitySignup.this, "Fill the Password field ", Toast.LENGTH_SHORT).show();
            password.setError("Required");
        }
//        } else if (TextUtils.isEmpty(confirmpwd)) {
//            Toast.makeText(ActivitySignup.this, " Fill the Confirm Password Field", Toast.LENGTH_SHORT).show();
//            Cpassword.setError("Required");
//        }
//        else if (!(radBussiness.isSelected() && radCustomer.isSelected())) {
//            Toast.makeText(ActivitySignup.this, "Select the Category ", Toast.LENGTH_SHORT).show();
//            //radCustomer.setError("Required");
//        }

        else if (!(Cpassword.getText().toString()).matches(password.getText().toString())) {
            //Toast.makeText(ActivitySignup.this, "Confirm your Password", Toast.LENGTH_SHORT).show();
            Cpassword.setError("Your Password Does not Match");
        }


   // tvload.setText("Busy Registering User.......please Wait");



    }








    @Override
    public void onClick(View v) {

        ///Data save on firebase on clicking the register button////

        addCustomer();
//        String confirmpwd = Cpassword.getText().toString();
//        String Pasword = password.getText().toString();

    }





    /*RadioButtonClick*/

        public void radiobuttonclick(View view) {
        String RBC = radCustomer.getText().toString();
        String RBB =radBussiness.getText().toString();
            boolean checked = ((RadioButton) view).isChecked();


    }

        }




















