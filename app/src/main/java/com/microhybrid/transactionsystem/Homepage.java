package com.microhybrid.transactionsystem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class Homepage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseUser CurrentUser;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth mAuth;
   // private Session session;
    TextView TVusername, TVuseremail;
    public static final String TAG = "YOUR-TAG-NAME";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            // User is signed in
            Intent i = new Intent(Homepage.this, Homepage.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        } else {
            // User is signed out
            Log.d(TAG, "onAuthStateChanged:signed_out");
        }

//        TVuseremail = findViewById(R.id.tvuseremail);
      //  TVusername = findViewById(R.id.tvusername);

       // String name = TVusername.getText().toString();
//        String username = TVusername.getText().toString();

        Intent i = getIntent();
          //TVusername.setText(i.getStringExtra("Values"));

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase =FirebaseDatabase.getInstance();
      //  CurrentUser = mAuth.getCurrentUser();

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

       // UpdateNavHeader();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
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
                finish();
                break;


            case R.id.nav_history:
                getSupportFragmentManager().beginTransaction().replace(R.id.Content_fragment,
                        new History()).commit();
                finish();
                break;

            case  R.id.logout:
                mAuth.getInstance().signOut();
                Intent out = new Intent(Homepage.this,LoginActivity.class);
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
//       TextView TVusername = (TextView) header.findViewById(R.id.tvusername);
//        TextView TVuseremail = (TextView) header.findViewById(R.id.tvuseremail);

   //    TVusername.setText(CurrentUser.getDisplayName());
//        TVuseremail.setText(CurrentUser.getEmail());
    }
}

