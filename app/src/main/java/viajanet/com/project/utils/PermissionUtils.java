package viajanet.com.project.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by Rafael Magalhães on 02/02/18.
 */

public class PermissionUtils {

    public static final int PERMISSIONS = 0x11;

    public static boolean requestPermission(Activity activity) {
        String[] permissions = new String[2];
        boolean isProvided = false;

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissions[0] = Manifest.permission.ACCESS_COARSE_LOCATION;
            isProvided = true;
        }

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissions[1] = Manifest.permission.ACCESS_FINE_LOCATION;
            isProvided = true;
        }

        if (isProvided) {
            ActivityCompat.requestPermissions(activity, permissions, PERMISSIONS);
        }

        return isProvided;
    }
}
