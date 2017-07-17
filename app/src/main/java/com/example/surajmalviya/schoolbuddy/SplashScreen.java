package com.example.surajmalviya.schoolbuddy;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    private TextView appNameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        appNameView = (TextView)findViewById(R.id.appName);
        Typeface font = Typeface.createFromAsset(getAssets(),"Oregano-Regular.ttf");
        appNameView.setTypeface(font);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent launchMainActivity = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(launchMainActivity);
                finish();
            }
        }, 4000);

    }
}
