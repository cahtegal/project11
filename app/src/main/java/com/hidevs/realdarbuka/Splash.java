package com.hidevs.realdarbuka;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Splash extends AppCompatActivity {

    ImageView logoJadis;
    RelativeLayout rlCahTegal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        logoJadis = findViewById(R.id.logo_realdarbukapro);
        rlCahTegal = findViewById(R.id.rlCahtegal);
        logoJadis.setVisibility(View.GONE);
        Animation animation = AnimationUtils.loadAnimation(Splash.this, R.anim.blink);
        rlCahTegal.setAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rlCahTegal.setVisibility(View.GONE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showLogo();
                    }
                },200);
            }

        }, 1000);
    }

    private void showLogo() {
        Animation animation = AnimationUtils.loadAnimation(Splash.this, R.anim.blink);
        logoJadis.setAnimation(animation);
        logoJadis.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                startActivity(new Intent(Splash.this,Menu.class));
            }
        },2000);
    }
}
