package com.sp.betternow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Calendar;

public class EntryInput extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private EditText entryName, entryDesc;
    //private Button buttonDate;
    private TextView textView, timeText;
    private FloatingActionButton buttonSave;
    private CardView addImage;
    private ImageView activityImage;
    private RadioGroup moodTypes;
    private Uri imageData, captureImage;
    String currentDateString = "No Date Selected";
    String currentTimeString = "No Time Selected";

    private DataHelper helper = null;
    private String entryID = "";

    //public static final int CAMERA_PERM_CODE = 101;
    //public static final int CAMERA_REQUEST_CODE = 102;
    public static final int GALLERY_REQUEST_CODE = 105;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_input);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        entryName = findViewById(R.id.name);
        entryDesc = findViewById(R.id.description);
        buttonSave = findViewById(R.id.save);
        buttonSave.setOnClickListener(onSave);
        //buttonDate = findViewById(R.id.dateButton);
        //buttonDate.setOnClickListener(onDate);
        textView = findViewById(R.id.showDate);
        textView.setOnClickListener(onDate);
        timeText = findViewById(R.id.showTime);
        timeText.setOnClickListener(onTime);
//        addImage = findViewById(R.id.onImage);
//        addImage.setOnClickListener(image_button);
        activityImage = findViewById(R.id.activity_image);
        moodTypes = findViewById(R.id.moodTypes);
        helper = new DataHelper(this);

        if (ContextCompat.checkSelfPermission(EntryInput.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(EntryInput.this, new String[]{
                Manifest.permission.CAMERA
            },
                    100);
        }

        entryID = getIntent().getStringExtra("ID");
        if (entryID != null) {
            load();
        }

    }

    public void showAlertDialog(View v) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setPositiveButton("Gallery", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent picture = new Intent();
                picture.setType("image/*");
                picture.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(picture, "Choose an image"), GALLERY_REQUEST_CODE);
            }
        });

        alertDialog.setNeutralButton("Camera", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent campicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(campicture,100);
            }
        });

        alertDialog.show();
    }


    private View.OnClickListener onDate = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DialogFragment datePicker = new DatePickerFragment();
            datePicker.show(getSupportFragmentManager(), "date picker");
        }
    };

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        textView.setText(currentDateString);
    }

    private View.OnClickListener onTime = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DialogFragment timePicker = new TimePickerFragment();
            timePicker.show(getSupportFragmentManager(), "time picker");
        }
    };

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        int hour = hourOfDay % 12;
        currentTimeString = String.format("%01d:%02d %s", hour == 0 ? 12 : hour,
                minute, hourOfDay < 12 ? "am" : "pm");
        timeText.setText(currentTimeString);

//        timeText.setText(String.format("%01d:%02d %s", hour == 0 ? 12 : hour,
//                minute, hourOfDay < 12 ? "am" : "pm"));

        //timeText.setText(hourOfDay + ":" + minute);
    }

//    private View.OnClickListener image_button = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Intent picture = new Intent();
//            picture.setType("image/*");
//            picture.setAction(Intent.ACTION_GET_CONTENT);
//            startActivityForResult(Intent.createChooser(picture, "Choose an image"), GALLERY_REQUEST_CODE);
//        }
//    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            imageData = data.getData();
            activityImage.setImageURI(imageData);
        }
        if (requestCode == 100) {
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            activityImage.setImageBitmap(captureImage);
            //imageData1 = data.getData();
            //activityImage.setImageURI(imageData1);
        }
    }

    @Override
    protected void onDestroy() {
        helper.close();
        super.onDestroy();
    }

    private void load() {
        Cursor c = helper.getById(entryID);
        c.moveToFirst();
        entryName.setText(helper.getActivityName(c));
        entryDesc.setText(helper.getActivityDesc(c));
        textView.setText(helper.getActivityDate(c));
        timeText.setText(helper.getActivityTime(c));
        //restaurantTel.setText(helper.getRestaurantTel(c));

        if (helper.getActivityMood(c).equals("Happy")) {
            moodTypes.check(R.id.radioButton1);
        } else if (helper.getActivityMood(c).equals("Neutral")) {
            moodTypes.check(R.id.radioButton2);
        } else if (helper.getActivityMood(c).equals("Sad")) {
            moodTypes.check(R.id.radioButton3);
        }
    }

    private View.OnClickListener onSave = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String nameStr = entryName.getText().toString();
            String descStr = entryDesc.getText().toString();
            String dateStr = currentDateString;
            String timeStr = currentTimeString;
            String moodStr = "No mood selected";
            switch (moodTypes.getCheckedRadioButtonId()) {
                case R.id.radioButton1:
                    moodStr = "Happy";
                    break;
                case R.id.radioButton2:
                    moodStr = "Neutral";
                    break;
                case R.id.radioButton3:
                    moodStr = "Sad";
                    break;
            }

            byte[] inputData = null;
            InputStream iStream = null;
            try {
                iStream = getContentResolver().openInputStream(imageData);
                ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
                int bufferSize = 1024;
                byte[] buffer = new byte[bufferSize];

                int len = 0;
                while ((len = iStream.read(buffer)) != -1) {
                    byteBuffer.write(buffer, 0, len);
                }
                inputData = byteBuffer.toByteArray();
            } catch (Exception e) {

            }

            if((TextUtils.isEmpty(entryName.getText().toString())) || (TextUtils.isEmpty(entryDesc.getText().toString()))) {
                Toast.makeText(getApplicationContext(), "Please fill up all the fields :)",Toast.LENGTH_LONG).show();
            } else {

                //helper.insert(nameStr, descStr, inputData, dateStr, timeStr, moodStr);

                if (entryID == null) {
                    helper.insert(nameStr, descStr, inputData, dateStr, timeStr, moodStr);
                } else {
                    helper.update(entryID, nameStr, descStr, inputData, dateStr, timeStr, moodStr);
                }

                finish();
            }
        }
    };

}