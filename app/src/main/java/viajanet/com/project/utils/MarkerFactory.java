package viajanet.com.project.utils;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Rafael Magalhães on 30/01/18.
 */

public class MarkerFactory {

    public static MarkerOptions createOptions(LatLng latLng) {
        return new MarkerOptions().position(latLng);
    }

    public static void create(GoogleMap map, MarkerOptions markerOptions) {
        map.addMarker(markerOptions);
    }
}