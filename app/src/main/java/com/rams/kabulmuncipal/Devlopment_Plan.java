package com.rams.kabulmuncipal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Devlopment_Plan extends BaseActivity {
Toolbar toolbar;
ImageView cross;
AppCompatButton but_complaint,but_recent;
TextView toolbar_title;
    ImageView open,hide,open1,hide1,open2,hide2,open3,hide3;
    LinearLayout ll_plan,ll_strag,ll_develop,ll_landplan;
    TextView plan1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.development_plan);
        toolbar = findViewById(R.id.toolbar);
        but_complaint=findViewById(R.id.but_complaint);
        but_recent=findViewById(R.id.but_recent);
        toolbar_title=findViewById(R.id.toolbar_title);

        //

        open=findViewById(R.id.open);
        hide=findViewById(R.id.hide);
        ll_plan=findViewById(R.id.ll_plan);
        open1=findViewById(R.id.open1);
        hide1=findViewById(R.id.hide1);
        ll_strag=findViewById(R.id.ll_strag);
        open2=findViewById(R.id.open2);
        hide2=findViewById(R.id.hide2);
        ll_develop=findViewById(R.id.ll_develop);
        open3=findViewById(R.id.open3);
        hide3=findViewById(R.id.hide3);
        ll_landplan=findViewById(R.id.ll_landplan);
        plan1=findViewById(R.id.plan1);
       // String title=Issue_reporting.this.getResources().getString(R.string.issuereport);
        toolbar.setTitle("");
        cross=findViewById(R.id.cross);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar_title.setText(""+Devlopment_Plan.this.getResources().getString(R.string.developmentplan_base));


        plan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Devlopment_Plan.this, Kabulcity_Acitivity.class);
                startActivity(i);

            }
        });
        but_recent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent i = new Intent(Devlopment_Plan.this, Recent_Complaint.class);
               // startActivity(i);
            }
        });

        //
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open.setVisibility(View.GONE);
                hide.setVisibility(View.VISIBLE);
                ll_plan.setVisibility(View.GONE);
            }
        });
        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open.setVisibility(View.VISIBLE);
                hide.setVisibility(View.GONE);

                ll_plan.setVisibility(View.VISIBLE);

            }
        });


        open1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open1.setVisibility(View.GONE);
                hide1.setVisibility(View.VISIBLE);
                ll_strag.setVisibility(View.GONE);
            }
        });
        hide1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open1.setVisibility(View.VISIBLE);
                hide1.setVisibility(View.GONE);

                ll_strag.setVisibility(View.VISIBLE);

            }
        });

        open2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open2.setVisibility(View.GONE);
                hide2.setVisibility(View.VISIBLE);
                ll_develop.setVisibility(View.GONE);
            }
        });
        hide2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open2.setVisibility(View.VISIBLE);
                hide2.setVisibility(View.GONE);

                ll_develop.setVisibility(View.VISIBLE);

            }
        });


        open3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open3.setVisibility(View.GONE);
                hide3.setVisibility(View.VISIBLE);
                ll_landplan.setVisibility(View.GONE);
            }
        });
        hide3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open3.setVisibility(View.VISIBLE);
                hide3.setVisibility(View.GONE);

                ll_landplan.setVisibility(View.VISIBLE);

            }
        });
        //
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
