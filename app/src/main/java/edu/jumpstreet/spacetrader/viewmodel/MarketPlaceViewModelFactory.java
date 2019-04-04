package edu.jumpstreet.spacetrader.viewmodel;

import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

/**
 * Allows for the creation of a MarketPlaceViewModel.
 */
public class MarketPlaceViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public MarketPlaceViewModel create(@NonNull Class modelClass) {
        return new MarketPlaceViewModel();
    }
}
