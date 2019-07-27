package com.rams.kabulmuncipal;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import Utils.LocaleHelper;
import Utils.StaticDataHelper;

public class Language_setting extends BaseActivity {
Toolbar toolbar;
ImageView cross;
TextView  toolbar_title;
AppCompatButton but_complaint,but_recent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_langu);
        toolbar = findViewById(R.id.toolbar);
        but_complaint=findViewById(R.id.but_complaint);
        but_recent=findViewById(R.id.but_recent);
        toolbar_title=findViewById(R.id.toolbar_title);
       // String title=Issue_reporting.this.getResources().getString(R.string.issuereport);
        toolbar.setTitle("");
        cross=findViewById(R.id.cross);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar_title.setText(""+ Language_setting.this.getResources().getString(R.string.setting));


        but_complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Intent i = new Intent(Language_setting.this, Complaint_Register.class);
               // startActivity(i);
                dialog();

            }
        });

        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    void dialog(){
        String grpname[]={"Dari","Pastho"};

        String language= StaticDataHelper.getStringFromPreferences(Language_setting.this,StaticDataHelper.LANGUAGE);
        int m;
        if(language.equalsIgnoreCase("dari"))
        {
            m=0;
        }
        else
        {
            m=1;
        }

        AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
        //alt_bld.setIcon(R.drawable.icon);
        alt_bld.setTitle("Select a Language");
        alt_bld.setSingleChoiceItems(grpname,m, new DialogInterface
                .OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
              //  Toast.makeText(getApplicationContext(),
                       // "Group Name = "+grpname[item], Toast.LENGTH_SHORT).show();
               // dialog.dismiss();// dismiss the alertbox after chose option
               // LocaleHelper.setLocale(Language_setting.this, "en");
            }
        });

        alt_bld.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // user clicked OK
            }
        });
        alt_bld.setNegativeButton("Cancel", null);


///// grpname is a array where data is stored...
        AlertDialog dialog = alt_bld.create();
        dialog.show();

    }
}
