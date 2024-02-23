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
import android.widget.TextView;
import android.widget.Toast;

public class CounsellingCentre extends AppCompatActivity {

    private TextView ShanYou, SCC, CCC, Silver1, Silver2, Silver3, silWeb, shanWeb, sccWeb, cccWeb, silnum1, silnum2, silnum3, shannum, sccnum, cccnum;
    private static final int REQUEST_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counselling_centre);

        ShanYou = findViewById(R.id.shanyou);
        ShanYou.setOnClickListener(shanyoulocation);

        SCC = findViewById(R.id.scc);
        SCC.setOnClickListener(scclocation);

        CCC = findViewById(R.id.ccc);
        CCC.setOnClickListener(ccclocation);

        Silver1 = findViewById(R.id.silver1);
        Silver1.setOnClickListener(s1location);
        Silver2 = findViewById(R.id.silver2);
        Silver2.setOnClickListener(s2location);
        Silver3 = findViewById(R.id.silver3);
        Silver3.setOnClickListener(s3location);

        silWeb = findViewById(R.id.silweb);
        silWeb.setOnClickListener(onsilWeb);

        shanWeb = findViewById(R.id.shanweb);
        shanWeb.setOnClickListener(onshanWeb);

        cccWeb = findViewById(R.id.cccweb);
        cccWeb.setOnClickListener(oncccWeb);

        sccWeb = findViewById(R.id.sccweb);
        sccWeb.setOnClickListener(onsccWeb);

        silnum1 = findViewById(R.id.silnum1);
        silnum2 = findViewById(R.id.silnum2);
        silnum3 = findViewById(R.id.silnum3);
        shannum = findViewById(R.id.shannum);
        sccnum = findViewById(R.id.sccnum);
        cccnum = findViewById(R.id.cccnum);
    }

    private View.OnClickListener shanyoulocation = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent chooser = null;

            Uri gmmIntentUri = Uri.parse("geo:0,0?q=215 Upper Boon Keng Rd, Block 5");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            //chooser = Intent.createChooser(mapIntent, "Launch Maps");
            startActivity(mapIntent);

//            Intent i = new Intent(Intent.ACTION_VIEW);
//            i.setData(Uri.parse("geo:1.3127, 103.8726"));
//            chooser = Intent.createChooser(i, "Launch Maps");
//            startActivity(chooser);
        }
    };

    private View.OnClickListener scclocation = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent chooser = null;

            Uri gmmIntentUri = Uri.parse("geo:0,0?q=Singapore Counselling Centre");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);

        }
    };

    private View.OnClickListener ccclocation = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent chooser = null;

            Uri gmmIntentUri = Uri.parse("geo:0,0?q=Blk 536 Upper Cross Street, Hong Lim Complex, Counselling & Care Centre");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);

        }
    };

    private View.OnClickListener s1location = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent chooser = null;

            Uri gmmIntentUri = Uri.parse("geo:0,0?q=Blk 208 Serangoon Central, #01-238, Silver Ribbon (Singapore)");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);

        }
    };

    private View.OnClickListener s2location = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent chooser = null;

            Uri gmmIntentUri = Uri.parse("geo:0,0?q=Wisma Geylang Serai, 1 Engku Aman Turn Level 4, Singapore 408528");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);

        }
    };

    private View.OnClickListener s3location = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent chooser = null;

            Uri gmmIntentUri = Uri.parse("geo:0,0?q=Blk 550 Hougang Street 51 #01-168 S(530550)");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);

        }
    };

    private View.OnClickListener onsilWeb = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(CounsellingCentre.this, Web.class);
            intent.putExtra("url", "https://www.silverribbonsingapore.com/");
            startActivity(intent);
        }
    };

    private View.OnClickListener onshanWeb = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(CounsellingCentre.this, Web.class);
            intent.putExtra("url", "https://www.shanyou.org.sg/");
            startActivity(intent);
        }
    };

    private View.OnClickListener onsccWeb = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(CounsellingCentre.this, Web.class);
            intent.putExtra("url", "https://scc.sg/");
            startActivity(intent);
        }
    };

    private View.OnClickListener oncccWeb = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(CounsellingCentre.this, Web.class);
            intent.putExtra("url", "https://www.counsel.org.sg/");
            startActivity(intent);
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
                        .putExtra(ContactsContract.Intents.Insert.NAME, "Silver Ribbon Serangoon")
                        .putExtra(ContactsContract.Intents.Insert.PHONE, "63861928")
                        .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK);
                startActivity(intent);

            }
        });

        alertDialog.show();
    }

    private void makePhoneCall() {
        String number = silnum1.getText().toString();
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(CounsellingCentre.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(CounsellingCentre.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        } else {
                Toast.makeText(CounsellingCentre.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(CounsellingCentre.this, "Permission DENIED", Toast.LENGTH_SHORT).show();
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
                        .putExtra(ContactsContract.Intents.Insert.NAME, "Silver Ribbon Geylang")
                        .putExtra(ContactsContract.Intents.Insert.PHONE, "65090271")
                        .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK);
                startActivity(intent);

            }
        });

        alertDialog.show();
    }

    private void makePhoneCall1() {
        String number = silnum2.getText().toString();
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(CounsellingCentre.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(CounsellingCentre.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        } else {
            Toast.makeText(CounsellingCentre.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
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
                        .putExtra(ContactsContract.Intents.Insert.NAME, "Silver Ribbon Hougang")
                        .putExtra(ContactsContract.Intents.Insert.PHONE, "63853714")
                        .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK);
                startActivity(intent);

            }
        });

        alertDialog.show();
    }

    private void makePhoneCall2() {
        String number = silnum3.getText().toString();
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(CounsellingCentre.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(CounsellingCentre.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        } else {
            Toast.makeText(CounsellingCentre.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
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
                        .putExtra(ContactsContract.Intents.Insert.NAME, "Shan You")
                        .putExtra(ContactsContract.Intents.Insert.PHONE, "67419293")
                        .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK);
                startActivity(intent);

            }
        });

        alertDialog.show();
    }

    private void makePhoneCall3() {
        String number = shannum.getText().toString();
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(CounsellingCentre.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(CounsellingCentre.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        } else {
            Toast.makeText(CounsellingCentre.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
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
                        .putExtra(ContactsContract.Intents.Insert.NAME, "Singapore Counselling Centre")
                        .putExtra(ContactsContract.Intents.Insert.PHONE, "63395411")
                        .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK);
                startActivity(intent);

            }
        });

        alertDialog.show();
    }

    private void makePhoneCall4() {
        String number = sccnum.getText().toString();
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(CounsellingCentre.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(CounsellingCentre.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        } else {
            Toast.makeText(CounsellingCentre.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
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
                        .putExtra(ContactsContract.Intents.Insert.NAME, "Counselling & Care Centre")
                        .putExtra(ContactsContract.Intents.Insert.PHONE, "65366366")
                        .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK);
                startActivity(intent);

            }
        });

        alertDialog.show();
    }

    private void makePhoneCall5() {
        String number = cccnum.getText().toString();
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(CounsellingCentre.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(CounsellingCentre.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        } else {
            Toast.makeText(CounsellingCentre.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }
}