package com.rams.kabulmuncipal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Information_Activity extends BaseActivity {
Toolbar toolbar;
TextView toolbar_title;
ImageView cross;
LinearLayout development,muncipla,informations,publicservice,organization,rules,turist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        toolbar = findViewById(R.id.toolbar);
        toolbar_title=findViewById(R.id.toolbar_title);
        cross=findViewById(R.id.cross);
        development=findViewById(R.id.development);
        toolbar.setTitle("");
        toolbar_title.setText(""+ Information_Activity.this.getResources().getString(R.string.information));
        development.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Information_Activity.this, Devlopment_Plan_dynamic.class);
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
