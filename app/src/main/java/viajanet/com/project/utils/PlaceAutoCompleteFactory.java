package viajanet.com.project.utils;

import android.app.Activity;

import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;

import viajanet.com.project.R;

/**
 * Created by Rafael Magalhães on 30/01/18.
 */

public class PlaceAutoCompleteFactory {

    public static PlaceAutocompleteFragment create(Activity activity) {
        return (PlaceAutocompleteFragment) activity.getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
    }

}

