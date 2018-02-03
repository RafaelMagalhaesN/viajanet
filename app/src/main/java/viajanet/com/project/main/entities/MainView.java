package viajanet.com.project.main.entities;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Rafael Magalhães on 02/02/18.
 */

public interface MainView {
    void showToast(Throwable t);

    void onPermission(boolean isGranted);
}
