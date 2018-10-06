package com.sevenwin.mydarbuka;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

public class Menu extends AppCompatActivity {

    ImageView imgPlay,imgShare,imgRateUs,imgTemaDarbuka,imgBgDarbuka;
    String appPackageName = BuildConfig.APPLICATION_ID; // getPackageName() from Context or Activity object
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        declaration();
        action();
        startAnimationPlay();
    }

    private void declaration() {
        imgPlay = findViewById(R.id.imgPlay);
        imgBgDarbuka = findViewById(R.id.imgBgTheme);
        imgTemaDarbuka = findViewById(R.id.imgDarbukaTheme);
        imgRateUs = findViewById(R.id.imgRateUs);
        imgShare = findViewById(R.id.imgShare);
    }

    private void action() {
        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgPlay.startAnimation(AnimationUtils.loadAnimation(Menu.this,R.anim.zoom_out2));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Menu.this,JenisDarbuka.class));
                    }
                },300);
            }
        });
        imgTemaDarbuka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgTemaDarbuka.startAnimation(AnimationUtils.loadAnimation(Menu.this,R.anim.zoom_out2));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Menu.this,JenisTemaDarbuka.class));
                    }
                },300);
            }
        });
        imgShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgShare.startAnimation(AnimationUtils.loadAnimation(Menu.this,R.anim.zoom_out2));
                final Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Play darbuka with funny and happy, Get it on Play Store https://play.google.com/store/apps/details?id="+ appPackageName);
                sendIntent.setType("text/plain");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(sendIntent);
                    }
                },300);
            }
        });
        imgRateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgRateUs.startAnimation(AnimationUtils.loadAnimation(Menu.this,R.anim.zoom_out2));
                try {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                        }
                    },300);
                } catch (ActivityNotFoundException e) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                        }
                    },300);
                }
            }
        });
        imgBgDarbuka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgBgDarbuka.startAnimation(AnimationUtils.loadAnimation(Menu.this,R.anim.zoom_out2));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Menu.this,BackgroundTheme.class));                    }
                },300);
            }
        });
    }

    @Override
    public void onBackPressed() {
        dialogOut();
    }

    private void startAnimationPlay() {
        final AnimatorSet animatorSet1 = new AnimatorSet();
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(imgPlay, "scaleY", 0.6f);
        scaleY.setDuration(200);
        ObjectAnimator scaleYBack = ObjectAnimator.ofFloat(imgPlay, "scaleY", 1f);
        scaleYBack.setDuration(500);
        scaleYBack.setInterpolator(new BounceInterpolator());
        animatorSet1.setStartDelay(600);
        animatorSet1.playSequentially(scaleY, scaleYBack);
        animatorSet1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                animatorSet1.setStartDelay(500);
                animatorSet1.start();
            }
        });
        imgPlay.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        animatorSet1.start();
    }
    private void dialogOut() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Menu.this);
        alertDialogBuilder.setTitle("Quit");
        alertDialogBuilder
                .setMessage("You want to quit now ?")
                .setCancelable(false)
                .setPositiveButton("Yes, I want play again later", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .setNegativeButton("No, I like this game", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
