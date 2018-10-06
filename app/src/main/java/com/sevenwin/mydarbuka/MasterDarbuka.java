package com.sevenwin.mydarbuka;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MasterDarbuka extends AppCompatActivity {
    TextView teksSlap,teksDum,teksRoll1,teksRoll2,teksKa1,teksKa2,teksTak;
    TextView teksSlap2,teksDum2,teksRoll12,teksRoll22,teksKa12,teksKa22,teksTak2;
    ImageView imgDarbuka,imgDarbuka2,imgRebanaKecil1,imgRebanaKecil2,imgRebanaKecil3,imgRebanaKecil4;
    private int sslap,sslap2;
    private int sdum,sdum2;
    private int ssroll1,ssroll12;
    private int ssroll2,ssroll22;
    private int ska1,ska12;
    private int ska2,ska22;
    private int stak,stak2;
    private int skecil1;
    private int skecil2;
    private int skecil3;
    private int skecil4;
    private SoundPool sp;
    AudioManager mAudioManager;
    RelativeLayout layUtama;
    AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.master_darbuka);
        deklarasi();
        action();
    }

    @Override
    public void onPause() {
        // This method should be called in the parent Activity's onPause() method.
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        // This method should be called in the parent Activity's onResume() method.
        if (mAdView != null) {
            mAdView.resume();
        }
        JenisDarbuka.isUtama = true;
    }

    @Override
    public void onDestroy() {
        // This method should be called in the parent Activity's onDestroy() method.
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

    private void deklarasi() {
        mAdView = findViewById(R.id.adview);
        layUtama = findViewById(R.id.layUtama);
        imgDarbuka = findViewById(R.id.imgDarbuka1);
        imgDarbuka2 = findViewById(R.id.imgDarbuka2);
        imgRebanaKecil1 = findViewById(R.id.rebana_kecil1);
        imgRebanaKecil2 = findViewById(R.id.rebana_kecil2);
        imgRebanaKecil3 = findViewById(R.id.rebana_kecil3);
        imgRebanaKecil4 = findViewById(R.id.rebana_kecil4);

        teksDum = findViewById(R.id.teksDum1);
        teksKa1 = findViewById(R.id.teksKa11);
        teksKa2 = findViewById(R.id.teksKa21);
        teksSlap = findViewById(R.id.teksSlap1);
        teksTak = findViewById(R.id.teksTak1);
        teksRoll1 = findViewById(R.id.teksRol11);
        teksRoll2 = findViewById(R.id.teksRol21);

        teksDum2 = findViewById(R.id.teksDum2);
        teksKa12 = findViewById(R.id.teksKa12);
        teksKa22 = findViewById(R.id.teksKa22);
        teksSlap2 = findViewById(R.id.teksSlap2);
        teksTak2 = findViewById(R.id.teksTak2);
        teksRoll12 = findViewById(R.id.teksRol12);
        teksRoll22 = findViewById(R.id.teksRol22);

        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sp = new SoundPool.Builder().setMaxStreams(5).build();
        } else {
            sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }
        sslap = sp.load(this, R.raw.slap, 1);
        sdum = sp.load(this, R.raw.dum, 1);
        ssroll1 = sp.load(this, R.raw.roll, 1);
        ssroll2 = sp.load(this, R.raw.roll2, 1);
        ska1 = sp.load(this, R.raw.ka, 1);
        ska2 = sp.load(this, R.raw.ka, 1);
        stak = sp.load(this, R.raw.tak, 1);

        sslap2 = sp.load(this, R.raw.bongo1, 1);
        sdum2 = sp.load(this, R.raw.dan1, 1);
        ssroll12 = sp.load(this, R.raw.darbuka5, 1);
        ssroll22 = sp.load(this, R.raw.roll2, 1);
        ska12 = sp.load(this, R.raw.darbuka2, 1);
        ska22 = sp.load(this, R.raw.darbuka2, 1);
        stak2 = sp.load(this, R.raw.bongo2, 1);

        skecil1 = sp.load(this, R.raw.uo1, 1);
        skecil2 = sp.load(this, R.raw.uo2, 1);
        skecil3 = sp.load(this, R.raw.uo3, 1);
        skecil4 = sp.load(this, R.raw.uo4, 1);
    }

    private void action() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }
        });
        if (BackgroundTheme.bgDarbuka == 0) {
            layUtama.setBackground(getResources().getDrawable(R.drawable.bg_darbuka1));
        } else if (BackgroundTheme.bgDarbuka == 1) {
            layUtama.setBackground(getResources().getDrawable(R.drawable.bg_darbuka2));
        }

        if (TemaDarbuka2.temaDarbuka1 == 0) {
            imgDarbuka.setImageDrawable(getResources().getDrawable(R.drawable.ic_darbuka1));
        } else if (TemaDarbuka2.temaDarbuka1 == 1) {
            imgDarbuka.setImageDrawable(getResources().getDrawable(R.drawable.ic_darbuka2));
        } else if (TemaDarbuka2.temaDarbuka1 == 2) {
            imgDarbuka.setImageDrawable(getResources().getDrawable(R.drawable.ic_darbuka3));
        } else if (TemaDarbuka2.temaDarbuka1 == 3) {
            imgDarbuka.setImageDrawable(getResources().getDrawable(R.drawable.ic_darbuka4));
        } else if (TemaDarbuka2.temaDarbuka1 == 4) {
            imgDarbuka.setImageDrawable(getResources().getDrawable(R.drawable.ic_darbuka5));
        } else if (TemaDarbuka2.temaDarbuka1 == 5) {
            imgDarbuka.setImageDrawable(getResources().getDrawable(R.drawable.ic_darbuka6));
        } else if (TemaDarbuka2.temaDarbuka1 == 6) {
            imgDarbuka.setImageDrawable(getResources().getDrawable(R.drawable.ic_darbuka7));
        } else if (TemaDarbuka2.temaDarbuka1 == 7) {
            imgDarbuka.setImageDrawable(getResources().getDrawable(R.drawable.ic_darbuka8));
        }

        if (TemaDarbuka2.temaDarbuka2 == 0) {
            imgDarbuka2.setImageDrawable(getResources().getDrawable(R.drawable.ic_darbuka1));
        } else if (TemaDarbuka2.temaDarbuka2 == 1) {
            imgDarbuka2.setImageDrawable(getResources().getDrawable(R.drawable.ic_darbuka2));
        } else if (TemaDarbuka2.temaDarbuka2 == 2) {
            imgDarbuka2.setImageDrawable(getResources().getDrawable(R.drawable.ic_darbuka3));
        } else if (TemaDarbuka2.temaDarbuka2 == 3) {
            imgDarbuka2.setImageDrawable(getResources().getDrawable(R.drawable.ic_darbuka4));
        } else if (TemaDarbuka2.temaDarbuka2 == 4) {
            imgDarbuka2.setImageDrawable(getResources().getDrawable(R.drawable.ic_darbuka5));
        } else if (TemaDarbuka2.temaDarbuka2 == 5) {
            imgDarbuka2.setImageDrawable(getResources().getDrawable(R.drawable.ic_darbuka6));
        } else if (TemaDarbuka2.temaDarbuka2 == 6) {
            imgDarbuka2.setImageDrawable(getResources().getDrawable(R.drawable.ic_darbuka7));
        } else if (TemaDarbuka2.temaDarbuka2 == 7) {
            imgDarbuka2.setImageDrawable(getResources().getDrawable(R.drawable.ic_darbuka8));
        }

        teksDum.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSound(sdum,motionEvent, teksDum);
            }
        });

        teksTak.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSound(stak,motionEvent, teksTak);
            }
        });

        teksKa1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSound(ska1,motionEvent, teksKa1);
            }
        });

        teksKa2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSound(ska2,motionEvent, teksKa2);
            }
        });

        teksRoll1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSound(ssroll1,motionEvent, teksRoll1);
            }
        });

        teksRoll2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSound(ssroll2,motionEvent, teksRoll2);
            }
        });

        teksSlap.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSound(sslap,motionEvent, teksSlap);
            }
        });

        imgRebanaKecil1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSoundKecil(skecil1,motionEvent, imgRebanaKecil1);
            }
        });

        imgRebanaKecil2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSoundKecil(skecil2,motionEvent, imgRebanaKecil2);
            }
        });

        imgRebanaKecil3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSoundKecil(skecil3,motionEvent, imgRebanaKecil3);
            }
        });

        imgRebanaKecil4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSoundKecil(skecil4,motionEvent, imgRebanaKecil4);
            }
        });

        teksDum2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSound2(sdum2,motionEvent, teksDum2);
            }
        });

        teksTak2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSound2(stak2,motionEvent, teksTak2);
            }
        });

        teksKa12.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSound2(ska12,motionEvent, teksKa12);
            }
        });

        teksKa22.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSound2(ska22,motionEvent, teksKa22);
            }
        });

        teksRoll12.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSound2(ssroll12,motionEvent, teksRoll12);
            }
        });

        teksRoll22.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSound2(ssroll22,motionEvent, teksRoll22);
            }
        });

        teksSlap2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSound2(sslap2,motionEvent, teksSlap2);
            }
        });
    }

    private boolean playSound(int id, MotionEvent motionEvent,TextView teksView) {
        Log.d("Motionsss  ",String.valueOf(id));
        if (motionEvent == null || motionEvent.getAction() == 0) {
            float origionalVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            sp.play(id, origionalVolume, origionalVolume, 0, 0, 1.0f);
            imgDarbuka.startAnimation(AnimationUtils.loadAnimation(MasterDarbuka.this,R.anim.zoom_out));
            teksView.startAnimation(AnimationUtils.loadAnimation(MasterDarbuka.this,R.anim.zoom_in));
        }
        return true;
    }
    private boolean playSound2(int id, MotionEvent motionEvent,TextView teksView) {
        Log.d("Motionsss  ",String.valueOf(id));
        if (motionEvent == null || motionEvent.getAction() == 0) {
            float origionalVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            sp.play(id, origionalVolume, origionalVolume, 0, 0, 1.0f);
            imgDarbuka2.startAnimation(AnimationUtils.loadAnimation(MasterDarbuka.this,R.anim.zoom_out));
            teksView.startAnimation(AnimationUtils.loadAnimation(MasterDarbuka.this,R.anim.zoom_in));
        }
        return true;
    }
    private boolean playSoundKecil(int id, MotionEvent motionEvent,ImageView imgView) {
        Log.d("Motionsss  ",String.valueOf(id));
        if (motionEvent == null || motionEvent.getAction() == 0) {
            float origionalVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            sp.play(id, origionalVolume, origionalVolume, 0, 0, 1.0f);
            imgView.startAnimation(AnimationUtils.loadAnimation(MasterDarbuka.this,R.anim.zoom_in));
        }
        return true;
    }
}
