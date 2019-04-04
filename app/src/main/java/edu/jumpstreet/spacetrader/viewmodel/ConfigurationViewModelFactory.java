package edu.jumpstreet.spacetrader.viewmodel;

import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

/**
 * Allows for the creation of the ConfigurationViewModel
 */
public class ConfigurationViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    public ConfigurationViewModel create(@NonNull Class modelClass) {
        return new ConfigurationViewModel();
    }
}
