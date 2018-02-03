package viajanet.com.project.map.entities;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Rafael Magalhães on 01/02/18.
 */

public interface MapInteracts {
    void showMap(int visibility);
    void moveCamera(LatLng latLng);
    void addMaker(LatLng latLng);
}
