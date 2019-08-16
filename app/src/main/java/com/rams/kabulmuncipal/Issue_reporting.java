package com.rams.kabulmuncipal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip;

public class Issue_reporting extends BaseActivity {
Toolbar toolbar;
ImageView cross;
LinearLayout ll_registration,ll_recent;
AppCompatButton but_complaint,but_recent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_reporting);
        toolbar = findViewById(R.id.toolbar);
        but_complaint=findViewById(R.id.but_complaint);
        but_recent=findViewById(R.id.but_recent);
        ll_recent=findViewById(R.id.ll_recent);
        ll_registration=findViewById(R.id.ll_registration);
       // String title=Issue_reporting.this.getResources().getString(R.string.issuereport);
        toolbar.setTitle("");
        cross=findViewById(R.id.cross);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);




        ll_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               new SimpleTooltip.Builder(Issue_reporting.this)
                        .anchorView(ll_registration)
                        .text("he hello")
                        .gravity(Gravity.BOTTOM)
                        .build()
                        .show();

          /*      Intent i = new Intent(Issue_reporting.this, Complaint_Register.class);
                startActivity(i);*/

            }
        });
        ll_recent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Issue_reporting.this, Recent_Complaint.class);
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
