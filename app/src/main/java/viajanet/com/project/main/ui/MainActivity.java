package viajanet.com.project.main.ui;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import viajanet.com.project.R;
import viajanet.com.project.databinding.ActivityMainBinding;
import viajanet.com.project.main.entities.MainPresenter;
import viajanet.com.project.main.entities.MainView;
import viajanet.com.project.map.ui.MapFragment;
import viajanet.com.project.utils.PermissionUtils;
import viajanet.com.project.utils.PlaceAutoCompleteFactory;

public class MainActivity extends AppCompatActivity implements MainView{

    private ActivityMainBinding mActivityMainBinding;

    private MapFragment mMapFragment;

    private static MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        if (mMainPresenter == null) {
            mMainPresenter = new viajanet.com.project.main.domain.MainPresenter();
            mMainPresenter.setView(this);
        }

        checkPermissionsButton();

        if(!PermissionUtils.requestPermission(this)) {
            showContent();
            mMapFragment = new MapFragment();
            initializeMap();
            initializePlaceAutoComplete();
        } else {
            onError();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mMainPresenter.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void onError() {
        mActivityMainBinding.btnCheckPermissions.setVisibility(View.VISIBLE);
        mActivityMainBinding.txtError.setVisibility(View.VISIBLE);
        mActivityMainBinding.mapView.setVisibility(View.INVISIBLE);
        mActivityMainBinding.place.setVisibility(View.INVISIBLE);
    }

    private void showContent() {
        mActivityMainBinding.btnCheckPermissions.setVisibility(View.GONE);
        mActivityMainBinding.txtError.setVisibility(View.INVISIBLE);
        mActivityMainBinding.mapView.setVisibility(View.VISIBLE);
        mActivityMainBinding.place.setVisibility(View.VISIBLE);
    }

    private void checkPermissionsButton() {
        mActivityMainBinding.btnCheckPermissions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionUtils.requestPermission(MainActivity.this);
            }
        });
    }

    private void initializeMap() {
        FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.mapView, mMapFragment);
        mFragmentTransaction.commit();
    }

    private void initializePlaceAutoComplete() {
        PlaceAutocompleteFragment mPlaceAutoCompleteFragment = PlaceAutoCompleteFactory.create(this);
        mMainPresenter.addAutoCompleteListener(mPlaceAutoCompleteFragment);
    }

    @Override
    public void showToast(Throwable t) {
        String message = getResources().getString(R.string.auto_complete_error);
        if (t != null) message = t.getMessage();
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermission(boolean isGranted) {
        if (isGranted) {
            showContent();
            mMapFragment = new MapFragment();
            initializeMap();
            initializePlaceAutoComplete();
        }
    }

}
