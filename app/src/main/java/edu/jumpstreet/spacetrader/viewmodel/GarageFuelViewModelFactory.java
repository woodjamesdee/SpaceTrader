package edu.jumpstreet.spacetrader.viewmodel;

import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class GarageFuelViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public GarageFuelViewModel create(@NonNull Class modelClass) {
        return new GarageFuelViewModel();
    }
}
