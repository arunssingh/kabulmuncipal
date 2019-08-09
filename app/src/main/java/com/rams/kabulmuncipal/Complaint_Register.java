package com.rams.kabulmuncipal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rams.kabulmuncipal.Application.MyUIApplication;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Model.District;
import Utils.Logger1;
import Utils.StaticDataHelper;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Complaint_Register extends BaseActivity {

    Toolbar toolbar;
    ImageView cross;
    TextView toolbar_title;

    Spinner spinner_dist,spinner_dirt,spinner_issue;
    EditText et_firstname,et_lastname,et_phonenumber,et_email,et_desination,et_remark;
    AppCompatButton bt_con,bt_upload,bt_final;
    ScrollView ll_1,ll_2;
    RadioGroup rg1;
    String sr="";
    File newFile;
    Uri fileuri;
    String picture_main = null;
    ProgressDialog pDialog;
    MultipartBody.Part imagenPerfil = null;
    ArrayList<District> distictlist= new ArrayList<>();
    ArrayList<District> directorlist= new ArrayList<>();
    ArrayList<District> issuelist= new ArrayList<>();

    String distid,directorid,issueid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint__register);
        toolbar = findViewById(R.id.toolbar);
        toolbar_title=findViewById(R.id.toolbar_title);
        toolbar.setTitle("");
        toolbar_title.setText(""+Complaint_Register.this.getResources().getString(R.string.complaint));
        cross=findViewById(R.id.cross);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        pDialog = new ProgressDialog(Complaint_Register.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        spinner_dist=findViewById(R.id.spinner_dist);
        spinner_dirt=findViewById(R.id.spinner_dirt);
        spinner_issue=findViewById(R.id.spinner_issue);
        et_firstname=findViewById(R.id.et_firstname);
        et_lastname=findViewById(R.id.et_lastname);
        et_phonenumber=findViewById(R.id.et_phonenumber);
        et_email=findViewById(R.id.et_email);
        et_desination=findViewById(R.id.et_desination);
        et_remark=findViewById(R.id.et_remark);
        bt_con=findViewById(R.id.bt_con);
        bt_upload=findViewById(R.id.bt_upload);
        bt_final=findViewById(R.id.bt_final);
        ll_1=findViewById(R.id.ll_1);
        ll_2=findViewById(R.id.ll_2);
        rg1=findViewById(R.id.rg1);



       /* ArrayAdapter<District> spinnerAdapter2 = new ArrayAdapter<District>(Complaint_Register.this, R.layout.activity_ac, directorlist);
        spinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_dirt.setAdapter(spinnerAdapter2);*/


        bt_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(Complaint_Register.this);
            }
        });

        if (StaticDataHelper.isNetworkConnected(Complaint_Register.this)) {
            getdistrict("");
        }
        else
        {
            StaticDataHelper.Show_Dialog(Complaint_Register.this);
        }

        if (StaticDataHelper.isNetworkConnected(Complaint_Register.this)) {
            getdirect("");
        }
        else
        {
            StaticDataHelper.Show_Dialog(Complaint_Register.this);
        }


        bt_con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(et_firstname.getText().toString().trim().equals("") || et_firstname.getText().toString().trim().equals(null))
                {
                    Toast.makeText(Complaint_Register.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
                   // Staticutils.showToast(Registration_new.this,"Please enter Business/company name");
                    et_firstname.requestFocus();
                }

                else
                {
                    ll_1.setVisibility(View.GONE);
                    ll_2.setVisibility(View.VISIBLE);


                }

            }
        });

        District district= new District();
        district.setId("0");
        district.setName("Select Issues");
        issuelist.add(district);

        ArrayAdapter<District> spinnerAdapter3 = new ArrayAdapter<District>(Complaint_Register.this, R.layout.activity_ac, issuelist);
        spinnerAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_issue.setAdapter(spinnerAdapter3);

        spinner_dirt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (StaticDataHelper.isNetworkConnected(Complaint_Register.this)) {
                    getissue(directorlist.get(position).getId());
                }
                else
                {
                    StaticDataHelper.Show_Dialog(Complaint_Register.this);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bt_final.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(et_firstname.getText().toString().trim().equals("") || et_firstname.getText().toString().trim().equals(null))
                {
                    Toast.makeText(Complaint_Register.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
                    // Staticutils.showToast(Registration_new.this,"Please enter Business/company name");
                    et_firstname.requestFocus();
                }
               else if(spinner_dist.getSelectedItem().toString().equalsIgnoreCase("Select District"))
                {
                    Toast.makeText(Complaint_Register.this, "Select District", Toast.LENGTH_SHORT).show();
                }

                else if(spinner_dirt.getSelectedItem().toString().equalsIgnoreCase("Select Directortiat"))
                {
                    Toast.makeText(Complaint_Register.this, "Select Directortiat", Toast.LENGTH_SHORT).show();
                }
                else if(spinner_issue.getSelectedItem().toString().equalsIgnoreCase("Select Issues"))
                {
                    Toast.makeText(Complaint_Register.this, "Select Issues", Toast.LENGTH_SHORT).show();
                }
                else  if(et_remark.getText().toString().trim().equals("") || et_remark.getText().toString().trim().equals(null))
                {
                    Toast.makeText(Complaint_Register.this, "Please Enter Remarks", Toast.LENGTH_SHORT).show();
                    // Staticutils.showToast(Registration_new.this,"Please enter Business/company name");
                    et_remark.requestFocus();
                }
                else {

                    int indexValue = spinner_dist.getSelectedItemPosition();
                    int indexValue1 = spinner_dirt.getSelectedItemPosition();
                    int indexValue2 = spinner_issue.getSelectedItemPosition();

                    distid = distictlist.get(indexValue).getId();
                    Logger1.e("shekhawat", "shekhawat=" + distid);

                    directorid = directorlist.get(indexValue1).getId();
                    issueid = issuelist.get(indexValue2).getId();
                    if (StaticDataHelper.isNetworkConnected(Complaint_Register.this)) {
                        UpdateComplaint_data();
                    }
                    else
                    {
                        StaticDataHelper.Show_Dialog(Complaint_Register.this);
                    }

                }


            }
        });

        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (reqCode) {

                case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE:
                    Logger1.e("call", "call1");


                    CropImage.ActivityResult result = CropImage.getActivityResult(data);



                    Logger1.e("call", "Cropping successful call" + result.getUri());


                    newFile = new File(result.getUri().getPath());

                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 8;
                    Log.e("arunin1", "arun0camerain1" + newFile.getPath());
                    picture_main = newFile.getPath();
                    Bitmap selectedImage = BitmapFactory.decodeFile(picture_main, options);
                    break;



            }

        }

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked

        switch (view.getId()) {
            case R.id.radiomale:
                if (checked)
                    sr = "male";
                break;
            case R.id.radiofemale:
                if (checked)
                    sr = "female";
                break;
            case R.id.radionotsay:
                if (checked)
                    sr = "Prefer not to say";
                break;
        }
    }

    //
    public void UpdateComplaint_data() {



        showpDialog();
        RequestBody fname =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), et_firstname.getText().toString());


        RequestBody phone =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), et_phonenumber.getText().toString());
        RequestBody email =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), et_email.getText().toString());


        RequestBody gender =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), sr);
        RequestBody district =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), distid);
        RequestBody directorate =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), directorid);
        RequestBody issues =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), issueid);
        RequestBody remarks =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), et_remark.getText().toString());


            if (picture_main != null) {
                File file = new File(picture_main);
                Log.i("Register", "Nombre del archivo " + file.getName());
                // create RequestBody instance from file
                RequestBody requestFile =
                        RequestBody.create(MediaType.parse("image/jpeg"), file);
                // MultipartBody.Part is used to send also the actual file name
                imagenPerfil = MultipartBody.Part.createFormData("attachment", file.getName(), requestFile);
            }
            else {
                imagenPerfil=null;
            }

                ApiClient.getClient().create(ApiInterface.class).postcomlaint(fname,
                        phone,
                        email,
                        gender,
                        district,
                        directorate,
                        issues,
                        remarks,
                        imagenPerfil



                ).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                        Logger1.e("onresponce", "" + response.body());
                        hidepDialog();
                        if (response.isSuccessful()) {
                            try {
                                JSONObject obj = new JSONObject(response.body());
                                //String message = obj.get("message").toString();

                                //Toast.makeText(Login.this, obj.get("message")+"", Toast.LENGTH_SHORT).show();

                                if (obj.optBoolean("status")) {


                                    StaticDataHelper.showtoast(Complaint_Register.this,obj.optString("message")+"");
                         /*   String storeuser=jsonobj.optJSONObject("store").toString();
                            storemodel = MyUIApplication.getInstance().getGson().fromJson(storeuser, Store.class);*/


                                    finish();


                                } else {
                                     StaticDataHelper.showtoast(Complaint_Register.this,obj.optString("message")+"");
                                }


                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                                hidepDialog();
                            } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                                hidepDialog();
                            }


                        }
                    }




                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Logger1.e("onfailure", "" + t.getMessage());

                        hidepDialog();

                        // StaticDataHelper.showtoast(My_profile.this, "" + t.getMessage());
                    }
                });

        }
        // add another part within the multipart request


    private void getdistrict(String post_id) {

        showpDialog();

        distictlist = new ArrayList<>();


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<String> call1 = apiService.getdistricts();
        call1.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Log.e("HomeActivity", "Response" + response.body());


                if (response != null && response.isSuccessful()) {
                    try {

                        // List<HomeModel> lists = new ArrayList<>();
                        JSONObject obj = new JSONObject(response.body().toString());

                        if(obj.optBoolean("status")) {


                            List<District> lists = Arrays.asList(MyUIApplication.getInstance().getGson().fromJson(obj.optJSONArray("data").toString(),
                                    District[].class));
                            District district= new District();
                            district.setId("0");
                            district.setName("Select District");
                            distictlist.add(district);


                            distictlist.addAll(lists);

                            ArrayAdapter<District> spinnerAdapter = new ArrayAdapter<District>(Complaint_Register.this, R.layout.activity_ac, distictlist);
                            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner_dist.setAdapter(spinnerAdapter);
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


    private void getdirect(String post_id) {

        showpDialog();

        directorlist= new ArrayList<>();


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<String> call1 = apiService.getdirct();
        call1.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Log.e("HomeActivity", "Response" + response.body());


                if (response != null && response.isSuccessful()) {
                    try {

                        // List<HomeModel> lists = new ArrayList<>();
                        JSONObject obj = new JSONObject(response.body().toString());

                        if(obj.optBoolean("status")) {


                            List<District> lists = Arrays.asList(MyUIApplication.getInstance().getGson().fromJson(obj.optJSONArray("data").toString(),
                                    District[].class));
                            District district= new District();
                            district.setId("0");
                            district.setName("Select Directortiat");
                            directorlist.add(district);


                            directorlist.addAll(lists);

                            ArrayAdapter<District> spinnerAdapter2 = new ArrayAdapter<District>(Complaint_Register.this, R.layout.activity_ac, directorlist);
                            spinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner_dirt.setAdapter(spinnerAdapter2);
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
    private void getissue(String direct_id) {

        showpDialog();

        issuelist= new ArrayList<>();


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<String> call1 = apiService.getissue(direct_id);
        call1.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Log.e("HomeActivity", "Response" + response.body());


                if (response != null && response.isSuccessful()) {
                    try {

                        // List<HomeModel> lists = new ArrayList<>();
                        JSONObject obj = new JSONObject(response.body().toString());

                        if(obj.optBoolean("status")) {


                            List<District> lists = Arrays.asList(MyUIApplication.getInstance().getGson().fromJson(obj.optJSONArray("data").toString(),
                                    District[].class));

                            District district= new District();
                            district.setId("0");
                            district.setName("Select Issues");
                            issuelist.add(district);

                            issuelist.addAll(lists);

                            ArrayAdapter<District> spinnerAdapter3 = new ArrayAdapter<District>(Complaint_Register.this, R.layout.activity_ac, issuelist);
                            spinnerAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner_issue.setAdapter(spinnerAdapter3);
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

    //
}
