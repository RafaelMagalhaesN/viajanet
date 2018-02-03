package viajanet.com.project.utils;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Rafael Magalhães on 01/02/18.
 */

public interface PlaceObservable {
    void onChangeLocal(LatLng latLng);
    void onMapReady(boolean t);
}
