package com.sp.betternow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3500;

    Animation topAnim, bottomAnim, zoom;
    ImageView appLogo;
    TextView appName, powered;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences settings = getSharedPreferences("PREFS", 0);
        password = settings.getString("password", "");

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        zoom = AnimationUtils.loadAnimation(this, R.anim.zoom);

        appLogo = findViewById(R.id.logo);
        //appName = findViewById(R.id.appname);
        powered = findViewById(R.id.powered);

        appLogo.setAnimation(topAnim);
        appLogo.setAnimation(zoom);
        //appName.setAnimation(bottomAnim);
        powered.setAnimation(bottomAnim);

        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sound);
        mediaPlayer.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(password.equals("")) {
                    Intent homeIntent = new Intent(SplashScreen.this, HomeActivity.class);
                    startActivity(homeIntent);

                    finish();
                } else {
                    Intent passIntent = new Intent(SplashScreen.this, EnterPasswordActivity.class);
                    startActivity(passIntent);

                    finish();
                }

            }
        }, SPLASH_TIME_OUT);
    }
}