package edu.jumpstreet.spacetrader.viewmodel;

import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

/**
 * Allows for the creation of a GarageViewModel.
 */
public class GarageViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public GarageViewModel create(@NonNull Class modelClass) {
        return new GarageViewModel();
    }
}
