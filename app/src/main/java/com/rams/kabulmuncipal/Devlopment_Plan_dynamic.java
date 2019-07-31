package com.rams.kabulmuncipal;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rams.kabulmuncipal.Application.MyUIApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Adapter.Complaint_Adapter;
import Adapter.Develop_Adapter_Child;
import Model.Develop_Model.ModelDevelopment;
import Model.Model_Complaint.ModelComplaint;
import Utils.Logger1;
import Utils.StaticDataHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Devlopment_Plan_dynamic extends BaseActivity {
Toolbar toolbar;
ImageView cross;
AppCompatButton but_complaint,but_recent;
TextView toolbar_title;
LinearLayout rlparent;
    ProgressDialog pDialog;
    ImageView open,hide,open1,hide1,open2,hide2,open3,hide3;
    LinearLayout ll_plan,ll_strag,ll_develop,ll_landplan;
    TextView plan1;
    ArrayList<ModelDevelopment> developlist= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.development_plan_dynmic);
        toolbar = findViewById(R.id.toolbar);

        toolbar_title=findViewById(R.id.toolbar_title);
        rlparent=findViewById(R.id.rlparent);

        //

       // String title=Issue_reporting.this.getResources().getString(R.string.issuereport);
        toolbar.setTitle("");
        cross=findViewById(R.id.cross);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        Bundle extras = getIntent().getExtras();
        String name=extras.getString("name");
        String id=extras.getString("id");
        toolbar_title.setText(""+ name);
        pDialog = new ProgressDialog(Devlopment_Plan_dynamic.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);


        if (StaticDataHelper.isNetworkConnected(Devlopment_Plan_dynamic.this)) {
            getdevelopment("Dari",id);
        }
        else
        {
            StaticDataHelper.Show_Dialog(Devlopment_Plan_dynamic.this);
        }


        //
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getdevelopment(String lang,String id) {

        showpDialog();

        developlist= new ArrayList<>();


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<String> call1 = apiService.getdevelopment(lang,id);
        call1.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Log.e("HomeActivity", "Response" + response.body());


                if (response != null && response.isSuccessful()) {
                    try {

                        // List<HomeModel> lists = new ArrayList<>();
                        JSONObject obj = new JSONObject(response.body().toString());

                        if(obj.optBoolean("status")) {


                            List<ModelDevelopment> lists = Arrays.asList(MyUIApplication.getInstance().getGson().fromJson(obj.optJSONArray("data").toString(),
                                    ModelDevelopment[].class));

                            //  issuelist.add(district);

                            developlist.addAll(lists);

                           // Complaint_Adapter adpter= new Complaint_Adapter(Recent_Complaint.this,complaintlist);

                           // recylerview.setAdapter(adpter);
                            Set_LibraryAdapter();
                            hidepDialog();
                        }

                        //  madpter.addAll(lists, false);

                        //adapter_parent = new CustomAdapterParent(HomeActivity.this,list);
                        //recyclerView_parent.setAdapter(adapter_parent);



                    } catch (JSONException e) {
                        e.printStackTrace();
                        hidepDialog();
                    }
                }
                hidepDialog();

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("HomeActivity", t.toString());
                hidepDialog();

            }

        });
    }


    private void showpDialog() {
        if (!pDialog.isShowing()) {
            pDialog.show();
        }
    }

    private void hidepDialog() {
        if (pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }

    //

    public void Set_LibraryAdapter() {

        rlparent.removeAllViews();

        for (int i = 0; i < developlist.size(); i++)

        {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View not_view = inflater.inflate(R.layout.development_row, null);

            final RelativeLayout main_lay = (RelativeLayout) not_view.findViewById(R.id.mainToggle);

            final ImageView open = (ImageView) not_view.findViewById(R.id.open);
            final ImageView hide = (ImageView) not_view.findViewById(R.id.hide);
            final TextView tv_name = (TextView) not_view.findViewById(R.id.tv_name);
            final ImageView clicknext=not_view.findViewById(R.id.clicknext);


            final RecyclerView recyclerViewsub = (RecyclerView) not_view.findViewById(R.id.recyclerViewsub);
           // recyclerViewsub.setHasFixedSize(false);
            recyclerViewsub.setLayoutManager(new LinearLayoutManager(Devlopment_Plan_dynamic.this,LinearLayoutManager.VERTICAL,false));
            Logger1.e("shekhawatsize","shekhawatsize"+i+"size="+developlist.get(i).getChildrens().size());
            Develop_Adapter_Child adapter = new Develop_Adapter_Child(Devlopment_Plan_dynamic.this, developlist.get(i).getChildrens());
            recyclerViewsub.setAdapter(adapter);


            main_lay.setTag(i + "");
            open.setTag(i + "");
            hide.setTag(i + "");



            // final TextView tv_description = (TextView) not_view.findViewById(R.id.textView18);

            if(developlist.get(i).getChildrens().size()==0)
            {
                open.setVisibility(View.GONE);
                hide.setVisibility(View.GONE);
            }
            else
            {

            }


            tv_name.setText(developlist.get(i).getPlanName());


            final int finalI = i;
            main_lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    //
                    if(developlist.get(finalI).getChildrens().size()==0)
                    {
                        Intent is = new Intent(Devlopment_Plan_dynamic.this, About_us.class);
                        is.putExtra("name",developlist.get(finalI).getPlanName());
                        is.putExtra("desc",developlist.get(finalI).getDescription());
                        is.putExtra("file",developlist.get(finalI).getFile());
                        startActivity(is);
                    }
                    else {

                        Intent is = new Intent(Devlopment_Plan_dynamic.this, Chils_Plan_dynamic.class);
                        is.putExtra("name",developlist.get(finalI).getPlanName());
                        is.putParcelableArrayListExtra("childlist", (ArrayList<? extends Parcelable>) developlist.get(finalI).getChildrens());

                        startActivity(is);

                    }

                    //

                    /*if(developlist.get(finalI).getChildrens().size()==0)
                    {
                        Intent is = new Intent(Devlopment_Plan_dynamic.this, About_us.class);
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
                    }*/
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
        hidepDialog();
    }
    //
}
