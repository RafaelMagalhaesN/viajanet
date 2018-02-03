package viajanet.com.project.map.entities;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Rafael Magalhães on 01/02/18.
 */

public interface MapModel {
    void setMarker(LatLng latLng);
    void showMap(boolean s);
    void onDestroy();
    void onMapLoad(GoogleMap googleMap, Context context);
    void centerInMyLocation();
}
