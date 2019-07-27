package Utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AlertDialog;
import android.view.Window;
import android.widget.Toast;


import com.rams.kabulmuncipal.Application.MyUIApplication;
import com.rams.kabulmuncipal.R;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by arun on 17-Sep-16.
 */
public class StaticDataHelper {


    public static final String PREF_NAME = "Kabul_app";



    //
    private static SharedPreferences pref;



    public static String isUserLogin = "isUserLogin";

    public static String UserId = "userId";

    public static String UserImageUrl = "userImage";

    public static String Delivery_Address = "Delivery_Address";

    public static String Lat = "lat";
    public static String LNG = "lng";
    public static String RADIOUS = "radius";
    public static String ADDRESS = "address";
    public static String LOCALITY = "locality";

    public static String Selected_Category = "selected_cat_id";
    public static String LANGUAGE = "LANGUAGE";



    public static String getUserID(Context con) {

            return getStringFromPreferences(con,UserId);
    }
    public static String getUserImage(Context con) {

        return getStringFromPreferences(con,UserImageUrl);
    }
    public static String TERMS_URL = "https://yeorder.com/terms-&-condition.php";



    public static SharedPreferences getPreferences(Context ctx) {
       // Logger1.e("context",""+ctx);
        if (ctx==null)
            ctx= MyUIApplication.getContext();
        pref = ctx.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
       return pref;
    }
    public static void clearPrefs(Context ctx) {
        pref = getPreferences(ctx);
        SharedPreferences.Editor editor = pref.edit();

        editor.clear();
    }

    public static void setStringInPreferences(Context ctx, String key, String value) {
        pref = getPreferences(ctx);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString(key, value);

        editor.apply();
    }

