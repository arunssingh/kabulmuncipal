package com.rams.kabulmuncipal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rams.kabulmuncipal.Application.MyUIApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Adapter.Complaint_Adapter;
import Adapter.Information_Adapter;
import Model.InformationModel;
import Model.Model_Complaint.ModelComplaint;
import Model.NewsFeed;
import Utils.StaticDataHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Information_Activity_dynmic extends BaseActivity {
    Toolbar toolbar;
    TextView toolbar_title;
    ImageView cross;
    ProgressDialog pDialog;

    GridLayoutManager manager;
    LinearLayout development, muncipla, informations, publicservice, organization, rules, turist;
    RecyclerView recylerview;
    ArrayList<InformationModel> complaintlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_dynamic);
        toolbar = findViewById(R.id.toolbar);
        toolbar_title = findViewById(R.id.toolbar_title);
        cross = findViewById(R.id.cross);
        pDialog = new ProgressDialog(Information_Activity_dynmic.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        recylerview = findViewById(R.id.recylerview);
        toolbar.setTitle("");
        toolbar_title.setText("" + Information_Activity_dynmic.this.getResources().getString(R.string.information));
     /*   development.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Information_Activity_dynmic.this, Devlopment_Plan_dynamic.class);
                startActivity(i);
            }
        });*/

        manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recylerview.setLayoutManager(manager);

        if (StaticDataHelper.isNetworkConnected(Information_Activity_dynmic.this)) {
            getinformation();
        } else {
            StaticDataHelper.Show_Dialog(Information_Activity_dynmic.this);
        }

        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void getinformation() {

        showpDialog();

        complaintlist = new ArrayList<>();


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<String> call1 = apiService.getinformation();
        call1.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Log.e("HomeActivity", "Response" + response.body());


                if (response != null && response.isSuccessful()) {
                    try {

                        // List<HomeModel> lists = new ArrayList<>();
                        JSONObject obj = new JSONObject(response.body().toString());

                        if (obj.optBoolean("status")) {


                            List<InformationModel> lists = Arrays.asList(MyUIApplication.getInstance().getGson().fromJson(obj.optJSONArray("data").toString(),
                                    InformationModel[].class));

                            //  issuelist.add(district);

                            complaintlist.addAll(lists);

                            Information_Adapter adpter = new Information_Adapter(Information_Activity_dynmic.this, complaintlist);

                            recylerview.setAdapter(adpter);


                        }

                        //  madpter.addAll(lists, false);

                        //adapter_parent = new CustomAdapterParent(HomeActivity.this,list);
                        //recyclerView_parent.setAdapter(adapter_parent);
                        hidepDialog();


                    } catch (JSONException e) {
                        e.printStackTrace();
                        hidepDialog();
                    }
                }


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
}
