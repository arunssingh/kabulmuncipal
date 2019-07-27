package com.rams.kabulmuncipal;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import Model.Model_Complaint.ModelComplaint;

public class Complaintdetail_Acitivity extends BaseActivity {
TextView toolbar_title,click;
Toolbar toolbar;
ImageView cross,open,hide,open1,hide1;
TextView name,email,phonenmuber,distict,issue;
RelativeLayout rl1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complaintdetail_acitivity);
        toolbar_title=findViewById(R.id.toolbar_title);
        toolbar = findViewById(R.id.toolbar);

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        phonenmuber=findViewById(R.id.phonenmuber);
        distict=findViewById(R.id.distict);
        issue=findViewById(R.id.issue);
        Bundle extras = getIntent().getExtras();
        ModelComplaint model=extras.getParcelable("obj");


        // String title=Issue_reporting.this.getResources().getString(R.string.issuereport);
        toolbar.setTitle("");
        cross=findViewById(R.id.cross);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar_title.setText(""+ model.getIds());
        name.setText(""+model.getFname());
        email.setText(""+model.getEmail());
        phonenmuber.setText(""+model.getPhone());
        distict.setText(""+model.getDistrict().toString());
        issue.setText(""+model.getIssues().getName());

        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
