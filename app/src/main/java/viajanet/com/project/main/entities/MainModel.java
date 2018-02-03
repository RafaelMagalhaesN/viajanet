package viajanet.com.project.main.entities;

import android.app.Activity;

import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Rafael Magalhães on 02/02/18.
 */

public interface MainModel {
    void onPlaceReceive(LatLng latLng);
    void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults, Activity activity);
    void addAutoCompleteListener(PlaceAutocompleteFragment placeAutocompleteFragment);

}
