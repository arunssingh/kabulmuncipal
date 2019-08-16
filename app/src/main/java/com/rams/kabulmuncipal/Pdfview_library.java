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
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import Utils.Logger1;
import Utils.MarshMallowPermission;
import Utils.StaticDataHelper;
import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;

public class Pdfview_library extends BaseActivity implements DownloadFile.Listener {
WebView webview;
ProgressDialog pDialog;
    TextView toolbar_title,click,tv_detail;
    Toolbar toolbar;
    ImageView cross;
    String url;
    String name;
    String desc;
    PDFPagerAdapter adapter;
    RemotePDFViewPager remotePDFViewPager;
    public static String output_file = "";
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 100;
    public static final String ALLOW_KEY = "ALLOWED";
    public static final String CAMERA_PREF = "camera_pref";
    public static void setOutput_file(String output_file) {
        Pdfview_library.output_file = output_file;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us_library);

        toolbar_title=findViewById(R.id.toolbar_title);
        cross=findViewById(R.id.cross);
        tv_detail=findViewById(R.id.tv_detail);
        toolbar = findViewById(R.id.toolbar);


       pDialog = new ProgressDialog(Pdfview_library.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);


       // final TransparentProgressDialog Dialog = new TransparentProgressDialog(getActivity(), R.mipmap.loader, "Please wait..");
      //  webview.loadUrl("https://marvelapp.com/c50i33e");

        Bundle extras = getIntent().getExtras();
        name=extras.getString("name");
        desc=extras.getString("desc");
        url=extras.getString("file");
        Logger1.e("url","urlfile="+url);
        //toolbar_title.setText(""+name);
        toolbar_title.setText(""+name);
        if(url.isEmpty())
        {
            //tv_detail.setVisibility(View.GONE);
        }
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        remotePDFViewPager =
                new RemotePDFViewPager(Pdfview_library.this, url, this);

    }

    private void showpDialog() {
        if (pDialog != null && !pDialog.isShowing()) {
            pDialog.show();
        }
    }

    private void hidepDialog() {
        if (pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }
//
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
                        saveToPreferences(Pdfview_library.this, ALLOW_KEY, true);
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
        AlertDialog alertDialog = new AlertDialog.Builder(Pdfview_library.this).create();
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
                        ActivityCompat.requestPermissions(Pdfview_library.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                MY_PERMISSIONS_REQUEST_CAMERA);
                    }
                });
        alertDialog.show();
    }

    //
    String outputFile1 = "";

    @Override
    public void onSuccess(String url, String destinationPath) {
        //adapter = new PDFPagerAdapter(this, destinationPath);
       // remotePDFViewPager.setAdapter(adapter);
       // setContentView(remotePDFViewPager);
        Logger1.e("sucess","sucess"+destinationPath);
        setContentView(remotePDFViewPager);

    }

    @Override
    public void onFailure(Exception e) {
        Logger1.e("failure","failure");
    }

    @Override
    public void onProgressUpdate(int progress, int total) {

    }


    //
    @Override
    protected void onDestroy() {
        super.onDestroy();

        adapter.close();
    }

}
