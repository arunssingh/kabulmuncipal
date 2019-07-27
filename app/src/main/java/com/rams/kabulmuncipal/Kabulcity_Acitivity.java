package com.rams.kabulmuncipal;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Kabulcity_Acitivity extends BaseActivity {
TextView toolbar_title,click,tv_detail;
Toolbar toolbar;
ImageView cross;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kabulcity__acitivity);
        toolbar_title=findViewById(R.id.toolbar_title);
        tv_detail=findViewById(R.id.tv_detail);
        toolbar = findViewById(R.id.toolbar);
        click=findViewById(R.id.click);
        // String title=Issue_reporting.this.getResources().getString(R.string.issuereport);
        toolbar.setTitle("");
        cross=findViewById(R.id.cross);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        Bundle extras = getIntent().getExtras();
        String name=extras.getString("name");
        String desc=extras.getString("desc");
        toolbar_title.setText(""+name);
        click.setPaintFlags(click.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tv_detail.setText(""+ Html.fromHtml(desc));
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
}
