package edu.jumpstreet.spacetrader.viewmodel;

import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

/**
 * Allows for the creation of a TravelPopupViewModel.
 */
public class TravelPopupViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public TravelPopupViewModel create(@NonNull Class modelClass) {
        return new TravelPopupViewModel();
    }
}
