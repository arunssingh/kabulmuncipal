package com.rams.kabulmuncipal;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rams.kabulmuncipal.Application.MyUIApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Model.BuildModel;
import Model.District;
import Model.LandType;
import Model.ZoneModel;
import Utils.Logger1;
import Utils.StaticDataHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Safaye_Calculator extends BaseActivity {
Toolbar toolbar;
TextView toolbar_title;
Spinner spinner_dist,spinner_dirt,spinner_land,spinner_build;
ImageView cross,iv_image;
Button bt_final;
    ArrayList<District> distictlist= new ArrayList<>();
    ArrayList<ZoneModel> zonelist= new ArrayList<>();
    ArrayList<LandType> landlist= new ArrayList<>();
    ArrayList<BuildModel> buildlist= new ArrayList<>();
    ProgressDialog pDialog;
    private Boolean spinner_distTouched = false;
    private Boolean spinner_dirtTouched = false;
    private Boolean spinner_landTouched = false;
    private Boolean spinner_buildTouched = false;
    EditText et_land,et_cubic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safaye_calculating);
        toolbar = findViewById(R.id.toolbar);
        toolbar_title=findViewById(R.id.toolbar_title);
        cross=findViewById(R.id.cross);
        iv_image=findViewById(R.id.iv_image);
        bt_final=findViewById(R.id.bt_final);
        spinner_dist=findViewById(R.id.spinner_dist);
        spinner_dirt=findViewById(R.id.spinner_dirt);
        spinner_land=findViewById(R.id.spinner_land);
        spinner_build=findViewById(R.id.spinner_build);
        et_cubic=findViewById(R.id.et_cubic);
        et_land=findViewById(R.id.et_land);
        pDialog = new ProgressDialog(Safaye_Calculator.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        toolbar.setTitle("");
        toolbar_title.setText(""+ Safaye_Calculator.this.getResources().getString(R.string.safayecal));

        spinner_dist.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
             //   System.out.println("Real touch felt.");
                spinner_distTouched = true;
                return false;
            }
        });
        spinner_dirt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //   System.out.println("Real touch felt.");
                spinner_dirtTouched = true;
                return false;
            }
        });
        spinner_land.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //   System.out.println("Real touch felt.");
                spinner_landTouched = true;
                return false;
            }
        });

        spinner_build.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //   System.out.println("Real touch felt.");
                spinner_buildTouched = true;
                return false;
            }
        });

        if (StaticDataHelper.isNetworkConnected(Safaye_Calculator.this)) {
            getdistrict("");
        }
        else
        {
            StaticDataHelper.Show_Dialog(Safaye_Calculator.this);
        }
        ZoneModel district= new ZoneModel();
        district.setId("0");
        district.setCoefficient(0.0);
        district.setDistrictId("0");
        district.setName("Select Zone");
        zonelist.add(district);


        ArrayAdapter<ZoneModel> spinnerAdapter2 = new ArrayAdapter<ZoneModel>(Safaye_Calculator.this, R.layout.activity_ac, zonelist);
        spinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_dirt.setAdapter(spinnerAdapter2);

        LandType landtype= new LandType();
        landtype.setId("0");
        landtype.setRateCoefficient(0.0);
        landtype.setName("Select Land Type");
        landlist.add(landtype);



        ArrayAdapter<LandType> spinnerAdapter3 = new ArrayAdapter<LandType>(Safaye_Calculator.this, R.layout.activity_ac, landlist);
        spinnerAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_land.setAdapter(spinnerAdapter3);

        BuildModel buildmodel= new BuildModel();
        buildmodel.setId("0");
        buildmodel.setDistrict("");
        buildmodel.setLand("");
        buildmodel.setZone("");
        buildmodel.setZone("");
        buildmodel.setName("Select Building Type");
        buildlist.add(buildmodel);



        ArrayAdapter<BuildModel> spinnerAdapter4 = new ArrayAdapter<BuildModel>(Safaye_Calculator.this, R.layout.activity_ac, buildlist);
        spinnerAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_build.setAdapter(spinnerAdapter4);





       spinner_dist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(spinner_distTouched) {
                    if (StaticDataHelper.isNetworkConnected(Safaye_Calculator.this)) {

                        String districtid = "0";
                        try {
                            int indexValue1 = spinner_dist.getSelectedItemPosition();
                            districtid = distictlist.get(indexValue1).getId();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        zonelist= new ArrayList<>();
                        ZoneModel district = new ZoneModel();
                        district.setId("0");
                        district.setCoefficient(0.0);
                        district.setDistrictId("0");
                        district.setName("Select Zone");
                        zonelist.add(district);


                        ArrayAdapter<ZoneModel> spinnerAdapter2 = new ArrayAdapter<ZoneModel>(Safaye_Calculator.this, R.layout.activity_ac, zonelist);
                        spinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_dirt.setAdapter(spinnerAdapter2);

                        landlist=new ArrayList<>();
                        LandType landtype = new LandType();
                        landtype.setId("0");
                        landtype.setRateCoefficient(0.0);
                        landtype.setName("Select Land Type");
                        landlist.add(landtype);


                        ArrayAdapter<LandType> spinnerAdapter3 = new ArrayAdapter<LandType>(Safaye_Calculator.this, R.layout.activity_ac, landlist);
                        spinnerAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_land.setAdapter(spinnerAdapter3);
                        buildlist=new ArrayList<>();
                        BuildModel buildmodel = new BuildModel();
                        buildmodel.setId("0");
                        buildmodel.setDistrict("");
                        buildmodel.setLand("");
                        buildmodel.setZone("");
                        buildmodel.setZone("");
                        buildmodel.setName("Select Building Type");
                        buildlist.add(buildmodel);


                        ArrayAdapter<BuildModel> spinnerAdapter4 = new ArrayAdapter<BuildModel>(Safaye_Calculator.this, R.layout.activity_ac, buildlist);
                        spinnerAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_build.setAdapter(spinnerAdapter4);
                        if (districtid.equalsIgnoreCase("0")) {


                        } else {
                            getzone(distictlist.get(position).getId());
                        }
                    } else {
                        StaticDataHelper.Show_Dialog(Safaye_Calculator.this);
                    }
                }
                spinner_distTouched=false;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_dirt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(spinner_dirtTouched) {
                if (StaticDataHelper.isNetworkConnected(Safaye_Calculator.this)) {

                    String districtid= "0";
                    String zonalid= "0";
                    try {
                        int indexValue2 = spinner_dist.getSelectedItemPosition();
                        districtid = distictlist.get(indexValue2).getId();

                        int indexValue1 = spinner_dirt.getSelectedItemPosition();
                        zonalid = zonelist.get(indexValue1).getId();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    landlist=new ArrayList<>();
                    LandType landtype= new LandType();
                    landtype.setId("0");
                    landtype.setRateCoefficient(0.0);
                    landtype.setName("Select Land Type");
                    landlist.add(landtype);



                    ArrayAdapter<LandType> spinnerAdapter3 = new ArrayAdapter<LandType>(Safaye_Calculator.this, R.layout.activity_ac, landlist);
                    spinnerAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_land.setAdapter(spinnerAdapter3);
                    buildlist=new ArrayList<>();
                    BuildModel buildmodel= new BuildModel();
                    buildmodel.setId("0");
                    buildmodel.setDistrict("");
                    buildmodel.setLand("");
                    buildmodel.setZone("");
                    buildmodel.setZone("");
                    buildmodel.setName("Select Building Type");
                    buildlist.add(buildmodel);



                    ArrayAdapter<BuildModel> spinnerAdapter4 = new ArrayAdapter<BuildModel>(Safaye_Calculator.this, R.layout.activity_ac, buildlist);
                    spinnerAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_build.setAdapter(spinnerAdapter4);

                    if(zonalid.equalsIgnoreCase("0")) {



                    }
                    else {

                        getland(districtid, zonelist.get(position).getId());
                    }

                }
                else
                {
                    StaticDataHelper.Show_Dialog(Safaye_Calculator.this);
                }
                }
                spinner_dirtTouched=false;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinner_land.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spinner_landTouched) {
                    if (StaticDataHelper.isNetworkConnected(Safaye_Calculator.this)) {

                        int indexValue1 = spinner_dist.getSelectedItemPosition();
                        int indexValue2 = spinner_dirt.getSelectedItemPosition();
                        String districtid = "0";
                        String zoneid = "0";
                        try {
                            districtid = distictlist.get(indexValue1).getId();
                            zoneid = zonelist.get(indexValue2).getId();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (zoneid.equalsIgnoreCase("0")) {
                            buildlist=new ArrayList<>();
                            BuildModel buildmodel1= new BuildModel();
                            buildmodel1.setId("0");
                            buildmodel1.setDistrict("");
                            buildmodel1.setLand("");
                            buildmodel1.setZone("");
                            buildmodel1.setName("Select Building Type");
                            buildlist.add(buildmodel1);



                            ArrayAdapter<BuildModel> spinnerAdapter5 = new ArrayAdapter<BuildModel>(Safaye_Calculator.this, R.layout.activity_ac, buildlist);
                            spinnerAdapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner_build.setAdapter(spinnerAdapter5);
                        } else {

                            try {
                                getbuild(districtid, zoneid, landlist.get(position).getId());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        StaticDataHelper.Show_Dialog(Safaye_Calculator.this);
                    }
                }
                spinner_landTouched=false;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        bt_final.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(spinner_dist.getSelectedItem().toString().equalsIgnoreCase("Select Disctict"))
                {
                    Toast.makeText(Safaye_Calculator.this, "Select Disctict", Toast.LENGTH_SHORT).show();
                }
                else if(spinner_dirt.getSelectedItem().toString().equalsIgnoreCase("Select Zone"))
                {
                    Toast.makeText(Safaye_Calculator.this, "Select Zone", Toast.LENGTH_SHORT).show();
                }

                else if(spinner_land.getSelectedItem().toString().equalsIgnoreCase("Select Land Type"))
                {
                    Toast.makeText(Safaye_Calculator.this, "Select Land Type", Toast.LENGTH_SHORT).show();
                }
                else if(spinner_build.getSelectedItem().toString().equalsIgnoreCase("Select Building Type"))
                {
                    Toast.makeText(Safaye_Calculator.this, "Select Building Type", Toast.LENGTH_SHORT).show();
                }
                else if(et_cubic.getText().toString().trim().equalsIgnoreCase(""))
                {
                    Toast.makeText(Safaye_Calculator.this, "Please Enter Value of Cubic Meter", Toast.LENGTH_SHORT).show();
                    // Staticutils.showToast(Registration_new.this,"Please enter Business/company name");
                    et_cubic.requestFocus();
                }
                else if(et_land.getText().toString().trim().equalsIgnoreCase(""))
                {
                    Toast.makeText(Safaye_Calculator.this, "Please Enter Land Area", Toast.LENGTH_SHORT).show();
                    // Staticutils.showToast(Registration_new.this,"Please enter Business/company name");
                    et_land.requestFocus();
                }
                else {

                    try {
                        int indexValue1 = spinner_dirt.getSelectedItemPosition();
                        double coefficent = zonelist.get(indexValue1).getCoefficient();

                        int indexValue2 = spinner_land.getSelectedItemPosition();
                        double rateCoefficient = landlist.get(indexValue2).getRateCoefficient();
                        double landcoefs= landlist.get(indexValue2).getCoefficient();

                        int indexValue3 = spinner_build.getSelectedItemPosition();
                        double price = buildlist.get(indexValue3).getPrice();

                        double cubic_value = Double.valueOf(et_cubic.getText().toString().trim());
                        double land_value = Double.valueOf(et_land.getText().toString().trim());
                        //Building Coefficient * Building Category Price * Value of Cubic Meter * 1.1 = Total Building Price
                        double totalbuildprice=coefficent*price*cubic_value*1.1;
                        // Land Coefficient * Land Area = Total Land Price
                        Logger1.e("totalbuildprice","totalbuildprice="+totalbuildprice);

                        double landprice=landcoefs*land_value;

                        Logger1.e("landprice","landprice="+landprice);
                        //Total Building Price + Total Land Price * Safay Calculator = Safaye Calculation

                        double safayecalc=totalbuildprice+landprice*rateCoefficient;


                        ShowTHankDialog(safayecalc);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }



                //
            }
        });


        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Safaye_Calculator.this, Safaye_Calculator_Search.class);
                startActivity(i);

            }
        });
    }

    public void ShowTHankDialog(double safayecalc)
    {
        final Dialog dialog = new Dialog(Safaye_Calculator.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dailogsafayecalculator);
        dialog.setCancelable(false);
        TextView tv_result = (TextView) dialog.findViewById(R.id.tv_result);
        ImageView iv_cross=(ImageView) dialog.findViewById(R.id.iv_cross);

        tv_result.setText(""+safayecalc);
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

    //

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
                            final District district= new District();
                            district.setId("0");
                            district.setName("Select Disctict");
                            distictlist.add(district);


                            distictlist.addAll(lists);

                            ArrayAdapter<District> spinnerAdapter = new ArrayAdapter<District>(Safaye_Calculator.this, R.layout.activity_ac, distictlist);
                            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner_dist.setAdapter(spinnerAdapter);


                            //
                            LandType landtype= new LandType();
                            landtype.setId("0");
                            landtype.setRateCoefficient(0.0);
                            landtype.setName("Select Land Type");
                            landlist.add(landtype);



                            ArrayAdapter<LandType> spinnerAdapter3 = new ArrayAdapter<LandType>(Safaye_Calculator.this, R.layout.activity_ac, landlist);
                            spinnerAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner_land.setAdapter(spinnerAdapter3);

                            BuildModel buildmodel= new BuildModel();
                            buildmodel.setId("0");
                            buildmodel.setDistrict("");
                            buildmodel.setLand("");
                            buildmodel.setZone("");
                            buildmodel.setName("Select Building Type");
                            buildlist.add(buildmodel);



                            ArrayAdapter<BuildModel> spinnerAdapter4 = new ArrayAdapter<BuildModel>(Safaye_Calculator.this, R.layout.activity_ac, buildlist);
                            spinnerAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner_build.setAdapter(spinnerAdapter4);
                            //


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


    private void getzone(String post_id) {

        showpDialog();

        zonelist= new ArrayList<>();


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<String> call1 = apiService.getzonewise(post_id);
        call1.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Log.e("HomeActivity", "Response" + response.body());


                if (response != null && response.isSuccessful()) {
                    try {

                        // List<HomeModel> lists = new ArrayList<>();
                        JSONObject obj = new JSONObject(response.body().toString());

                        if(obj.optBoolean("status")) {


                            List<ZoneModel> lists = Arrays.asList(MyUIApplication.getInstance().getGson().fromJson(obj.optJSONArray("data").toString(),
                                    ZoneModel[].class));
                            ZoneModel district= new ZoneModel();
                            district.setId("0");
                            district.setCoefficient(0.0);
                            district.setDistrictId("0");
                            district.setName("Select Zone");
                            zonelist.add(district);


                            zonelist.addAll(lists);

                            ArrayAdapter<ZoneModel> spinnerAdapter2 = new ArrayAdapter<ZoneModel>(Safaye_Calculator.this, R.layout.activity_ac, zonelist);
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
    private void getland(String dist_id,String zone_id) {

        showpDialog();

        landlist= new ArrayList<>();


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<String> call1 = apiService.getlandwise(dist_id,zone_id);
        call1.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Log.e("HomeActivity", "Response" + response.body());


                if (response != null && response.isSuccessful()) {
                    try {

                        // List<HomeModel> lists = new ArrayList<>();
                        JSONObject obj = new JSONObject(response.body().toString());

                        if(obj.optBoolean("status")) {


                            List<LandType> lists = Arrays.asList(MyUIApplication.getInstance().getGson().fromJson(obj.optJSONArray("data").toString(),
                                    LandType[].class));

                            LandType landtype= new LandType();
                            landtype.setId("0");
                            landtype.setRateCoefficient(0.0);
                            landtype.setName("Select Land Type");
                            landlist.add(landtype);

                            landlist.addAll(lists);

                            ArrayAdapter<LandType> spinnerAdapter3 = new ArrayAdapter<LandType>(Safaye_Calculator.this, R.layout.activity_ac, landlist);
                            spinnerAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner_land.setAdapter(spinnerAdapter3);
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

    private void getbuild(final String direct_id, String zone_id, String land_id) {

        showpDialog();

        buildlist= new ArrayList<>();


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<String> call1 = apiService.getbuildwise(direct_id,zone_id,land_id);
        call1.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Log.e("HomeActivity", "Response" + response.body());


                if (response != null && response.isSuccessful()) {
                    try {

                        // List<HomeModel> lists = new ArrayList<>();
                        JSONObject obj = new JSONObject(response.body().toString());

                        if(obj.optBoolean("status")) {


                            List<BuildModel> lists = Arrays.asList(MyUIApplication.getInstance().getGson().fromJson(obj.optJSONArray("data").toString(),
                                    BuildModel[].class));

                            BuildModel district= new BuildModel();
                            district.setId("0");
                            district.setDistrict("");
                            district.setLand("");
                            district.setZone("");
                            district.setZone("");
                            district.setName("Select Building Type");
                            buildlist.add(district);

                            buildlist.addAll(lists);

                            ArrayAdapter<BuildModel> spinnerAdapter4 = new ArrayAdapter<BuildModel>(Safaye_Calculator.this, R.layout.activity_ac, buildlist);
                            spinnerAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner_build.setAdapter(spinnerAdapter4);
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
