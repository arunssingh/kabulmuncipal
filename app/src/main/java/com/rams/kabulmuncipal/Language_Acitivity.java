package com.rams.kabulmuncipal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import Utils.StaticDataHelper;

public class Language_Acitivity extends BaseActivity {
TextView toolbar_title,click;
Toolbar toolbar;
ImageView cross;
CheckBox check_dari,check_pastho;
RelativeLayout  rl1,rl2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language_acitivity);
        toolbar_title=findViewById(R.id.toolbar_title);
        toolbar = findViewById(R.id.toolbar);
        check_pastho=findViewById(R.id.check_pastho);
        check_dari=findViewById(R.id.check_dari);
        rl1=findViewById(R.id.rl1);
        rl2=findViewById(R.id.rl2);


        // String title=Issue_reporting.this.getResources().getString(R.string.issuereport);
        toolbar.setTitle("");
        cross=findViewById(R.id.cross);
        cross.setVisibility(View.GONE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar_title.setText(""+ Language_Acitivity.this.getResources().getString(R.string.seelctlanghaue));
        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                check_pastho.setChecked(false);

                check_dari.setChecked(true);
             //   Intent i = new Intent(Language_Acitivity.this,MainActivity.class);
             //   startActivity(i);
              //  finish();
            }
        });
        rl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_pastho.setChecked(true);

                check_dari.setChecked(false);

              //  Intent i = new Intent(Language_Acitivity.this,MainActivity.class);
               // startActivity(i);
               // finish();

            }
        });
        check_pastho.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    StaticDataHelper.setBooleanInPreferences(Language_Acitivity.this,StaticDataHelper.isUserLogin,true);

                    StaticDataHelper.setStringInPreferences(Language_Acitivity.this,StaticDataHelper.LANGUAGE,"PASHTO");
                    check_dari.setChecked(false);
                    Intent i = new Intent(Language_Acitivity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    check_dari.setChecked(true);
                }

            }
        });
        check_dari.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    StaticDataHelper.setStringInPreferences(Language_Acitivity.this,StaticDataHelper.LANGUAGE,"DARI");
                    StaticDataHelper.setBooleanInPreferences(Language_Acitivity.this,StaticDataHelper.isUserLogin,true);

                    check_pastho.setChecked(false);
                    Intent i = new Intent(Language_Acitivity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    check_pastho.setChecked(true);
                }

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
