package com.rams.kabulmuncipal;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.NavigationView;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.ArrayList;
import java.util.HashMap;

import Model.ModelSlider;

public class MainActivity extends BaseActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    LinearLayout issuereporting,sales_calc,news,information;
    ActionBarDrawerToggle mDrawerToggle;
    SliderLayout slider;
    ArrayList<ModelSlider> sliderlist=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureNavigationDrawer();
        toolbar = findViewById(R.id.toolbar);
        issuereporting=findViewById(R.id.issuereporting);
        sales_calc=findViewById(R.id.sales_calc);
        news=findViewById(R.id.news);
        information=findViewById(R.id.information);
        slider = findViewById(R.id.slider);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        mDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );


        drawerLayout.setDrawerListener(mDrawerToggle);

       // slidershow();
        setDefaultAdapter();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle.setDrawerIndicatorEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_navigation_menu);
        mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        mDrawerToggle.syncState();

        issuereporting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Issue_reporting.class);
                startActivity(i);
            }
        });
        sales_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Safaye_Calculatorwithfloor.class);
                startActivity(i);
            }
        });
        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Information_Activity_dynmic.class);
                startActivity(i);
            }
        });
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, News_Discussion.class);
                startActivity(i);
            }
        });

    }

    private void configureNavigationDrawer() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.navigation);






        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                int itemId = menuItem.getItemId();

                if (itemId == R.id.home) {

                    if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                        drawerLayout.closeDrawer(GravityCompat.START);
                    }
              /*      finish();
                    Intent i = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(i);*/
                }/* else if (itemId == R.id.selfie) {
                    Intent i = new Intent(MainActivity.this, ScanSoilActivity.class);
                    startActivity(i);

                } else if (itemId == R.id.activities) {

                }*/
                else if (itemId == R.id.about_us) {
                    if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                        drawerLayout.closeDrawer(GravityCompat.START);
                    }
                    Intent i = new Intent(MainActivity.this, Aboutus_Acitivity.class);
                   startActivity(i);

                }
                else if(itemId==R.id.language)
                {
                    if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                        drawerLayout.closeDrawer(GravityCompat.START);
                    }
                    Intent i = new Intent(MainActivity.this, Language_setting.class);
                    startActivity(i);
                }

             /*   } else if (itemId == R.id.feed) {

                } */ /*else if (itemId == R.id.logout) {
                    //Intent i = new Intent(HomeActivity.this, LoginActivity.class);
                    // startActivity(i);

                    String device_id = StaticDataHelper.getStringFromPreferences(HomeActivity.this, StaticDataHelper.DEVICEID);
                    String address = StaticDataHelper.getADDRESS(HomeActivity.this);


                    finish();

                }*/
                return false;
            }

        });
    }

    private  void slidershow() {

        sliderlist=new ArrayList<>();
        ModelSlider modelslider= new ModelSlider();
        modelslider.setName("");
        modelslider.setPic("https://km.gov.af/images/407.jpg");

        ModelSlider modelslider1= new ModelSlider();
        modelslider1.setName("");
        modelslider1.setPic("https://km.gov.af/images/484.jpg");

        ModelSlider modelslider2= new ModelSlider();
        modelslider2.setName("");
        modelslider2.setPic("https://km.gov.af/images/1974.png");
        sliderlist.add(modelslider);
        sliderlist.add(modelslider1);
        sliderlist.add(modelslider2);

        for (int i = 0; i < sliderlist.size(); i++) {
            //images.put(modelList.get(i).getAdTitle(), modelList.get(i).getAdImageUrl());


            TextSliderView textSliderView = new TextSliderView(MainActivity.this);
            // initialize a SliderLayout
            textSliderView
                    .description(" ")
                    .image(sliderlist.get(i).getPic())
                    .setScaleType(BaseSliderView.ScaleType.CenterInside);
            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra","");
            textSliderView.getBundle().putInt("index", i);



            slider.addSlider(textSliderView);
        }


        slider.setPresetTransformer(SliderLayout.Transformer.ZoomOut);
        slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);

        slider.setCustomAnimation(new DescriptionAnimation());
        slider.setDuration(5000);
        if (sliderlist.size() <= 1) {
            slider.stopAutoCycle();
            slider.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Invisible);
        }
    }
    public void setDefaultAdapter() {
        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("0", R.drawable.slider1);
        file_maps.put("1", R.drawable.slider2);
        file_maps.put("2", R.drawable.slider3);

        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(MainActivity.this);
            // initialize a SliderLayout
            textSliderView
                    // .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);


            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            slider.addSlider(textSliderView);
        }
        slider.setPresetTransformer(SliderLayout.Transformer.ZoomOut);
        slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
       // slider.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Invisible);
        slider.setCustomAnimation(new DescriptionAnimation());
        slider.setDuration(5000);

    }

}
