package com.winefinder.io.winefinder.presentation.home;

import android.arch.lifecycle.ViewModel;

public class HomeActivityViewModel extends ViewModel {

    //TODO:Implement autentication service
    public boolean isUserAuthenticated(){return true;}

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
