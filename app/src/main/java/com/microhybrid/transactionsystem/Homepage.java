package com.microhybrid.transactionsystem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;


public class Homepage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


private FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseUser User;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth mAuth;
   // private Session session;
   public static TextView TVusername, TVuseremail ,TVNo,TVCOMP;
    public static final String TAG = "YOUR-TAG-NAME";
    String Userid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);


        mAuthListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
               User =FirebaseAuth.getInstance().getCurrentUser();

                if (User != null) {
                    // User is signed in
                    Log.d(TAG,"onAuthStateSignedin:" +User.getUid());
                    Intent a = new Intent(Homepage.this, GenerateQR.class);
                    a.putExtra("Name",User.getDisplayName());
                    Log.d(TAG,"onAuthStateSignedin:" +User.getDisplayName());


                    Intent i = new Intent(Homepage.this,Homepage.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);


                }
                else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    Toast.makeText(Homepage.this, "Successfully Signedout", Toast.LENGTH_SHORT).show();
                }

            }
        };




//        TVuseremail = findViewById(R.id.tvuseremail);
      //  TVusername = findViewById(R.id.tvusername);

       // String name = TVusername.getText().toString();
//        String username = TVusername.getText().toString();

        Intent i = getIntent();
        //TVusername.setText(i.getStringExtra("Values"));

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase =FirebaseDatabase.getInstance();
       User = mAuth.getCurrentUser();

      // session = new Session();

//        session.setName("Name");
//        session.getName();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent a = new Intent(Homepage.this, GenerateQR.class);
                startActivity(a);

            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        UpdateNavHeader();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent i = new Intent(Homepage.this,LoginActivity.class);
            startActivity(i);
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.homepage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent profile= new Intent(Homepage.this,profileActivity.class);
            startActivity(profile);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

           switch (item.getItemId()) {
            case R.id.nav_loanreq:
                getSupportFragmentManager().beginTransaction().replace(R.id.Content_fragment,
                        new LoanRequest()).commit();
                break;


            case R.id.nav_history:
                getSupportFragmentManager().beginTransaction().replace(R.id.Content_fragment,
                        new History()).commit();
                break;

            case  R.id.logout:
                mAuth.getInstance().signOut();
                Intent out = new Intent(Homepage.this,LoginActivity.class);
                Toast.makeText(Homepage.this, "Successfully Signed out", Toast.LENGTH_SHORT).show();
                out.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                out.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(out);
                finish();

//        int id = item.getItemId();
//
//            if (id == R.id.nav_notify) {
//                // Handle the camera action
//            } else if (id == R.id.nav_loanreq) {
//
//
//            } else if (id == R.id.nav_history) {

//        } else if (id == R.id.nav_tools) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {

            }



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void UpdateNavHeader(){

        NavigationView navigationView = findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        TVusername = (TextView) header.findViewById(R.id.tvusername);
        TVuseremail = (TextView) header.findViewById(R.id.tvuseremail);
        TVNo = (TextView) header.findViewById(R.id.TVphone);
        TVCOMP = (TextView) header.findViewById(R.id.TVcom);

        Intent b = getIntent();
        String x =b.getStringExtra("Name");
        String y =b.getStringExtra("Email");
        String z =b.getStringExtra("Num");
        String a =b.getStringExtra("company");
        TVusername.setText(x);
        TVuseremail.setText(y);
        TVNo.setText(z);
        TVCOMP.setText(a);
        TVNo.setVisibility(View.INVISIBLE);

        Intent c =  new Intent(Homepage.this,profileActivity.class);
        c.putExtra("Name", x);

        // b.putExtra("profileName",x);

       //  Log.d(TAG,"show message", );



//       TVusername.setText(User.getDisplayName());
//       TVuseremail.setText(User.getEmail());
    }

//    public  void onStart(){
//        super.onStart();
//        mAuth.addAuthStateListener(mAuthListener);
//    }
//
//    public void onStop(){
//        super.onStop();
//        mAuth.removeAuthStateListener(mAuthListener);
//    }


}

