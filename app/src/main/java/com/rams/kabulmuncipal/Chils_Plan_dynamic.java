package com.rams.kabulmuncipal;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rams.kabulmuncipal.Application.MyUIApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Adapter.Develop_Adapter_Child;
import Model.Develop_Model.Childs;
import Model.Develop_Model.ModelDevelopment;
import Utils.Logger1;
import Utils.StaticDataHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Chils_Plan_dynamic extends BaseActivity {
Toolbar toolbar;
ImageView cross;
AppCompatButton but_complaint,but_recent;
TextView toolbar_title;
LinearLayout rlparent;
    ProgressDialog pDialog;
    ImageView open,hide,open1,hide1,open2,hide2,open3,hide3;
    LinearLayout ll_plan,ll_strag,ll_develop,ll_landplan;
    TextView plan1;
    ArrayList<Childs> developlist= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.development_plan_dynmic);
        toolbar = findViewById(R.id.toolbar);

        toolbar_title=findViewById(R.id.toolbar_title);
        rlparent=findViewById(R.id.rlparent);

        //
        developlist= new ArrayList<>();
       // String title=Issue_reporting.this.getResources().getString(R.string.issuereport);
        toolbar.setTitle("");
        cross=findViewById(R.id.cross);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        Bundle extras = getIntent().getExtras();
        String name=extras.getString("name");
        developlist=extras.getParcelableArrayList("childlist");
        Logger1.e("size","size="+developlist.size());
        toolbar_title.setText(""+ name);
        pDialog = new ProgressDialog(Chils_Plan_dynamic.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);



        Set_LibraryAdapter();

        //
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }






    //

    public void Set_LibraryAdapter() {

        rlparent.removeAllViews();

        for (int i = 0; i < developlist.size(); i++)

        {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View not_view = inflater.inflate(R.layout.child_row, null);

            final RelativeLayout main_lay = (RelativeLayout) not_view.findViewById(R.id.mainToggle);

            final ImageView open = (ImageView) not_view.findViewById(R.id.open);
            final ImageView hide = (ImageView) not_view.findViewById(R.id.hide);
            final TextView tv_name = (TextView) not_view.findViewById(R.id.tv_name);



            final WebView recyclerViewsub = (WebView) not_view.findViewById(R.id.recyclerViewsub);
           // recyclerViewsub.setHasFixedSize(false);
            recyclerViewsub.getSettings().setAllowContentAccess(true);
            recyclerViewsub.getSettings().setJavaScriptEnabled(true);
            recyclerViewsub.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

            recyclerViewsub.getSettings().setDomStorageEnabled(true);



            main_lay.setTag(i + "");
            open.setTag(i + "");
            hide.setTag(i + "");



            // final TextView tv_description = (TextView) not_view.findViewById(R.id.textView18);


            tv_name.setText(developlist.get(i).getPlanName());
            //recyclerViewsub.setText(""+developlist.get(i).getDescription());

            recyclerViewsub.setHapticFeedbackEnabled(false);


            InputStream is = null;
            try {
                is = getAssets().open("demo_child.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
            int size = 0;
            try {
                size = is.available();


                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();

                String str = new String(buffer);
                str = str.replace("Welcome to CustomFont Demo", developlist.get(i).getDescription());

                recyclerViewsub.loadDataWithBaseURL(null,str,"text/html","utf-8",null);
            } catch (IOException e) {
                e.printStackTrace();
            }

            final int finalI = i;
            main_lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    if(!developlist.get(finalI).getFile().equalsIgnoreCase(""))
                    {
                        Intent is = new Intent(Chils_Plan_dynamic.this, About_us.class);
                        is.putExtra("name",developlist.get(finalI).getPlanName());
                        is.putExtra("desc",developlist.get(finalI).getDescription());
                        is.putExtra("file",developlist.get(finalI).getFile());
                        startActivity(is);
                    }
                    else {
                        if (recyclerViewsub.getVisibility() == View.VISIBLE) {
                            recyclerViewsub.setVisibility(View.GONE);
                            open.setVisibility(View.GONE);
                            hide.setVisibility(View.VISIBLE);
                        } else {
                            recyclerViewsub.setVisibility(View.VISIBLE);
                            open.setVisibility(View.VISIBLE);
                            hide.setVisibility(View.GONE);
                        }
                    }
                }
            });
            /*open.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if(developlist.get(finalI).getChildrens().size()==0)
                    {
                        Intent is = new Intent(Devlopment_Plan_dynamic.this, About_us.class);
                        is.putExtra("name",developlist.get(finalI).getPlanName());
                        is.putExtra("desc",developlist.get(finalI).getDescription());
                        is.putExtra("file",developlist.get(finalI).getFile());
                        startActivity(is);
                    }
                    else {
                        open.setVisibility(View.GONE);
                        hide.setVisibility(View.VISIBLE);
                        recyclerViewsub.setVisibility(View.GONE);
                    }
                }
            });
            hide.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(developlist.get(finalI).getChildrens().size()==0)
                    {
                        Intent is = new Intent(Devlopment_Plan_dynamic.this, About_us.class);
                        is.putExtra("name",developlist.get(finalI).getPlanName());
                        is.putExtra("desc",developlist.get(finalI).getDescription());
                        is.putExtra("file",developlist.get(finalI).getFile());
                        startActivity(is);
                    }
                    else {
                        open.setVisibility(View.VISIBLE);
                        hide.setVisibility(View.GONE);

                        recyclerViewsub.setVisibility(View.VISIBLE);
                    }
                }
            });*/
    /*        main_lay.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    if (recyclerViewsub.getVisibility() == View.VISIBLE) {
                        recyclerViewsub.setVisibility(View.GONE);
                        //toggle_button_section.setSelected(false);


                    } else {
                        recyclerViewsub.setVisibility(View.VISIBLE);
                        //toggle_button_section.setSelected(true);


                    }
                }
            });*/


            rlparent.addView(not_view);
        }

    }
    //
}
