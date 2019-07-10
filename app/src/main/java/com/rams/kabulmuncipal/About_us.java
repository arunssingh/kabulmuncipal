package com.rams.kabulmuncipal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class About_us extends AppCompatActivity {
WebView webview;
ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        webview=(WebView)findViewById(R.id.webview);
        WebSettings webSettings = webview.getSettings();

       pDialog = new ProgressDialog(About_us.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);


        //
        webSettings.setAllowContentAccess(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        webview.getSettings().setDomStorageEnabled(true);

       // final TransparentProgressDialog Dialog = new TransparentProgressDialog(getActivity(), R.mipmap.loader, "Please wait..");
        webview.loadUrl("https://marvelapp.com/c50i33e");
        webview.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);

               // Uri uri = Uri.parse(url);
             //startActivity(new Intent(Intent.ACTION_VIEW, uri));
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
}
