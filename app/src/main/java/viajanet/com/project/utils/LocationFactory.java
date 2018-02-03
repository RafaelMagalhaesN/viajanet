package viajanet.com.project.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Rafael Magalhães on 02/02/18.
 */

public class LocationFactory {

    public static LatLng getLastKnownLocation(Context context, LocationListener locationListener) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        double v1 = -23.546391;
        double v2 = -46.637440;

        LatLng latLng = new LatLng(v1, v2);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }

        long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
        long MIN_TIME_BW_UPDATES = 100 * 60;
        try {
            assert locationManager != null;
            locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    MIN_TIME_BW_UPDATES,
                    MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListener);

            Location location = locationManager
                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);

            latLng = new LatLng(location.getLatitude(), location.getLongitude());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return latLng;
    }
}
