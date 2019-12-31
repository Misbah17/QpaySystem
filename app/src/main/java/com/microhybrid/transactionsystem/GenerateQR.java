package com.microhybrid.transactionsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.WriterException;

import java.util.ArrayList;
import java.util.List;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;


public class GenerateQR extends AppCompatActivity {

    FirebaseUser user;
    FirebaseAuth mauth;
    FirebaseDatabase databse;
    private DatabaseReference ref;
    String TAG = "GenerateQRCode";
    EditText edtValue;
    ImageView qrImage;
    Button start, save;
    String inputValue;
    final String savePath = Environment.getExternalStorageDirectory().getPath() + "/QRCode/";
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    Button Scan;
    ArrayAdapter<String> adapter;
    public static ListView list;
    public static EditText resultScanTextview;
    private List<String> userInformations ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qr);


        Scan = findViewById(R.id.btnscan);
        resultScanTextview = findViewById(R.id.ScanResult);

        // Intent i = getIntent();

        // resultScanTextview.setText(i.getStringExtra("Value"));

//        Bundle bundle = getIntent().getExtras();
//        resultScanTextview.setText(bundle.getString("Value"));

        Scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ScanCode.class));
            }
        });

        qrImage = (ImageView) findViewById(R.id.imageQR);
        edtValue = (EditText) findViewById(R.id.etinputvalue);
        start = (Button) findViewById(R.id.Generateqr);
       // list = findViewById(R.id.listviewuser);
        //  save = (Button) findViewById(R.id.btngeneratecode);
        mauth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

    }

    private void Generate() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        String amountInput = edtValue.getText().toString().trim();
        DatabaseReference ref = database.getReference().child("user Information");

        final UserInformation userInformation = new UserInformation();


        final String amount;
        final String email;
        final String date;


        email = transaction.user_email;
        amount = amountInput;
        date = transaction.date;

//          userInformations = new ArrayList<String>();
//
//          adapter = new ArrayAdapter<String>(this, R.layout.activity_generate_qr,R.id.username, userInformations);
        ref.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //  String amountInput = edtValue.getText().toString().trim();

                //   final String amount; amount = amountInput;
//                userInformations.clear();
                final String name = transaction.user_name;
                List<String> userinfo = new ArrayList<>();
                for (DataSnapshot Snapshot : dataSnapshot.getChildren()) {

                    userinfo.add(Snapshot.getKey());
                    UserInformation retriveinfo = Snapshot.getValue(UserInformation.class);

                    //   name.matches(retriveinfo.getName());
                  //  userInformations.add(userInformation.getName().toString()+ "  "+userInformation.getEmail());
                    //  name.concat(userInformation.getName());
                    //   resultScanTextview.setText(retriveinfo.getName());

                }

                inputValue = ":"  + name +";:" + email + ";:" + amount + ";:" + date + ";";

                // list.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        if (amountInput.length() > 0) {
            WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();

            Point point = new Point();
            display.getSize(point);
            int width = 1000;
            int height = 700;
            int smallerDimension = 1000 > 700 ? width : height;
            smallerDimension = smallerDimension * 1000 / 700;

            qrgEncoder = new QRGEncoder(
                    inputValue, null,
                    QRGContents.Type.TEXT,
                    smallerDimension);
            try {
                bitmap = qrgEncoder.encodeAsBitmap();
                qrImage.setImageBitmap(bitmap);
            } catch (WriterException e) {
                Log.v(TAG, e.toString());
            }
        } else {
            edtValue.setError("Required");
        }


    }
//            }
//        });

//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean save;
//                String result;
//                try {
//                    save = QRGSaver.save(savePath, edtValue.getText().toString().trim(), bitmap, QRGContents.ImageType.IMAGE_JPEG);
//                    result = save ? "Image Saved" : "Image Not Saved";
//                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });


    public void GenerateQR (View view){

        Generate();
    }
}

