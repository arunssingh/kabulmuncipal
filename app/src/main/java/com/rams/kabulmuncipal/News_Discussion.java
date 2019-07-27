package com.rams.kabulmuncipal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import Adapter.News_Adapter;
import Model.NewsFeed;

public class News_Discussion extends BaseActivity {
Toolbar toolbar;
ImageView cross;
AppCompatButton but_complaint,but_face;
TextView toolbar_title;
RecyclerView recylerview;
ArrayList<NewsFeed> list = new ArrayList<>();
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_disc);
        list = new ArrayList<>();
        toolbar = findViewById(R.id.toolbar);
        but_complaint=findViewById(R.id.but_complaint);
        but_face=findViewById(R.id.but_face);
        toolbar_title=findViewById(R.id.toolbar_title);
        recylerview=findViewById(R.id.recylerview);
        layoutManager= new LinearLayoutManager(News_Discussion.this);
        recylerview.setLayoutManager(layoutManager);
        NewsFeed newsfed= new NewsFeed();
        newsfed.setName(""+"First aid round was held");
        newsfed.setTitle(""+" 18 Cancer 1398");
        newsfed.setMessage("1398, the alternative distribution of underlying assets, the first phase of the Kabul-South Transitional Road, while Sayedur Rahman Jalal Moin, financial and administrative");
        newsfed.setPic("http://km.gov.af/images/2027.jpg");
        NewsFeed newsfed1= new NewsFeed();
        newsfed1.setName(""+"First aid round was held");
        newsfed1.setTitle(""+" 18 Cancer 1398");
        newsfed1.setMessage("1398, the alternative distribution of underlying assets, the first phase of the Kabul-South Transitional Road, while Sayedur Rahman Jalal Moin, financial and administrative");
        newsfed1.setPic("http://km.gov.af/images/2036.jpg");
       list.add(newsfed);
        list.add(newsfed1);
        list.add(newsfed);
        list.add(newsfed1);
       // String title=Issue_reporting.this.getResources().getString(R.string.issuereport);
        toolbar.setTitle("");
        cross=findViewById(R.id.cross);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar_title.setText(""+ News_Discussion.this.getResources().getString(R.string.newsdis));
        News_Adapter adpter= new News_Adapter(News_Discussion.this,list);

        recylerview.setAdapter(adpter);




        but_complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://km.gov.af/"));
                startActivity(browserIntent);

            }
        });
        but_face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/KabulMunicipality/"));
                startActivity(browserIntent);
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
