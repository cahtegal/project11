package com.hidevs.realdarbuka;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TemaDarbuka2 extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dotsLayout;
    Button btnOk1;
    private int[] layouts;
    public static int temaDarbuka1 = 0, temaDarbuka2 = 2;
    boolean isTema1 = true;
    int posisi = 0;
    TextView teksTema;
    ImageView imgBack;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tema_darbuka2);
        imgBack = findViewById(R.id.imgBack);
        teksTema = findViewById(R.id.teksTheme);
        teksTema.setText("Choose Your FIRST Darbuka's Theme");
        viewPager = findViewById(R.id.view_pager);
        dotsLayout = findViewById(R.id.layoutDots);
        btnOk1 = findViewById(R.id.btnOkTema1);
        isTema1 = true;

        // layouts of all welcome sliders
        // add few more layouts if you want
        layouts = new int[]{
                R.layout.darbuka_theme1,
                R.layout.darbuka_theme2,
                R.layout.darbuka_theme3,
                R.layout.darbuka_theme4,
                R.layout.darbuka_theme5,
                R.layout.darbuka_theme6,
                R.layout.darbuka_theme7,
                R.layout.darbuka_theme8};

        // adding bottom dots
        addBottomDots(0);

        // making notification bar transparent
        changeStatusBarColor();

        TemaDarbuka2.MyViewPagerAdapter myViewPagerAdapter = new TemaDarbuka2.MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        btnOk1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                btnOk1.startAnimation(AnimationUtils.loadAnimation(TemaDarbuka2.this,R.anim.zoom_out2));
                if (isTema1) {
                    teksTema.setText("Choose Your SECOND Darbuka's Theme");
                    temaDarbuka1 = posisi;
                    isTema1 = false;
                } else {
                    temaDarbuka2 = posisi;
                    isTema1 = true;
                    finish();
                }
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgBack.startAnimation(AnimationUtils.loadAnimation(TemaDarbuka2.this,R.anim.zoom_out2));
                if (!isTema1) {
                    isTema1 = true;
                    teksTema.setText("Choose Your FIRST Darbuka's Theme");
                } else {
                    finish();
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBackPressed() {
        if (!isTema1) {
            isTema1 = true;
            teksTema.setText("Choose Your FIRST Darbuka's Theme");
        } else {
            finish();
        }
    }

    private void addBottomDots(int currentPage) {
        TextView[] dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
            posisi = position;
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        MyViewPagerAdapter() {
        }

        @SuppressWarnings("ConstantConditions")
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = null;
            if (layoutInflater != null) {
                view = layoutInflater.inflate(layouts[position], container, false);
            }
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}
