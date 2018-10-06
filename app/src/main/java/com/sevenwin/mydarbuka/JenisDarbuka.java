package com.sevenwin.mydarbuka;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class JenisDarbuka extends AppCompatActivity {

    LinearLayout layDarbuka1, layDarbuka2;
    ImageView imgBack;
    public static boolean iklanOpen = false, isUtama = false, isLama = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jenis_darbuka);
        deklarasi();
        action();
    }

    @Override
    protected void onResume() {
        super.onResume();
        iklanOpen = false;
        isUtama = false;
    }

    private void deklarasi() {
        imgBack = findViewById(R.id.imgBack);
        layDarbuka1 = findViewById(R.id.layDarbuka1);
        layDarbuka2 = findViewById(R.id.layDarbuka2);
    }

    private void action() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgBack.startAnimation(AnimationUtils.loadAnimation(JenisDarbuka.this, R.anim.zoom_out2));
                finish();
            }
        });
        layDarbuka1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isLama = false;
                layDarbuka1.startAnimation(AnimationUtils.loadAnimation(JenisDarbuka.this, R.anim.zoom_out2));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo netInfo = conMgr != null ? conMgr.getActiveNetworkInfo() : null;
                        if (netInfo == null) {
                            startActivity(new Intent(JenisDarbuka.this, Beginner.class));
                        } else {
                            iklan(true);
                        }
                    }
                }, 300);
            }
        });

        layDarbuka2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isLama = false;
                layDarbuka2.startAnimation(AnimationUtils.loadAnimation(JenisDarbuka.this, R.anim.zoom_out2));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo netInfo = conMgr != null ? conMgr.getActiveNetworkInfo() : null;
                        if (netInfo == null) {
                            startActivity(new Intent(JenisDarbuka.this, MasterDarbuka.class));
                        } else {
                            iklan(false);
                        }
                    }
                }, 300);
            }
        });
    }

    private void iklan(final boolean is1Darbuka) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait ...");
        progressDialog.show();

        final InterstitialAd mInterstitialAd = new InterstitialAd(JenisDarbuka.this);
        mInterstitialAd.setAdUnitId("ca-app-pub-4165439494743762/6594497318");
        mInterstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    if (!iklanOpen) {
                        iklanOpen = true;
                        isLama = true;
                    }
                }
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                if (!isUtama) {
                    isLama = true;
                    progressDialog.dismiss();
                    if (is1Darbuka) {
                        startActivity(new Intent(JenisDarbuka.this, Beginner.class));
                    } else {
                        startActivity(new Intent(JenisDarbuka.this, MasterDarbuka.class));
                    }
                }
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                if (!isUtama) {
                    isLama = true;
                    progressDialog.dismiss();
                    if (is1Darbuka) {
                        startActivity(new Intent(JenisDarbuka.this, Beginner.class));
                    } else {
                        startActivity(new Intent(JenisDarbuka.this, MasterDarbuka.class));
                    }
                }
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);

        if (!isLama) {
            isLama = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!iklanOpen) {
                        iklanOpen = true;
                        if (!isUtama) {
                            try {
                                progressDialog.dismiss();
                                if (is1Darbuka) {
                                    startActivity(new Intent(JenisDarbuka.this, Beginner.class));
                                } else {
                                    startActivity(new Intent(JenisDarbuka.this, MasterDarbuka.class));
                                }
                            } catch (IllegalArgumentException exp) {
                              exp.printStackTrace();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
            }, 3000);
        }
    }
}