    public static String getParseDate(String source){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat df1 = new SimpleDateFormat("dd MMM");
        Date date1 = null;
        try {
            date1 = df.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return df1.format(date1);
    }
    public static String getParseTime(String source){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat df1 = new SimpleDateFormat("HH:mm a");
        Date date1 = null;
        try {
            date1 = df.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return df1.format(date1);

    }
    public static void setBooleanInPreferences(Context ctx, String key, boolean value) {
        pref = getPreferences(ctx);
        SharedPreferences.Editor editor = pref.edit();

        editor.putBoolean(key, value);

        editor.apply();
    }

    public static String getStringFromPreferences(Context ctx, String key) {
        pref = getPreferences(ctx);
        return pref.getString(key, "");
    }

    public static String getDeliveryAddress(Context ctx, String key) {
if (ctx==null)
    ctx=MyUIApplication.getContext();
        pref = getPreferences(ctx);
        return pref.getString(key, "");
    }

    public static double getLat(Context ctx) {
        try {
            if (ctx != null) {
                pref = getPreferences(ctx);
                return Double.valueOf(pref.getString(Lat, "0.0"));
            } else {
                return 0.0;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    public static double getLng(Context ctx) {
        try {
            if (ctx != null) {
                pref = getPreferences(ctx);
                return Double.valueOf(pref.getString(LNG, "0.0"));
            } else {
                return 0.0;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    public static String getRadius(Context ctx) {
        pref = getPreferences(ctx);
        return pref.getString(RADIOUS, "5000");
    }

    public static String getADDRESS(Context ctx) {
        pref = getPreferences(ctx);
        return pref.getString(ADDRESS, "");
    }
    public static String getLocality(Context ctx) {
        pref = getPreferences(ctx);
        return pref.getString(LOCALITY, "");
    }


    public static String getSelected_Category(Context ctx) {
        pref = getPreferences(ctx);
        return pref.getString(Selected_Category, "");
    }

    public static boolean getBooleanFromPreferences(Context ctx, String key) {
        pref = getPreferences(ctx);
        return pref.getBoolean(key, false);
    }

    public static void setSelected_Category(Context ctx, String cat) {
        pref = getPreferences(ctx);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(Selected_Category, "" + cat);
        editor.apply();
    }

    public static void setLat(Context ctx, double lat) {
        pref = getPreferences(ctx);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(Lat, "" + lat);
        editor.apply();
    }

    public static void setLng(Context ctx, double lng) {
        pref = getPreferences(ctx);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(LNG, "" + lng);
        editor.apply();
    }

    public static void setRadious(Context ctx, String radius) {
        pref = getPreferences(ctx);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(RADIOUS, "" + radius);
        editor.apply();
    }

    public static void setAddress(Context ctx, String address) {
        pref = getPreferences(ctx);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(ADDRESS, "" + address);
        editor.apply();
    }
    public static void setLocality(Context ctx, String locality) {
        pref = getPreferences(ctx);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(LOCALITY, "" + locality);
        editor.apply();
    }

    public static void setDeliveryAddress(Context ctx, String address) {
        pref = getPreferences(ctx);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(Delivery_Address, "" + address);
        editor.apply();
    }

    public static void showtoast(Context ctx, String msg) {
        if (ctx!=null)
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
    }

    public static boolean isNetworkConnected(Context con) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;
        Context conn = con;
        if (con == null) {
            conn = MyUIApplication.getInstance();
        }
        ConnectivityManager cm = (ConnectivityManager) conn.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();

        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI")) {
                if (ni.isConnected()) {
                    haveConnectedWifi = true;
                }
            }
            if (ni.getTypeName().equalsIgnoreCase("MOBILE")) {
                if (ni.isConnected()) {
                    haveConnectedMobile = true;
                }
            }
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    public static boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

   /* public static void showLoginDialog(final Context con) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(con, R.style.CustomDialog);

        // Setting Dialog Title
        alertDialog.setTitle("Alert.");

        // Setting Dialog Message
        alertDialog.setMessage("You are not Logged in.For this need to Login. Do you want to Login ?.");

//        // Setting Icon to Dialog
//        alertDialog.setIcon(R.drawable.delete);

        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
                con.startActivity(new Intent(con, Login.class));
            }
        });

        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to invoke NO event

                dialog.cancel();
            }
        });
        alertDialog.setCancelable(false);
        // Showing Alert Message
        alertDialog.show();
    }*/

    public static void Call(Context con, String no,String seller_id,String buyer_id,String user_type) {
        if (!no.equalsIgnoreCase("")) {
            try {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + no));
                con.startActivity(intent);
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        } else {
            StaticDataHelper.showtoast(con, "Phone no not found.");
        }
    }
    /*public static void Navigate(Context con, Uri routeUri) {

        Analytics.AddData("Navigation");

        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, routeUri);
            // Uri gmmIntentUri = Uri.parse("google.navigation:q=Jaipur+Railway+Station,+Jaipur+Rajasthan");
            //Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            intent.setPackage("com.google.android.apps.maps");
            con.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public static String Format_Date(String date) {
        try {
            // 2017-03-29 14:40:26
            //  String strCurrentDate = "Wed, 18 Apr 2012 07:55:29 +0000";
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            //SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss Z");
            Date newDate = null;

            newDate = format.parse(date);


            format = new SimpleDateFormat("dd MMM yyyy hh:mm aa");
            return format.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return date;
        }
    }

    /*public static String Format_Date_Only(String date) {
        try {
            // 2017-03-29 14:40:26
            //  String strCurrentDate = "Wed, 18 Apr 2012 07:55:29 +0000";
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            //SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss Z");
            Date newDate = null;

            newDate = format.parse(date);


            //  format = new SimpleDateFormat("dd MMM yyyy hh:mm a");
            format = new SimpleDateFormat("dd MMM yyyy");
            return format.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return date;
        }
    }*/
    public static String Format_Date_With_Time(String date) {
        try {
            // 2017-03-29 14:40:26
            //  String strCurrentDate = "Wed, 18 Apr 2012 07:55:29 +0000";
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
            format.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date newDate = null;

            newDate = format.parse(date);
            format.setTimeZone(TimeZone.getDefault());

            format = new SimpleDateFormat("dd MMM yyyy    hh:mm aa");
            return format.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return date;
        }
    }
    public static String Format_Date_Only(String date) {
        try {
            // 2017-03-29 14:40:26
            //  String strCurrentDate = "Wed, 18 Apr 2012 07:55:29 +0000";
            //  String strCurrentDate = "2018-01-16T12:53:19.200Z";
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
            //SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss Z");
            Date newDate = null;

            newDate = format.parse(date);
            //  format = new SimpleDateFormat("dd MMM yyyy hh:mm a");
            format = new SimpleDateFormat("dd MMM yyyy");
            Logger1.e("staticdatahelper log", format.format(newDate));
            return format.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
            Logger1.e("date", e.getMessage());
            return date;
        }
    }

    public static String Format_Time_notification(String date) {
        try {
            // 2017-03-29 14:40:26
            //  String strCurrentDate = "Wed, 18 Apr 2012 07:55:29 +0000";
            //  String strCurrentDate = "2018-01-16T12:53:19.200Z";
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
            //SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss Z");
            Date newDate = null;

            newDate = format.parse(date);
            //  format = new SimpleDateFormat("dd MMM yyyy hh:mm a");
            format = new SimpleDateFormat("hh:mm:ss aa");
            Logger1.e("staticdatahelper log", format.format(newDate));
            return format.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
            Logger1.e("date", e.getMessage());
            return date;
        }
    }


    public static String Format_Date_Appointments(String date) {
        try {
            // 05/30/2018

            //  String strCurrentDate = "Wed, 18 Apr 2012 07:55:29 +0000";
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            //SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss Z");
            Date newDate = null;

            newDate = format.parse(date);


            //  format = new SimpleDateFormat("dd MMM yyyy hh:mm a");
            format = new SimpleDateFormat("dd MMM yyyy");
            return format.format(newDate);

        } catch (ParseException e) {
         //   e.printStackTrace();
            try {
                return date.substring(0,10);
            } catch (Exception e1) {
                e1.printStackTrace();
                return date;
            }
        }
    }
    public static String Format_Date_Appointments_Seller(String date) {
        try {
            // 05/30/2018

            //  String strCurrentDate = "Wed, 18 Apr 2012 07:55:29 +0000";
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            //SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss Z");
            Date newDate = null;

            newDate = format.parse(date);


            //  format = new SimpleDateFormat("dd MMM yyyy hh:mm a");
            format = new SimpleDateFormat("MM/dd/yyyy");
            return format.format(newDate);

        } catch (ParseException e) {
            e.printStackTrace();
            try {
                return date.substring(0,10);
            } catch (Exception e1) {
                e1.printStackTrace();
                return date;
            }
        }
    }






    public static void startActivity(Activity context, Intent intent)
    {
        context.startActivity(intent);
       // context.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        //		 context.overridePendingTransition(R.drawable.activity_open_translate,R.drawable.activity_close_scale);
    }

    //

    public static String getRealPath(Context context, Uri fileUri) {
        String realPath;
        // SDK < API11
        if (Build.VERSION.SDK_INT < 11) {
            realPath = StaticDataHelper.getRealPathFromURI_BelowAPI11(context, fileUri);
        }
        // SDK >= 11 && SDK < 19
        else if (Build.VERSION.SDK_INT < 19) {
            realPath = StaticDataHelper.getRealPathFromURI_API11to18(context, fileUri);
        }
        // SDK > 19 (Android 4.4) and up
        else {
            realPath = StaticDataHelper.getRealPathFromURI_API19(context, fileUri);
        }
        return realPath;
    }


    @SuppressLint("NewApi")
    public static String getRealPathFromURI_API11to18(Context context, Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        String result = null;

        CursorLoader cursorLoader = new CursorLoader(context, contentUri, proj, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();

        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            result = cursor.getString(column_index);
            cursor.close();
        }
        return result;
    }

    public static String getRealPathFromURI_BelowAPI11(Context context, Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
        int column_index = 0;
        String result = "";
        if (cursor != null) {
            column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            result = cursor.getString(column_index);
            cursor.close();
            return result;
        }
        return result;
    }

    /**
     * Get a file path from a Uri. This will get the the path for Storage Access
     * Framework Documents, as well as the _data field for the MediaStore and
     * other file-based ContentProviders.
     *
     * @param context The context.
     * @param uri     The Uri to query.
     * @author paulburke
     */
    @SuppressLint("NewApi")
    public static String getRealPathFromURI_API19(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
    //
    public static void Show_Dialog(Context con)
    {
        final Dialog dialog = new Dialog(con);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.no_network);



        try {
            dialog.show();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //

    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, 0);
            return true;
        }
        catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }


    public static boolean check_sdcardAvailable()
    {
        boolean isPresent = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);

        if (isPresent) {
            return true;
        }
        return false;
    }
    public static String getSdCard_Path(){

        String mediaFile=null;

        boolean isPresent = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED
        );

        if (isPresent) {
            // Check that the SDCard is mounted
            File mediaStorageDir = new File(Environment.getExternalStorageDirectory(), "Kabul");
            //		File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
            //				Environment.DIRECTORY_MOVIES), "YeDub");


            // Create the storage directory(MyCameraVideo) if it does not exist
            if (! mediaStorageDir.exists()){

                if (! mediaStorageDir.mkdirs()){


                    return null;
                }
            }

            // Create a media file name

            // For unique file name appending current timeStamp with file name

            // For unique video file name appending current timeStamp with file name
            mediaFile = mediaStorageDir.getPath() ;


        }
        else{

            // Check that the SDCard is mounted
            File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Kabul");
            //		File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
            //				Environment.DIRECTORY_PICTURES), "YeDubVideo");


            // Create the storage directory(MyCameraVideo) if it does not exist
            if (! mediaStorageDir.exists()){

                if (! mediaStorageDir.mkdirs()){


                    return null;
                }
            }

            mediaFile = mediaStorageDir.getPath() ;



        }

        return mediaFile;
    }
    //
    public static String GetFileName(Activity con1, String url)
    {
        //try {
        int index1=url.lastIndexOf("/");

        String filename=url.substring(index1,(url.length()));


        File mediaStorageDir = new File(StaticDataHelper.CreateSdCard_Path(con1,"Doc"));
        //	File mediaStorageDir = new File(Environment.getExternalStorageDirectory(), "YeDubVideo");


        // Create the storage directory(MyCameraVideo) if it does not exist
        if (! mediaStorageDir.exists()){

            if (! mediaStorageDir.mkdirs()){


            }
        }

        String outputFile = mediaStorageDir.getPath() +filename;
        return outputFile;


    }

    public static String CreateSdCard_Path(Activity con, String folderName)
    {
        String path = getSdCard_Path();
        String mediaFile;

        // For unique video file name appending current timeStamp with file name
        mediaFile = path + File.separator + folderName;

        File mediaStorageDir1 = new File(mediaFile);

        // Create the storage directory(MyCameraVideo) if it does not exist
        if (! mediaStorageDir1.exists())
        {
            if (! mediaStorageDir1.mkdirs())
            {

            }
        }

        return mediaStorageDir1.getPath();
    }
}