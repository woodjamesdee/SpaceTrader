package edu.jumpstreet.spacetrader.viewmodel;

import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

/**
 * Allows for the creation of a UniverseViewModel.
 */
public class UniverseViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public UniverseViewModel create(@NonNull Class modelClass) {
        return new UniverseViewModel();
    }
}
