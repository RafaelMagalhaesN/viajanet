package viajanet.com.project.map.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import viajanet.com.project.R;
import viajanet.com.project.map.entities.MapPresenter;
import viajanet.com.project.map.entities.MapInteracts;
import viajanet.com.project.utils.MarkerFactory;
import viajanet.com.project.utils.PlaceObservable;

/**
 * Created by Rafael Magalhães on 01/02/18.
 */

public class MapFragment extends Fragment implements OnMapReadyCallback, MapInteracts, PlaceObservable{

    private static MapPresenter mMapPresenter;
    private GoogleMap mGoogleMap;
    private View view;
    private MapView mMapView;
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mMapPresenter == null) {
            mMapPresenter = new viajanet.com.project.map.domain.MapPresenter();
        }
        mMapPresenter.setView(this);
        view = inflater.inflate(R.layout.fragment_map, container, false);
        mMapView = view.findViewById(R.id.map);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mContext = view.getContext();
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        mMapView.getMapAsync(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mGoogleMap = googleMap;
        mMapPresenter.onMapLoad(googleMap, mContext);
    }

    @Override
    public void showMap(int visibility) {
        if (view != null) {
            view.setVisibility(visibility);
        }
    }

    @Override
    public void onDestroy() {
        mMapPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void moveCamera(LatLng latLng) {
        if (mGoogleMap != null) {
            int zoom = 15;
            mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
        }
    }

    @Override
    public void addMaker(LatLng latLng) {
        if (mGoogleMap != null) {
            mGoogleMap.clear();
            MarkerFactory.create(mGoogleMap, MarkerFactory.createOptions(latLng));
            mMapPresenter.moveCamera(latLng);
        }
    }

    @Override
    public void onChangeLocal(LatLng latLng) {
        if (mGoogleMap != null) {
            mGoogleMap.clear();
            mMapPresenter.moveCamera(latLng);
            mMapPresenter.setMarker(latLng);
        }
    }

    @Override
    public void onMapReady(boolean t) {

    }

}
