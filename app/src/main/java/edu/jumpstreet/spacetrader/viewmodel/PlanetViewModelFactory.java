package edu.jumpstreet.spacetrader.viewmodel;

import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

/**
 * creates view model for planet
 */
public class PlanetViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public PlanetViewModel create(@NonNull Class modelClass) {
        return new PlanetViewModel();
    }
}
