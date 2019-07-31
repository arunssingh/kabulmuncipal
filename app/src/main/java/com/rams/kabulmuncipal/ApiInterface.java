package com.rams.kabulmuncipal;



import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {
    @FormUrlEncoded
    @POST("register")
    Call<String> createUser(
            @Field("name") String name,
            @Field("phone") String phone,
            @Field("email") String email,
            @Field("password") String password,
            @Field("cnf_password") String cnf_password,
            @Field("device_id") String device_id,
            @Field("role") String role,
            @Field("question_cat_id") String question_cat_id,
            @Field("lat") String lat,
            @Field("lng") String lng

    );
    @FormUrlEncoded
    @POST("socialregister")
    Call<String> socialLogin(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("social_id") String social_id,
            @Field("device_id") String device_id
    );
    //

    @GET("score/{user_id}")
    Call<String> getUserscore(
            @Path("user_id") String user_id


    );


    @GET("checkfriend/{login_id}/{user_id}")
    Call<String> getUserProfileDetailwithlogin(
            @Path("user_id") int user_id,
            @Path("login_id") String login_id

    );

    @FormUrlEncoded
    @POST("comment")
    Call<String> Creatcooment(
            @Field("user_id") String user_id,
            @Field("post_id") String post_id,
            @Field("comment") String comment
    );

    @GET("districts")
    Call<String> getdistricts();
    @GET("directortiats")
    Call<String> getdirct();
    //
    @GET("issues")
    Call<String> getissue(
            @Query("directorate_id") String directorate_id
    );
    @GET("complaints")
    Call<String> getcomplaint();
    @GET("information")
    Call<String> getinformation();

    @GET("development-plans")
    Call<String> getdevelopment(
            @Query("language") String language,
            @Query("information_id") String information_id
    );
    @Multipart
    @POST("register-complaint")
    Call<String> postcomlaint(
            @Part("name") RequestBody name,
            @Part("phone") RequestBody phone,
            @Part("email") RequestBody email,
            @Part("gender") RequestBody gender,
            @Part("district") RequestBody district,
            @Part("directorate") RequestBody directorate,
            @Part("issues") RequestBody issues,
            @Part("remarks") RequestBody remarks,
            @Part MultipartBody.Part attachment


    );

    @GET("zones")
    Call<String> getzonewise(
            @Query("district_id") String district_id
    );
    @GET("land-type")
    Call<String> getlandwise(
            @Query("district_id") String district_id,
            @Query("zone_id") String zone_id
    );
    @GET("building-type")
    Call<String> getbuildwise(
            @Query("district_id") String district_id,
            @Query("zone_id") String zone_id,
            @Query("land_id") String land_id
    );

/*    fname:String,
    lname:String,
    phone:String,
    email:String,
    designation:String,
    gender:String,
    district:String,
    directorate:String,
    issues:String,
    attachment:String,
    remarks:String*/
    @Multipart
    @POST("communitypost")
    Call<String> postfeed_cover(
            @Part("user_id") RequestBody user_id,
            @Part("community_id") RequestBody community_id,
            @Part("type") RequestBody type,
            @Part("description") RequestBody description,
            @Part MultipartBody.Part src


    );

}