package viajanet.com.project.utils;



import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by Rafael Magalhães on 02/02/18.
 */

public class ObservableFactory {

    private static final ArrayList<PlaceObservable> mPlaceObservables = new ArrayList<>();

    public static void addObservable(PlaceObservable placeObservable) {

        mPlaceObservables.add(placeObservable);
    }

    public static void removeObservable(PlaceObservable placeObservable) {
        mPlaceObservables.remove(placeObservable);
    }

    public static void notifyForLocalChanges(LatLng latLng) {
        for (PlaceObservable observable: mPlaceObservables) {
            observable.onChangeLocal(latLng);
        }
    }
}
