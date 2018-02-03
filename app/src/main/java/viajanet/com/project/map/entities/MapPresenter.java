package viajanet.com.project.map.entities;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Rafael Magalhães on 01/02/18.
 */

public interface MapPresenter {

    void moveCamera(LatLng latLng);
    void setMarker(LatLng latLng);
    void showMap(int visibility);
    void setView(MapInteracts mapInteracts);
    void onMapLoad(GoogleMap googleMap, Context context);
    void onDestroy();
}
