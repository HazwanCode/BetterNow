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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Helplines extends AppCompatActivity {
    private static final int REQUEST_CALL = 1;
    private TextView sos, samh, aware, tinkle, touch, imh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helplines);

        sos = findViewById(R.id.sos);
        samh = findViewById(R.id.samh);
        aware = findViewById(R.id.aware);
        tinkle = findViewById(R.id.tinkle);
        touch = findViewById(R.id.touch);
        imh = findViewById(R.id.imh);


    }

//    private View.OnClickListener onSOS = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Intent intent = new Intent();
//            intent.putExtra("phone", "65864662");
//            //startActivity(intent);
//            makePhoneCall();
//        }
//    };

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
                       .putExtra(ContactsContract.Intents.Insert.NAME, "SOS Helpline")
                       .putExtra(ContactsContract.Intents.Insert.PHONE, "1800221444")
                       .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK);
               startActivity(intent);

            }
        });

        alertDialog.show();
    }

    private void makePhoneCall() {
        String number = sos.getText().toString();
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(Helplines.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Helplines.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        } else {
            Toast.makeText(Helplines.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(Helplines.this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void showDialog1(View v) {
        android.app.AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Call Number?");
        alertDialog.setPositiveButton("Call", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                makePhoneCall1();

            }
        });

        alertDialog.setNegativeButton("Add Contact", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

                intent
                        .putExtra(ContactsContract.Intents.Insert.NAME, "SAMH Helpline")
                        .putExtra(ContactsContract.Intents.Insert.PHONE, "18002837019")
                        .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK);
                startActivity(intent);

            }
        });

        alertDialog.show();
    }

    private void makePhoneCall1() {
        String number = samh.getText().toString();
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(Helplines.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Helplines.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        } else {
            Toast.makeText(Helplines.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }

    public void showDialog2(View v) {
        android.app.AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Call Number?");
        alertDialog.setPositiveButton("Call", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                makePhoneCall2();

            }
        });

        alertDialog.setNegativeButton("Add Contact", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

                intent
                        .putExtra(ContactsContract.Intents.Insert.NAME, "AWARE Helpline")
                        .putExtra(ContactsContract.Intents.Insert.PHONE, "18007775555")
                        .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK);
                startActivity(intent);

            }
        });

        alertDialog.show();
    }

    private void makePhoneCall2() {
        String number = aware.getText().toString();
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(Helplines.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Helplines.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        } else {
            Toast.makeText(Helplines.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }

    public void showDialog3(View v) {
        android.app.AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Call Number?");
        alertDialog.setPositiveButton("Call", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                makePhoneCall3();

            }
        });

        alertDialog.setNegativeButton("Add Contact", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

                intent
                        .putExtra(ContactsContract.Intents.Insert.NAME, "Tinkle Helpline")
                        .putExtra(ContactsContract.Intents.Insert.PHONE, "18002744788")
                        .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK);
                startActivity(intent);

            }
        });

        alertDialog.show();
    }

    private void makePhoneCall3() {
        String number = tinkle.getText().toString();
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(Helplines.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Helplines.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        } else {
            Toast.makeText(Helplines.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }

    public void showDialog4(View v) {
        android.app.AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Call Number?");
        alertDialog.setPositiveButton("Call", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                makePhoneCall4();

            }
        });

        alertDialog.setNegativeButton("Add Contact", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

                intent
                        .putExtra(ContactsContract.Intents.Insert.NAME, "TOUCH Helpline")
                        .putExtra(ContactsContract.Intents.Insert.PHONE, "18003772252")
                        .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK);
                startActivity(intent);

            }
        });

        alertDialog.show();
    }

    private void makePhoneCall4() {
        String number = touch.getText().toString();
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(Helplines.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Helplines.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        } else {
            Toast.makeText(Helplines.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }

    public void showDialog5(View v) {
        android.app.AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Call Number?");
        alertDialog.setPositiveButton("Call", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                makePhoneCall5();

            }
        });

        alertDialog.setNegativeButton("Add Contact", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

                intent
                        .putExtra(ContactsContract.Intents.Insert.NAME, "IMH Helpline")
                        .putExtra(ContactsContract.Intents.Insert.PHONE, "63892222")
                        .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK);
                startActivity(intent);

            }
        });

        alertDialog.show();
    }

    private void makePhoneCall5() {
        String number = imh.getText().toString();
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(Helplines.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Helplines.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        } else {
            Toast.makeText(Helplines.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }
}