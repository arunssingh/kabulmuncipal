package Utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

/**
 * Created by anil on 7/21/2016.
 */
public class MarshMallowPermission {

    public static final int READ_CALEDAR_REQUEST_CODE = 1;
    public static final int WRITE_CALENDAR_PERMISSION_REQUEST_CODE = 2;
    public static final int CAMERA_PERMISSION_REQUEST_CODE = 3;
    public static final int READ_CONTACTS_REQUEST_CODE = 4;
    public static final int WRITE_CONTACT_REQUEST_CODE = 5;
    public static final int GET_ACCOUNTS_REQUEST_CODE = 6;
    public static final int ACCESS_FINE_LOCATION_REQUEST_CODE = 7;
    public static final int ACCESS_COARSE_LOCATION_REQUEST_CODE = 8;
    public static final int RECORD_AUDIO_REQUEST_CODE = 9;
    public static final int READ_PHONE_STATE_REQUEST_CODE = 10;
    public static final int CALL_PHONE_REQUEST_CODE = 11;
    public static final int BODY_SENSORS_REQUEST_CODE = 12;
    public static final int SEND_SMS_REQUEST_CODE = 13;
    public static final int RECEIVE_SMS_REQUEST_CODE = 14;
    public static final int READ_SMS_REQUEST_CODE = 15;
    public static final int RECEIVE_WAP_PUSH_REQUEST_CODE = 16;
    public static final int RECEIVE_MMS_REQUEST_CODE = 17;
    public static final int READ_EXTERNEL_STORAGE_REQUEST_CODE = 18;
    public static final int WRITE_EXTERNEL_STORAGE_REQUEST_CODE = 19;
    public static final int WRITE_other = 30;
    public static final int READ_CALL_LOG_REQUEST_CODE = 20;
    public static final int WRITE_CALL_LOG_REQUEST_CODE = 21;
    public static final int ADD_VOICEMAIL_REQUEST_CODE = 22;
    public static final int USE_SIP_REQUEST_CODE = 23;
    public static final int PROCESS_OUTGOING_CALLS_REQUEST_CODE = 24;
    Activity activity;

    public MarshMallowPermission(Activity activity) {
        this.activity = activity;
    }

