package edu.jumpstreet.spacetrader.viewmodel;

import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

/**
 * Allows for the creation of a MarketPlacePopupViewModel.
 */
public class MarketPlacePopupViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public MarketPlacePopupViewModel create(@NonNull Class modelClass) {
        return new MarketPlacePopupViewModel();
    }
}
