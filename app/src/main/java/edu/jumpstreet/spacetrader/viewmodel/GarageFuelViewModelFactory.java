package edu.jumpstreet.spacetrader.viewmodel;

import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

/**
 * Allows for the creation of a GarageFuelViewModel.
 */
public class GarageFuelViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public GarageFuelViewModel create(@NonNull Class modelClass) {
        return new GarageFuelViewModel();
    }
}
