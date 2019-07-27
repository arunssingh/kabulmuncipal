package com.rams.kabulmuncipal.Application;

import android.app.Activity;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MyUIApplication extends MultiDexApplication  {
    // App_ID and APP_NAMESPACE is for Facebook


    public static final String TAG = MyUIApplication.class.getSimpleName();

    private static MyUIApplication instance;

    Activity act;
    Gson gson;

    // Milliseconds per second













    public MyUIApplication() {
        instance = this;
    }



    //









    //
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static Context getContext() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        MultiDex.install(this);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();



        //

 /*       String  language_select= StaticDataHelper.getStringFromPreferences(MyUIApplication.getContext(),
                StaticDataHelper.LANGUAGE_APP);

        if (language_select.equalsIgnoreCase("hi")||language_select.equalsIgnoreCase("hm")) {
            language_select = "hm"; // your language


        }

        if(!language_select.equalsIgnoreCase("")) {


            String languageToLoad = language_select; // your language
            Locale locale = new Locale(languageToLoad);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        }*/
        //
    }

    public void LoadImage(int defaultIcon, int height, int width, Context con, String url, ImageView img, boolean isHeightSet) {
        try {
            RequestOptions myOptions = new RequestOptions()
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .placeholder(defaultIcon);
            if (isHeightSet)
                myOptions.override(width, height);
            Glide.with(con)
                    .load(url)
                    .apply(myOptions)
                    .into(img);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void LoadImagecover(int defaultIcon, int height, int width, Context con, String url, ImageView img, boolean isHeightSet) {
        try {
            RequestOptions myOptions = new RequestOptions()
                    .centerInside()
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .placeholder(defaultIcon);
            if (isHeightSet)
                myOptions.override(width, height);
            Glide.with(con)
                    .load(url)
                    .apply(myOptions)
                    .into(img);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void LoadImagewithcircle(int defaultIcon, int height, int width, Context con, String url, ImageView img, boolean isHeightSet) {
        try {
            RequestOptions myOptions = new RequestOptions()
                    .fitCenter()
                    .circleCrop()
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .placeholder(defaultIcon);
            if (isHeightSet)
                myOptions.override(width, height);
            Glide.with(con)
                    .load(url)
                    .apply(myOptions)
                    .into(img);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void LoadImagealways(int defaultIcon, int height, int width, Context con, String url, ImageView img, boolean isHeightSet) {
        try {
            RequestOptions myOptions = new RequestOptions()
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(defaultIcon);
            if (isHeightSet)
                myOptions.override(width, height);
            Glide.with(con)
                    .load(url)
                    .apply(myOptions)
                    .into(img);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void LoadImagealwaysclear(Context con, ImageView img) {
        try {

            Glide.with(con).clear(img);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Gson getGson() {
        if (gson != null) {
            return gson;
        } else {

            GsonBuilder gsonBuilder = new GsonBuilder();
            gson = gsonBuilder.create();
            return gson;
        }
    }


    public static synchronized MyUIApplication getInstance() {
        return instance;
    }

}
