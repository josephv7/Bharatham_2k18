package com.roundmelon.iamjosephvarghese.bharatham_2k18;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Splash extends AppCompatActivity {

    TextView textView, textView4, textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        textView = findViewById(R.id.textView);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);

        Typeface bebas = Typeface.createFromAsset(getAssets(),  "fonts/bebasneue.ttf");

        textView4.setTypeface(bebas);
        textView3.setTypeface(bebas);
        textView.setTypeface(bebas);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent homeIntent = new Intent(Splash.this,Home.class);
                startActivity(homeIntent);
                finish();
            }
        },3000);
    }
}
