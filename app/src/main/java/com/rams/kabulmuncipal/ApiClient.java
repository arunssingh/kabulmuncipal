package com.rams.kabulmuncipal;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Arun on 6/13/2018.
 */

public class ApiClient {



    ////Live Data
 public static final String BASE_URL = "http://13.126.162.107:3003/api/v1/user/";




    //// Test Data

   //public static final String BASE_URL = "http://ramscreative.com/mahindrademo/api/";
//public static final String BASE_URL = "http://192.168.0.21:3003/api/v1/user/";
//    public static final String SOCKET_URL="http://192.168.0.177:3003/";

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

     //  HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
     //   interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
     //  OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
         // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                Request.Builder builder = originalRequest.newBuilder()
                        .header("Authorization",
                       Credentials.basic("user", "user"));


                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        }).addInterceptor(logging)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(600,TimeUnit.SECONDS).build();


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                //  .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(okHttpClient)
                .build();



        return retrofit;
    }

}
