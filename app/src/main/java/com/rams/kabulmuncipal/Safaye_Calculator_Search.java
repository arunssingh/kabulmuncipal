package com.rams.kabulmuncipal;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class Safaye_Calculator_Search extends BaseActivity {
Toolbar toolbar;
TextView toolbar_title;
ImageView cross,iv_image;
    String sr="code";
    LinearLayout ll_name,ll_code;
    AppCompatButton bt_final;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safaye_search);
        toolbar = findViewById(R.id.toolbar);
        toolbar_title=findViewById(R.id.toolbar_title);
        cross=findViewById(R.id.cross);
        iv_image=findViewById(R.id.iv_image);
        ll_code=findViewById(R.id.ll_code);
        ll_name=findViewById(R.id.ll_name);
        bt_final=findViewById(R.id.bt_final);
        toolbar.setTitle("");
        toolbar_title.setText(""+ Safaye_Calculator_Search.this.getResources().getString(R.string.safayesearch));
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bt_final.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowTHankDialog();
            }
        });

    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked

        switch (view.getId()) {
            case R.id.radiomale:
                if (checked)
                    sr = "code";
                ll_code.setVisibility(View.VISIBLE);
                ll_name.setVisibility(View.GONE);
                break;
            case R.id.radiofemale:
                if (checked)
                    sr = "name";
                ll_code.setVisibility(View.GONE);
                ll_name.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void ShowTHankDialog()
    {
        final Dialog dialog = new Dialog(Safaye_Calculator_Search.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dailogsafayecalculatorsearch);
        dialog.setCancelable(false);
        TextView tv_safaye = (TextView) dialog.findViewById(R.id.tv_safaye);
        ImageView iv_cross=(ImageView) dialog.findViewById(R.id.iv_cross);


        iv_cross.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                dialog.dismiss();
                //

                //
                // startActivity(new Intent(CartOfProducts.this, HomeCategories.class));

                // finish();
            }
        });



        dialog.show();
    }

}
