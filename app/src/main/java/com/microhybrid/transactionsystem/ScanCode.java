package com.microhybrid.transactionsystem;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.Result;

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
        //GenerateQR.resultScanTextview.setText(rawResult.getText());
        String s = rawResult.getText();

        char[] charString;
        charString= s.toCharArray();
        boolean nameFlag,emailFlag,dateFlag,amountFlag;
        nameFlag = true;
        emailFlag = false;
        dateFlag = false;
        amountFlag= false;

        String nameS,amountS,emailS,dateS;
        nameS="";
        amountS="";
        emailS="";
        dateS="";
        int i=0;
        while (nameFlag || amountFlag || emailFlag || dateFlag){

            if(nameFlag){

                if(charString[i]==':') {
                    i++;
                    continue;
                }else if(charString[i]!=';'){
                    nameS = nameS.concat(charString[i]+"");
                }else{
                    nameFlag  = false;
                    emailFlag = true;
                    i++;
                    continue;
                }
            }
            if(emailFlag){
                if(charString[i]==':'){
                    i++;
                    continue;
                }else if(charString[i]!=';'){
                    emailS = emailS.concat(charString[i]+"");
                }else{
                    emailFlag = false;
                    amountFlag = true;
                    i++;
                    continue;
                }
            }
            if(amountFlag){
                if(charString[i]==':'){
                    i++;
                    continue;
                }else if(charString[i]!=';'){
                    amountS = amountS.concat(charString[i]+"");
                }else{
                    amountFlag = false;
                    dateFlag = true;
                    i++;
                    continue;
                }
            }
            if(dateFlag){
                if(charString[i]==':'){
                    i++;
                    continue;
                }else if(charString[i]!=';'){
                    dateS = dateS.concat(charString[i]+"");
                }else{
                    dateFlag = false;
                    i++;
                    continue;
                }
            }
            i++;
        }

     //   GenerateQR.ScanResult.setText(nameS);

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
