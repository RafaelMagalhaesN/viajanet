package viajanet.com.project.main.entities;

import android.app.Activity;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;

/**
 * Created by Rafael Magalhães on 02/02/18.
 */

public interface MainPresenter {
    void onPlaceReceive(Place place);
    void onError(Throwable t);
    void onViewError();
    void setView(MainView view);
    void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults, Activity activity);
    void onPermission(boolean isGranted);
    void addAutoCompleteListener(PlaceAutocompleteFragment placeAutocompleteFragment);
}
