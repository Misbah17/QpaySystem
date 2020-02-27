package com.microhybrid.transactionsystem;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.DateFormat;
import android.text.style.TtsSpan;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.zxing.WriterException;

import java.io.File;
import java.util.Date;
import java.util.List;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;


public class GenerateQR extends AppCompatActivity {

    FirebaseUser user;
    FirebaseAuth mauth;
    FirebaseDatabase databse;
    private DatabaseReference ref;
    String TAG = "GenerateQRCode";
  public static   EditText edtValue;
    ImageView qrImage;
    Button start, save;
    String inputValue;
    final String savePath = Environment.getExternalStorageDirectory().getPath() + "/QRCode/";
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    Button Scan;
    ArrayAdapter<String> adapter;
    private List<String> userInformations ;
    public  String st;
    public  static TextView tv;
    public static String amount;
    private StorageReference mStorageRef;
    public static Date date;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qr);


        qrImage = (ImageView) findViewById(R.id.imageQR);
        edtValue = (EditText) findViewById(R.id.etinputvalue);
        start = (Button) findViewById(R.id.Generateqr);
        tv= findViewById(R.id.text);

          //ScanResult.setVisibility(View.INVISIBLE);
     //   user =FirebaseAuth.getInstance().getCurrentUser();
        mStorageRef = FirebaseStorage.getInstance().getReference();


        if (user != null) {
            // User is signed in
            Intent i = new Intent(GenerateQR.this, GenerateQR.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        } else {
            // User is signed out
            Log.d(TAG, "onAuthStateChanged:signed_out");
        }
        // Intent i = getIntent();

        // resultScanTextview.setText(i.getStringExtra("Value"));

//        Bundle bundle = getIntent().getExtras();
//        resultScanTextview.setText(bundle.getString("Value"));




        //  save = (Button) findViewById(R.id.btngeneratecode);
     // st = FirebaseAuth.getInstance().getCurrentUser().getUid();


    }

    private void Generate() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        String amountInput = edtValue.getText().toString().trim();

       DatabaseReference ref = database.getReference().child("user Information");

         UserInformation userInformation = new UserInformation();
//
//        Intent b = getIntent();
//      //  String name= b.getStringExtra("Name");

        final String name;

        final String email;
        final String date;
        name = Homepage.TVusername.getText().toString();
       // name = userInformation.getName();
       email = Homepage.TVuseremail.getText().toString();
        amount = amountInput;
        date = transaction.date;



//

        inputValue = ":"  + name +";:" + email + ";:" + amount+ ";:" + date + ";";
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

            int inputvalue = Integer.parseInt(amountInput);
            int totalamount = UserHistory.money;
            String s = String.valueOf(totalamount);
            try {

                bitmap = qrgEncoder.encodeAsBitmap();
                qrImage.setImageBitmap(bitmap);

            } catch (WriterException e) {
                Log.v(TAG, e.toString());
            }


                if (inputvalue < totalamount) {

                    totalamount = totalamount - inputvalue;

//                    payment.totalamount.setText("Your remaning amount is " + totalamount);
                    Intent a = getIntent();
                    a.putExtra("Remain", totalamount);
                    Log.d(TAG, "Remaining amount" +totalamount);
                }
//            } catch (NullPointerException e) {
//                Log.v(TAG, e.toString());
//            }
        }
         else {
            edtValue.setError("Required");
        }




    }



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

