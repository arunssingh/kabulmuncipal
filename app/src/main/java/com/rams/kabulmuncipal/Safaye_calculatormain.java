package com.rams.kabulmuncipal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Safaye_calculatormain extends BaseActivity {
Toolbar toolbar;
ImageView cross;
TextView toolbar_title;
LinearLayout ll_registration,ll_recent;
AppCompatButton but_complaint,but_recent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.safyemain_reporting);
        toolbar = findViewById(R.id.toolbar);
        but_complaint=findViewById(R.id.but_complaint);
        but_recent=findViewById(R.id.but_recent);
        ll_recent=findViewById(R.id.ll_recent);
        ll_registration=findViewById(R.id.ll_registration);
        toolbar_title=findViewById(R.id.toolbar_title);
        String title=Safaye_calculatormain.this.getResources().getString(R.string.safayecal);
        toolbar.setTitle("");
        toolbar_title.setText(""+title);
        cross=findViewById(R.id.cross);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);




        ll_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  new SimpleTooltip.Builder(Issue_reporting.this)
                        .anchorView(ll_registration)
                        .text("he hello")
                        .gravity(Gravity.BOTTOM)
                        .build()
                        .show();*/

                Intent i = new Intent(Safaye_calculatormain.this, Safaye_Calculatorwithfloor.class);
                startActivity(i);

            }
        });
        ll_recent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Safaye_calculatormain.this, Safaye_Calculator_Search.class);
                startActivity(i);
            }
        });
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
