package com.rams.kabulmuncipal;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Aboutus_Acitivity extends BaseActivity {
TextView toolbar_title,click;
Toolbar toolbar;
ImageView cross,open,hide,open1,hide1,open5,hide5,iv_developmentcredit;
TextView versiondetail,versiondetail_pricy;
RelativeLayout rl1,rl_credit;
CardView cv_gautman,cv_kabul;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about__acitivity);
        toolbar_title=findViewById(R.id.toolbar_title);
        toolbar = findViewById(R.id.toolbar);
        open=findViewById(R.id.open);
        hide=findViewById(R.id.hide);
        open5=findViewById(R.id.open5);
        hide5=findViewById(R.id.hide5);
        cv_kabul=findViewById(R.id.cv_kabul);
        cv_gautman=findViewById(R.id.cv_gautman);
        iv_developmentcredit=findViewById(R.id.iv_developmentcredit);
        versiondetail=findViewById(R.id.versiondetail);
        rl_credit=findViewById(R.id.rl_credit);
        open1=findViewById(R.id.open1);
        hide1=findViewById(R.id.hide1);
        versiondetail_pricy=findViewById(R.id.versiondetail_pricy);
        rl1=findViewById(R.id.rl1);
        // String title=Issue_reporting.this.getResources().getString(R.string.issuereport);
        toolbar.setTitle("");
        cross=findViewById(R.id.cross);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar_title.setText(""+ Aboutus_Acitivity.this.getResources().getString(R.string.about));

        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open.setVisibility(View.GONE);
                hide.setVisibility(View.VISIBLE);
                versiondetail.setVisibility(View.GONE);
            }
        });
        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open.setVisibility(View.VISIBLE);
                hide.setVisibility(View.GONE);

                versiondetail.setVisibility(View.VISIBLE);

            }
        });
        open5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open5.setVisibility(View.GONE);
                hide5.setVisibility(View.VISIBLE);
                rl_credit.setVisibility(View.GONE);
            }
        });
        hide5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open5.setVisibility(View.VISIBLE);
                hide5.setVisibility(View.GONE);

                rl_credit.setVisibility(View.VISIBLE);

            }
        });

        iv_developmentcredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/SHAHARAFG/"));
                startActivity(browserIntent);

            }
        });

        cv_gautman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/munis-transparencia-gobierno-abierto/municipalidad-de-chiantla"));
                startActivity(browserIntent);
            }
        });
        cv_kabul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/munis-transparencia-gobierno-abierto/municipalidad-de-chiantla"));
                startActivity(browserIntent);

            }
        });

        open1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open1.setVisibility(View.GONE);
                hide1.setVisibility(View.VISIBLE);
                rl1.setVisibility(View.GONE);
            }
        });
        hide1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open1.setVisibility(View.VISIBLE);
                hide1.setVisibility(View.GONE);

                rl1.setVisibility(View.VISIBLE);

            }
        });
    }
}
