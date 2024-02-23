package com.sp.betternow;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FactsFragment extends Fragment {
//    ImageView imageView;
//    Button btSave;

    //BitmapDrawable drawable;
    //Bitmap bitmap;

    //OutputStream outputStream;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_facts, container, false);

//                imageView = view.findViewById(R.id.pic);
//                btSave = view.findViewById(R.id.saveimage);
//                btSave.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
//                        Bitmap bitmap = drawable.getBitmap();
//
//                        FileOutputStream outputStream = null;
//
//                        File filepath = Environment.getExternalStorageDirectory();
//                        File dir = new File(filepath.getAbsolutePath() + "/Demo");
//                        dir.mkdirs();
//
//                        String file = String.format("%d.jpg", System.currentTimeMillis());
//                        File outFile = new File(dir,file);
//
//                        //Toast.makeText(getContext(), "Image Saved", Toast.LENGTH_SHORT).show();
//
//                        //outputStream = new FileOutputStream(outFile);
//
//                        //File file = new File(dir, System.currentTimeMillis()+".jpg");
//                        try {
//                            outputStream = new FileOutputStream(outFile);
//                        } catch (FileNotFoundException e) {
//                            e.printStackTrace();
//                        }
//                            bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
//                        try {
//                            outputStream.flush();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            outputStream.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
////                        try {
////                            outputStream.close();
////                        } catch (IOException e) {
////                            e.printStackTrace();
////                        }
//                    }
//                });

        return view;
    }
}
