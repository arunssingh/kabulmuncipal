package com.rams.kabulmuncipal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;



import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import Utils.Logger1;

public class SplashActivity extends AppCompatActivity {

    private String LOG_TAG = "SplashActivity";


    public static void generateHashKeyForFb(Context context)
    {
        try
        {
            PackageInfo info = context.getPackageManager().getPackageInfo(BuildConfig.APPLICATION_ID, PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures)
            {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Logger1.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        }
        catch (NameNotFoundException e) {
            Logger1.e("NameNotFoundException:", e.getMessage());
        }
        catch (NoSuchAlgorithmException e) {
            Logger1.e("NoSuchAlgorithmException:", e.getMessage());
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //   setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        //  this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash_activity);


        generateHashKeyForFb(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
              //  MyUIApplication.getInstance().InitializeData();
                //Analytics.AddData("Splash");
            }
        }).start();

   /*     Intent intent = getIntent();
       String s= intent.getDataString();
        Logger1.e("arun", "data=" + s);

        Uri data = intent.getData();
        if(data!=null) {
            Logger1.e("arun", "data=" + data);
        }*/

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleSendText(intent); // Handle text being sent
            }
            else if (type.startsWith("image/")) {
                handleSendImage(intent); // Handle single image being sent
            }
        }
        startSplash();
    }

    void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);

        Logger1.e("arun","sharetext="+sharedText);
        if (sharedText != null) {
            // Update UI to reflect text being shared

           // Intent as = new Intent(SplashActivity.this,Add_Post_Activity.class);
            //as.putExtra("value",sharedText);
            //startActivity(as);
        }
    }

    void handleSendImage(Intent intent) {
        Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        Logger1.e("arun", "imageUri=" + imageUri);
        if (imageUri != null) {
            // Update UI to reflect image being shared
        }
    }


        @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    private void startSplash()
    {

        //startService(new Intent(SplashActivity.this, MyService.class));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {


                            startActivity(new Intent(SplashActivity.this,MainActivity.class));
                        finish();
                    }
                });
            }
        },1500);

    }



}