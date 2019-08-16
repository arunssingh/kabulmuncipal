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
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;

import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import Utils.Logger1;
import Utils.MarshMallowPermission;
import Utils.StaticDataHelper;

public class About_us extends BaseActivity {
WebView webview;
ProgressDialog pDialog;
    TextView toolbar_title,click,tv_detail;
    Toolbar toolbar;
    ImageView cross;
    String url;
    String name;
    String desc;
    public static String output_file = "";
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 100;
    public static final String ALLOW_KEY = "ALLOWED";
    public static final String CAMERA_PREF = "camera_pref";
    public static void setOutput_file(String output_file) {
        About_us.output_file = output_file;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        webview=(WebView)findViewById(R.id.webview);
        toolbar_title=findViewById(R.id.toolbar_title);
        cross=findViewById(R.id.cross);
        tv_detail=findViewById(R.id.tv_detail);
        toolbar = findViewById(R.id.toolbar);
        WebSettings webSettings = webview.getSettings();

       pDialog = new ProgressDialog(About_us.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        tv_detail.setPaintFlags(tv_detail.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        //
        webSettings.setAllowContentAccess(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        webview.getSettings().setDomStorageEnabled(true);

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
            tv_detail.setVisibility(View.GONE);
        }
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        webview.setHapticFeedbackEnabled(false);


        InputStream is = null;
        try {
            is = getAssets().open("demo.html");
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
            str = str.replace("Welcome to CustomFont Demo", desc);

            webview.loadDataWithBaseURL(null,str,"text/html","utf-8",null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        tv_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MarshMallowPermission permission = new MarshMallowPermission(About_us.this);

                String[] permessionss = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

                if (permission.checkPermissionForREAD_EXTERNAL_STORAGE()) {

                    File f = new File(StaticDataHelper.GetFileName(About_us.this, url));

              if (f.exists()) {

                        openFile(f);

                    } else {


                        new DownloadFile().execute(url);

                    }



                } else {

                    Logger1.e("else","else");
                    permission.requestPermission(permessionss);
                }





            }
        });


        webview.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //view.loadUrl(url);

                Uri uri = Uri.parse(url);
             startActivity(new Intent(Intent.ACTION_VIEW, uri));
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //super.onPageStarted(view, url, favicon);
                showpDialog();



     /*   final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message*/

            }

            @Override
            public void onPageFinished(WebView view, String url) {

                hidepDialog();

            }
        });

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
                        saveToPreferences(About_us.this, ALLOW_KEY, true);
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
        AlertDialog alertDialog = new AlertDialog.Builder(About_us.this).create();
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
                        ActivityCompat.requestPermissions(About_us.this,
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
            this.progressDialog = new ProgressDialog(About_us.this);
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


            Uri uri = FileProvider.getUriForFile(About_us.this, BuildConfig.APPLICATION_ID,url);
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
            Toast.makeText(About_us.this, "No application found which can open the file", Toast.LENGTH_SHORT).show();
        }
    }
}