    public boolean checkPermissionForREAD_CALENDAR() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_CALENDAR);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForWRITE_CALENDAR() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_CALENDAR);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForCamera() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForREAD_CONTACTS() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_CONTACTS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForWRITE_CONTACTS() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_CONTACTS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForGET_ACCOUNTS() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.GET_ACCOUNTS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForACCESS_FINE_LOCATION() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForACCESS_COARSE_LOCATION() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForRECORD_AUDIO() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForREAD_PHONE_STATE() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForCALL_PHONE() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForREAD_CALL_LOG() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_CALL_LOG);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForWRITE_CALL_LOG() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_CALL_LOG);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForADD_VOICEMAIL() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.ADD_VOICEMAIL);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForUSE_SIP() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.USE_SIP);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForPROCESS_OUTGOING_CALLS() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.PROCESS_OUTGOING_CALLS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForBODY_SENSORS() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.BODY_SENSORS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForSEND_SMS() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.SEND_SMS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForRECEIVE_SMS() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.RECEIVE_SMS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForREAD_SMS() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_SMS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForRECEIVE_WAP_PUSH() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.RECEIVE_WAP_PUSH);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForRECEIVE_MMS() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.RECEIVE_MMS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForREAD_EXTERNAL_STORAGE() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForWRITE_EXTERNAL_STORAGE() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public void requestPermissionForREAD_CALENDAR() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_CALENDAR)) {
            Toast.makeText(activity, "READ CALENDAR permission needed for recording. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_CALENDAR}, READ_CALEDAR_REQUEST_CODE);
        }
    }

    public void requestPermissionForWRITE_CALENDAR() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_CALENDAR)) {
            Toast.makeText(activity, "WRITE CALENDAR permission needed. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_CALENDAR}, WRITE_CALENDAR_PERMISSION_REQUEST_CODE);
        }
    }

    public void requestPermissionForCamera() {
        /*if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)){
            Toast.makeText(activity, "Camera permission needed. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
        } else {*/

        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
        // }
    }

    public void requestPermissionForREAD_CONTACTS() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_CONTACTS)) {
            Toast.makeText(activity, "READ CONTACTS permission needed. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_CONTACTS}, READ_CONTACTS_REQUEST_CODE);
        }
    }

    public void requestPermissionForWRITE_CONTACTS() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_CONTACTS)) {
            Toast.makeText(activity, "WRITE CONTACTS permission needed. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_CONTACTS}, WRITE_CONTACT_REQUEST_CODE);
        }
    }

    public void requestPermissionForGET_ACCOUNTS() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.GET_ACCOUNTS)) {
            Toast.makeText(activity, "GET ACCOUNTS permission needed. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.GET_ACCOUNTS}, GET_ACCOUNTS_REQUEST_CODE);
        }
    }

    public void requestPermissionForACCESS_FINE_LOCATION() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)) {
            Toast.makeText(activity, "ACCESS FINE LOCATION permission needed. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_FINE_LOCATION_REQUEST_CODE);
        }
    }

    public void requestPermissionForACCESS_COARSE_LOCATION() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            Toast.makeText(activity, "ACCESS COARSE LOCATION permission needed. Please allow in App Settings for additional functionality.",
                    Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    ACCESS_COARSE_LOCATION_REQUEST_CODE);
        }
    }

    public void requestPermissionForRECORD_AUDIO() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.RECORD_AUDIO)) {
            Toast.makeText(activity, "RECORD AUDIO permission needed. Please allow in App Settings for additional functionality.",
                    Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.RECORD_AUDIO},
                    RECORD_AUDIO_REQUEST_CODE);
        }
    }

    public void requestPermissionForREAD_PHONE_STATE() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_PHONE_STATE)) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_PHONE_STATE},
                    READ_PHONE_STATE_REQUEST_CODE);
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_PHONE_STATE},
                    READ_PHONE_STATE_REQUEST_CODE);
        }
    }

    public void requestPermissionForCALL_PHONE() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CALL_PHONE)) {

            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE},
                    CALL_PHONE_REQUEST_CODE);
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE},
                    CALL_PHONE_REQUEST_CODE);
        }
    }

    public void requestPermissionForREAD_CALL_LOG() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_CALL_LOG)) {
            Toast.makeText(activity, "READ CALL LOG permission needed. Please allow in App Settings for additional functionality.",
                    Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_CALL_LOG},
                    READ_CALL_LOG_REQUEST_CODE);
        }
    }

    public void requestPermissionForWRITE_CALL_LOG() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_CALL_LOG)) {
            Toast.makeText(activity, "WRITE CALL LOG permission needed. Please allow in App Settings for additional functionality.",
                    Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_CALL_LOG},
                    WRITE_CALL_LOG_REQUEST_CODE);
        }
    }

    public void requestPermissionForADD_VOICEMAIL() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ADD_VOICEMAIL)) {
            Toast.makeText(activity, "ADD VOICEMAIL permission needed. Please allow in App Settings for additional functionality.",
                    Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ADD_VOICEMAIL},
                    ADD_VOICEMAIL_REQUEST_CODE);
        }
    }

    public void requestPermissionForUSE_SIP() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.USE_SIP)) {
            Toast.makeText(activity, "USE SIP permission needed. Please allow in App Settings for additional functionality.",
                    Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.USE_SIP},
                    USE_SIP_REQUEST_CODE);
        }
    }

    public void requestPermissionForPROCESS_OUTGOING_CALLS() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.PROCESS_OUTGOING_CALLS)) {
            Toast.makeText(activity, "PROCESS OUTGOING CALLS permission needed. Please allow in App Settings for additional functionality.",
                    Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.PROCESS_OUTGOING_CALLS},
                    PROCESS_OUTGOING_CALLS_REQUEST_CODE);
        }
    }

    public void requestPermissionForBODY_SENSORS() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.BODY_SENSORS)) {
            Toast.makeText(activity, "BODY SENSORS permission needed. Please allow in App Settings for additional functionality.",
                    Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.BODY_SENSORS},
                    BODY_SENSORS_REQUEST_CODE);
        }
    }

    public void requestPermissionForSEND_SMS() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.SEND_SMS)) {
            Toast.makeText(activity, "SEND SMS permission needed. Please allow in App Settings for additional functionality.",
                    Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.SEND_SMS},
                    SEND_SMS_REQUEST_CODE);
        }
    }

    public void requestPermissionForRECEIVE_SMS() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.RECEIVE_SMS)) {
            Toast.makeText(activity, "RECEIVE SMS permission needed. Please allow in App Settings for additional functionality.",
                    Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.RECEIVE_SMS},
                    RECEIVE_SMS_REQUEST_CODE);
        }
    }

    public void requestPermissionForREAD_SMS() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_SMS)) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_SMS},
                    READ_SMS_REQUEST_CODE);
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_SMS},
                    READ_SMS_REQUEST_CODE);
        }
    }

    public void requestPermissionForRECEIVE_WAP_PUSH() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.RECEIVE_WAP_PUSH)) {
            Toast.makeText(activity, "RECEIVE WAP_PUSH permission needed. Please allow in App Settings for additional functionality.",
                    Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.RECEIVE_WAP_PUSH},
                    RECEIVE_WAP_PUSH_REQUEST_CODE);
        }
    }

    public void requestPermissionForRECEIVE_MMS() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.RECEIVE_MMS)) {
            Toast.makeText(activity, "RECEIVE MMS permission needed. Please allow in App Settings for additional functionality.",
                    Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.RECEIVE_MMS},
                    RECEIVE_MMS_REQUEST_CODE);
        }
    }

    public void requestPermissionForREAD_EXTERNAL_STORAGE() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(activity, "READ EXTERNAL STORAGE permission needed. Please allow in App Settings for additional functionality.",
                    Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    READ_EXTERNEL_STORAGE_REQUEST_CODE);
        }
    }

    public void requestPermissionForWRITE_EXTERNAL_STORAGE() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(activity, "WRITE EXTERNAL STORAGE permission needed. Please allow in App Settings for additional functionality.",
                    Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    WRITE_EXTERNEL_STORAGE_REQUEST_CODE);
        }
    }

    public void requestPermission(String[] mper) {

        ActivityCompat.requestPermissions(activity, mper,
                WRITE_other);

    }
}
