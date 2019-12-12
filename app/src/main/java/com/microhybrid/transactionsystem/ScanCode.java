package com.microhybrid.transactionsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.zxing.Result;
import com.google.zxing.qrcode.encoder.QRCode;

import java.util.logging.Logger;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanCode extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    ZXingScannerView mScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);



     //      val qrCodeScanner = findViewById(R.id.qrCodeScanner);
        // val = findViewById<ZXingScannerView>(R.id.qrCodeScanner);

    }
//    override fun onResume() {
//        super.onResume()
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),
//                        MY_CAMERA_REQUEST_CODE)
//                return
//            }
//        }
//        qrCodeScanner.startCamera()
//        qrCodeScanner.setResultHandler(this)
//    }



    @Override
    public void onResume() {
        super.onResume();
        // Register ourselves as a handler for scan results.
        mScannerView.setResultHandler(this);
        // Start camera on resume
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        // Stop camera on pause
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        GenerateQR.resultScanTextview.setText(rawResult.getText());
        onBackPressed();
        // Do something with the result here
        // Prints scan results
      //  Logger.verbose("result", rawResult.getText());
        // Prints the scan format (qrcode, pdf417 etc.)
        //Logger.verbose("result", rawResult.getBarcodeFormat().toString());
        //If you would like to resume scanning, call this method below:
        //mScannerView.resumeCameraPreview(this);
        //Intent intent = new Intent();
       // intent.putExtra(AppConstants.KEY_QR_CODE, rawResult.getText());
//        setResult(RESULT_OK, intent);
//        finish();
    }
}
