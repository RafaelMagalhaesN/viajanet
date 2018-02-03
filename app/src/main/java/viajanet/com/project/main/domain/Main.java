package viajanet.com.project.main.domain;

import android.app.Activity;
import android.content.pm.PackageManager;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;

import viajanet.com.project.main.entities.MainModel;
import viajanet.com.project.main.entities.MainPresenter;
import viajanet.com.project.utils.ObservableFactory;

import static viajanet.com.project.utils.PermissionUtils.PERMISSIONS;

/**
 * Created by Rafael Magalhães on 02/02/18.
 */

public class Main implements MainModel {

    private final MainPresenter mMainPresenter;

    public Main(MainPresenter mMainPresenter) {
        this.mMainPresenter = mMainPresenter;
    }

    @Override
    public void onPlaceReceive(LatLng latLng) {
        ObservableFactory.notifyForLocalChanges(latLng);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults, Activity activity) {
        switch (requestCode) {
            case PERMISSIONS:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mMainPresenter.onPermission(true);
                } else {
                    mMainPresenter.onError(new Throwable("Oops, aconteceu um erro! Precisamos da sua localização"));
                }
        }
    }


    @Override
    public void addAutoCompleteListener(PlaceAutocompleteFragment placeAutocompleteFragment) {
        placeAutocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                mMainPresenter.onPlaceReceive(place);
            }

            @Override
            public void onError(Status status) {
                mMainPresenter.onError(new Throwable(status.getStatusMessage()));
            }
        });
    }
}
