package com.sp.betternow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class About extends AppCompatActivity {

    private TextView myEmail, myNumber;
    private static final int REQUEST_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        myEmail = findViewById(R.id.myemail);
        myEmail.setOnClickListener(onEmail);

        myNumber = findViewById(R.id.mynumber);
    }

    private View.OnClickListener onEmail = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            Intent intent = new Intent(Intent.ACTION_SEND);
//            intent.setType("plain/text");
//            intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "HAZWANAHMAD.18@ichat.sp.edu.sg" });
            //intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
            //intent.putExtra(Intent.EXTRA_TEXT, "mail body");
//            startActivity(Intent.createChooser(intent, ""));

//            Intent intent = new Intent(Intent.ACTION_VIEW);
//            Uri data = Uri.parse("mailto:recipient@example.com?subject=" + Uri.encode(subject) + "&body=" + Uri.encode(body));
//            intent.setData(data);
//            startActivity(intent);

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:HAZWANAHMAD.18@ichat.sp.edu.sg")); // only email apps should handle this
            //intent.putExtra(Intent.EXTRA_EMAIL, addresses);
            //intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }

        }
    };

    public void showDialog(View v) {
        android.app.AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Call Number?");
        alertDialog.setPositiveButton("Call", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                makePhoneCall();

            }
        });

        alertDialog.setNegativeButton("Add Contact", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

                intent
                        .putExtra(ContactsContract.Intents.Insert.NAME, "Hazwan Bin Ahmad Zaki")
                        .putExtra(ContactsContract.Intents.Insert.PHONE, "96615301")
                        .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK);
                startActivity(intent);

            }
        });

        alertDialog.show();
    }

    private void makePhoneCall() {
        String number = myNumber.getText().toString();
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(About.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(About.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        } else {
            Toast.makeText(About.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(About.this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}