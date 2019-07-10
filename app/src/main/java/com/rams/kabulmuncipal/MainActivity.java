package com.rams.kabulmuncipal;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureNavigationDrawer();
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        drawerLayout.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();
    }

    private void configureNavigationDrawer() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.navigation);






        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                int itemId = menuItem.getItemId();

                if (itemId == R.id.home) {
                  //  Intent i = new Intent(HomeActivity.this, My_Profile.class);
                  //  startActivity(i);
                }/* else if (itemId == R.id.selfie) {
                    Intent i = new Intent(MainActivity.this, ScanSoilActivity.class);
                    startActivity(i);

                } else if (itemId == R.id.activities) {

                }*/
                else if (itemId == R.id.about_us) {
                  //  Intent i = new Intent(HomeActivity.this, NearByDealers.class);
                  //  startActivity(i);

                }
                else if(itemId==R.id.language)
                {
                   // Intent i = new Intent(HomeActivity.this, DailyActivity.class);
                   // startActivity(i);
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
}
