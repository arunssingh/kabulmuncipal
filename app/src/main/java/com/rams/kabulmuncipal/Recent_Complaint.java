package com.rams.kabulmuncipal;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rams.kabulmuncipal.Application.MyUIApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Adapter.Complaint_Adapter;
import Adapter.News_Adapter;
import Model.District;
import Model.Model_Complaint.ModelComplaint;
import Utils.StaticDataHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Recent_Complaint extends BaseActivity {
Toolbar toolbar;
TextView toolbar_title;
ImageView cross;
RecyclerView recylerview;
LinearLayoutManager layoutManager;
ArrayList<ModelComplaint> complaintlist= new ArrayList<>();
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent__complaint);
        toolbar = findViewById(R.id.toolbar);
        toolbar_title=findViewById(R.id.toolbar_title);
        cross=findViewById(R.id.cross);
        recylerview=findViewById(R.id.recylerview);
        pDialog = new ProgressDialog(Recent_Complaint.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        layoutManager= new LinearLayoutManager(Recent_Complaint.this);
        recylerview.setLayoutManager(layoutManager);
        toolbar.setTitle("");
        toolbar_title.setText(""+Recent_Complaint.this.getResources().getString(R.string.complaint_recent));

        if (StaticDataHelper.isNetworkConnected(Recent_Complaint.this)) {
            getcompaint();
        }
        else
        {
            StaticDataHelper.Show_Dialog(Recent_Complaint.this);
        }


        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    //
    private void getcompaint() {

        showpDialog();

        complaintlist= new ArrayList<>();


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<String> call1 = apiService.getcomplaint();
        call1.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Log.e("HomeActivity", "Response" + response.body());


                if (response != null && response.isSuccessful()) {
                    try {

                        // List<HomeModel> lists = new ArrayList<>();
                        JSONObject obj = new JSONObject(response.body().toString());

                        if(obj.optBoolean("status")) {


                            List<ModelComplaint> lists = Arrays.asList(MyUIApplication.getInstance().getGson().fromJson(obj.optJSONArray("data").toString(),
                                    ModelComplaint[].class));

                          //  issuelist.add(district);

                            complaintlist.addAll(lists);

                            Complaint_Adapter adpter= new Complaint_Adapter(Recent_Complaint.this,complaintlist);

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
