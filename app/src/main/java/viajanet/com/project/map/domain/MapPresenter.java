package viajanet.com.project.map.domain;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import viajanet.com.project.map.entities.MapInteracts;
import viajanet.com.project.map.entities.MapModel;

/**
 * Created by Rafael Magalhães on 01/02/18.
 */

public class MapPresenter implements viajanet.com.project.map.entities.MapPresenter {

    private final MapModel mModel;
    private MapInteracts mMapInteracts;

    public MapPresenter() {
        mModel = new Map(this);
    }

    @Override
    public void moveCamera(LatLng latLng) {
        mMapInteracts.moveCamera(latLng);
    }

    @Override
    public void setMarker(LatLng latLng) {
        mMapInteracts.addMaker(latLng);
    }

    @Override
    public void showMap(int visibility) {
        mMapInteracts.showMap(visibility);
    }

    @Override
    public void setView(MapInteracts mapInteracts) {
        this.mMapInteracts = mapInteracts;
    }

    @Override
    public void onMapLoad(GoogleMap googleMap, Context context) {
        mModel.onMapLoad(googleMap, context);
    }

    @Override
    public void onDestroy() {
        mModel.onDestroy();
    }

}
