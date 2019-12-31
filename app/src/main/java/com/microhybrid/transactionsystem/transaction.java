package com.microhybrid.transactionsystem;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.List;

public class transaction extends ArrayAdapter<UserInformation> {

    private Activity context;
    private List<UserInformation> informationList;
    FirebaseUser user;

    public int transactionID;
    public static String user_name  ;
    public static String user_email = "dummy@email.com";
    public static String amount = "1234";
    public static String date = "21/12/2020";


    //
    public transaction(Activity context, List<UserInformation> infomationList){


        super(context, R.layout.list_layout,infomationList);
        this.context = context;
        this.informationList =infomationList;
    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        LayoutInflater inflater =context.getLayoutInflater();
//
//        View listviewItem = inflater.inflate(R.layout.list_layout,null,true);
//
//        TextView tvUsername = listviewItem.findViewById(R.id.username);
//        TextView tvUseremail = listviewItem.findViewById(R.id.useremail);
//
//        UserInformation  user = informationList.get(position);
//
//        tvUsername.setText(user.getName());
//        tvUseremail.setText(user.getEmail());
//
//        return  listviewItem;
//
//    }
}
