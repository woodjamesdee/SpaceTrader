package edu.jumpstreet.spacetrader.viewmodel;

import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

/**
 * creates solar system viewmodel
 */
public class SolarSystemViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public SolarSystemViewModel create(@NonNull Class modelClass) {
        return new SolarSystemViewModel();
    }
}
