package com.rams.kabulmuncipal;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.Toast;

import com.rams.kabulmuncipal.Application.MyUIApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import Adapter.Complaint_Adapter;
import Adapter.Develop_Adapter_Child;
import Model.Develop_Model.ModelDevelopment;
import Model.Model_Complaint.ModelComplaint;
import Utils.Logger1;
import Utils.MarshMallowPermission;
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
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 100;
    public static final String ALLOW_KEY = "ALLOWED";
    public static final String CAMERA_PREF = "camera_pref";
    public static void setOutput_file(String output_file) {
        About_us.output_file = output_file;
    }
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
            final TextView tv_detail = (TextView) not_view.findViewById(R.id.tv_detail);

            final ImageView clicknext=not_view.findViewById(R.id.clicknext);
            final RelativeLayout rlwebview=not_view.findViewById(R.id.rlwebview);


          //  final RecyclerView recyclerViewsub = (RecyclerView) not_view.findViewById(R.id.recyclerViewsub);
           // recyclerViewsub.setHasFixedSize(false);
            //
            final WebView webview = (WebView) not_view.findViewById(R.id.webview);
            main_lay.setTag(i + "");
            open.setTag(i + "");
            hide.setTag(i + "");
            rlwebview.setTag(i+"");
            webview.setTag(i+"");
            tv_detail.setTag(i+"");


            final int finalI1 = i;
            tv_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    MarshMallowPermission permission = new MarshMallowPermission(Devlopment_Plan_dynamic.this);

                    String[] permessionss = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

                    if (permission.checkPermissionForREAD_EXTERNAL_STORAGE()) {

                        File f = new File(StaticDataHelper.GetFileName(Devlopment_Plan_dynamic.this, developlist.get(finalI1).getFile()));

                        if (f.exists()) {

                            openFile(f);

                        } else {


                            new DownloadFile().execute(developlist.get(finalI1).getFile());

                        }



                    } else {

                        Logger1.e("else","else");
                        permission.requestPermission(permessionss);
                    }





                }
            });


          /*  recyclerViewsub.setLayoutManager(new LinearLayoutManager(Devlopment_Plan_dynamic.this,LinearLayoutManager.VERTICAL,false));
            Logger1.e("shekhawatsize","shekhawatsize"+i+"size="+developlist.get(i).getChildrens().size());
            Develop_Adapter_Child adapter = new Develop_Adapter_Child(Devlopment_Plan_dynamic.this, developlist.get(i).getChildrens());
            recyclerViewsub.setAdapter(adapter);*/
            // recyclerViewsub.setHasFixedSize(false);
            tv_name.setText(developlist.get(i).getPlanName());


            //arun


            final int finalI = i;
            main_lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    //
                    if(developlist.get(finalI).getChildrens().size()==0)
                    {
                    /*    Intent is = new Intent(Devlopment_Plan_dynamic.this, About_us.class);
                        is.putExtra("name",developlist.get(finalI).getPlanName());
                        is.putExtra("desc",developlist.get(finalI).getDescription());
                        is.putExtra("file",developlist.get(finalI).getFile());
                        startActivity(is);*/
                    //
                        if(developlist.get(finalI).getFile().isEmpty())
                        {
                            tv_detail.setVisibility(View.GONE);
                        }
                        webview.getSettings().setAllowContentAccess(true);
                        webview.getSettings().setJavaScriptEnabled(true);
                        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

                        webview.getSettings().setDomStorageEnabled(true);


                        webview.setHapticFeedbackEnabled(false);


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
                            str = str.replace("Welcome to CustomFont Demo", developlist.get(finalI).getDescription());

                            webview.loadDataWithBaseURL(null,str,"text/html","utf-8",null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //arun


                        if (rlwebview.getVisibility() == View.VISIBLE) {
                            rlwebview.setVisibility(View.GONE);
                            open.setVisibility(View.GONE);
                            hide.setVisibility(View.VISIBLE);
                        } else {
                            rlwebview.setVisibility(View.VISIBLE);
                            open.setVisibility(View.VISIBLE);
                            hide.setVisibility(View.GONE);
                        }

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
    //arun
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MarshMallowPermission.WRITE_other: {
                for (int i = 0, len = permissions.length; i < len; i++) {
                    String permission = permissions[i];

                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        boolean
                                showRationale =
                                ActivityCompat.shouldShowRequestPermissionRationale(
                                        this, permission);

                        if (showRationale) {
                            showAlert();
                        } else if (!showRationale) {
                            // user denied flagging NEVER ASK AGAIN
                            // you can either enable some fall back,
                            // disable features of your app
                            // or open another dialog explaining
                            // again the permission and directing to
                            // the app setting
                            saveToPreferences(Devlopment_Plan_dynamic.this, ALLOW_KEY, true);
                        }
                    }
                }
            }
        }
    }

    //
    public static void saveToPreferences(Context context, String key, Boolean allowed) {
        SharedPreferences myPrefs = context.getSharedPreferences(CAMERA_PREF,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = myPrefs.edit();
        prefsEditor.putBoolean(key, allowed);
        prefsEditor.commit();
    }

    private void showAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(Devlopment_Plan_dynamic.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("App needs to access the Camera.");

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "DONT ALLOW",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ALLOW",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        ActivityCompat.requestPermissions(Devlopment_Plan_dynamic.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                MY_PERMISSIONS_REQUEST_CAMERA);
                    }
                });
        alertDialog.show();
    }

    //
    String outputFile1 = "";


    private class DownloadFile extends AsyncTask<String, String, String> {

        private ProgressDialog progressDialog;
        private String fileName;
        private String folder;
        private boolean isDownloaded;

        /**
         * Before starting background thread
         * Show Progress Bar Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = new ProgressDialog(Devlopment_Plan_dynamic.this);
            this.progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            this.progressDialog.setCancelable(false);
            this.progressDialog.show();
        }

        /**
         * Downloading file in background thread
         */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection connection = url.openConnection();
                connection.connect();
                // getting file length
                int lengthOfFile = connection.getContentLength();


                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

                //Extract file name from URL
                fileName = f_url[0].substring(f_url[0].lastIndexOf('/') , f_url[0].length());

                //Append timestamp to file name
                //  fileName = timestamp + "_" + fileName;

                //External directory path to save file
                //  folder = Environment.getExternalStorageDirectory() + File.separator + "androiddeft/";

                folder = Environment.getExternalStorageDirectory() + File.separator + "kabul/Doc/";

                //Create androiddeft folder if it does not exist
                File directory = new File(folder);

                if (!directory.exists()) {
                    directory.mkdirs();
                }
                outputFile1 = directory.getAbsolutePath()+"/" + fileName;
                About_us.setOutput_file(outputFile1);
                // Output stream to write file
                OutputStream output = new FileOutputStream(folder + fileName);

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lengthOfFile));
                    Log.d("Aboutus", "Progress: " + (int) ((total * 100) / lengthOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();
                return folder + fileName;

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return "Something went wrong";
        }

        /**
         * Updating progress bar
         */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            progressDialog.setProgress(Integer.parseInt(progress[0]));
        }


        @Override
        protected void onPostExecute(String result) {
            // dismiss the dialog after the file was downloaded
            this.progressDialog.dismiss();


            Logger1.e("results","result="+result);


            openFile(new File(outputFile1));


            // Display File path after downloading
            //  Toast.makeText(getApplicationContext(),
            //      result, Toast.LENGTH_LONG).show();
        }
    }


    //

    private void openFile(File url) {

        try {

            // Uri  uri = Uri.fromFile(url);


            Uri uri = FileProvider.getUriForFile(Devlopment_Plan_dynamic.this, BuildConfig.APPLICATION_ID,url);
            Logger1.e("uri","uri="+uri);


            Intent intent = new Intent(Intent.ACTION_VIEW);
            if (url.toString().contains(".doc") || url.toString().contains(".docx")) {
                // Word document
                intent.setDataAndType(uri, "application/msword");
            } else if (url.toString().contains(".pdf")) {
                // PDF file
                intent.setDataAndType(uri, "application/pdf");
            } else if (url.toString().contains(".ppt") || url.toString().contains(".pptx")) {
                // Powerpoint file
                intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
            } else if (url.toString().contains(".xls") || url.toString().contains(".xlsx")) {
                // Excel file
                intent.setDataAndType(uri, "application/vnd.ms-excel");
            } else if (url.toString().contains(".zip")) {
                // ZIP file
                intent.setDataAndType(uri, "application/zip");
            } else if (url.toString().contains(".rar")){
                // RAR file
                intent.setDataAndType(uri, "application/x-rar-compressed");
            } else if (url.toString().contains(".rtf")) {
                // RTF file
                intent.setDataAndType(uri, "application/rtf");
            } else if (url.toString().contains(".wav") || url.toString().contains(".mp3")) {
                // WAV audio file
                intent.setDataAndType(uri, "audio/x-wav");
            } else if (url.toString().contains(".gif")) {
                // GIF file
                intent.setDataAndType(uri, "image/gif");
            } else if (url.toString().contains(".jpg") || url.toString().contains(".jpeg") || url.toString().contains(".png")) {
                // JPG file
                intent.setDataAndType(uri, "image/jpeg");
            } else if (url.toString().contains(".txt")) {
                // Text file
                intent.setDataAndType(uri, "text/plain");
            } else if (url.toString().contains(".3gp") || url.toString().contains(".mpg") ||
                    url.toString().contains(".mpeg") || url.toString().contains(".mpe") || url.toString().contains(".mp4") || url.toString().contains(".avi")) {
                // Video files
                intent.setDataAndType(uri, "video/*");
            } else {
                intent.setDataAndType(uri, "*/*");
            }

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(Devlopment_Plan_dynamic.this, "No application found which can open the file", Toast.LENGTH_SHORT).show();
        }
    }

    //
}
