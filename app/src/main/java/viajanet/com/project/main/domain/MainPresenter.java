package viajanet.com.project.main.domain;

import android.app.Activity;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.maps.model.LatLng;

import viajanet.com.project.main.entities.MainModel;
import viajanet.com.project.main.entities.MainView;

/**
 * Created by Rafael Magalhães on 02/02/18.
 */

public class MainPresenter implements viajanet.com.project.main.entities.MainPresenter {


    private final MainModel mMainModel;

    private MainView mMainView;

    public MainPresenter() {
        mMainModel = new Main(this);
    }


    @Override
    public void onPlaceReceive(Place place) {
        LatLng latLng = editPlaceObject(place);
        mMainModel.onPlaceReceive(latLng);
    }

    @Override
    public void onError(Throwable t) {
        mMainView.showToast(t);
    }

    @Override
    public void setView(MainView view) {
        this.mMainView = view;
    }

    private LatLng editPlaceObject(Place place) {
        return place.getLatLng();
    }

    @Override
    public void onViewError() {
        String message = "Desculpe, aconteceu um erro inesperado. Por favor, tente novamente!";
        mMainView.showToast(new Throwable(message));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults, Activity activity) {
        mMainModel.onRequestPermissionsResult(requestCode, permissions, grantResults, activity);
    }

    @Override
    public void onPermission(boolean isGranted) {
        mMainView.onPermission(isGranted);
    }

    @Override
    public void addAutoCompleteListener(PlaceAutocompleteFragment placeAutocompleteFragment) {
        mMainModel.addAutoCompleteListener(placeAutocompleteFragment);
    }
}
