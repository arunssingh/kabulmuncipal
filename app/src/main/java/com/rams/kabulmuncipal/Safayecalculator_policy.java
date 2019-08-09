package com.rams.kabulmuncipal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Safayecalculator_policy extends AppCompatActivity {
    TextView toolbar_title;
    Toolbar toolbar;
    ImageView cross;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safayecalculator_policy);
        toolbar_title=findViewById(R.id.toolbar_title);
        toolbar = findViewById(R.id.toolbar);
        cross=findViewById(R.id.cross);
        toolbar_title.setText(""+Safayecalculator_policy.this.getResources().getString(R.string.safayepolicytitle));
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
