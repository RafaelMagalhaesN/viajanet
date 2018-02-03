package viajanet.com.project.map.domain;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import viajanet.com.project.map.entities.MapModel;
import viajanet.com.project.map.entities.MapPresenter;
import viajanet.com.project.utils.LocationFactory;
import viajanet.com.project.utils.ObservableFactory;
import viajanet.com.project.utils.PlaceObservable;

/**
 * Created by Rafael Magalhães on 30/01/18.
 */

public class Map implements MapModel, PlaceObservable, LocationListener {


    private final MapPresenter mMapPresenter;

    private LatLng mMyLocation;

    private GoogleMap mGoogleMap;

    public Map(MapPresenter mapPresenter) {
        this.mMapPresenter = mapPresenter;
        ObservableFactory.addObservable(this);
    }

    @Override
    public void setMarker(LatLng latLng) {
        mMapPresenter.setMarker(latLng);
    }

    @Override
    public void showMap(boolean s) {
        mMapPresenter.showMap(s ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onChangeLocal(LatLng latLng) {
        setMarker(latLng);
    }

    @Override
    public void onMapReady(boolean t) {
        showMap(t);
    }

    @Override
    public void onDestroy() {
        ObservableFactory.removeObservable(this);
    }

    @Override
    public void onMapLoad(GoogleMap googleMap, Context context) {
        this.mMyLocation = LocationFactory.getLastKnownLocation(context, this);
        this.mGoogleMap = googleMap;

        if (googleMap != null) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            googleMap.setMyLocationEnabled(true);
            googleMap.setPadding(0,200,0,0);
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(this.mMyLocation)             // Sets the center of the map to Mountain View
                    .zoom(15)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            double v1 = location.getLatitude();
            double v2 = location.getLongitude();

            this.mMyLocation = new LatLng(v1, v2);
        }
    }

    @Override
    public void centerInMyLocation() {
        if (mGoogleMap != null) {
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(this.mMyLocation)             // Sets the center of the map to Mountain View
                    .zoom(15)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mGoogleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
