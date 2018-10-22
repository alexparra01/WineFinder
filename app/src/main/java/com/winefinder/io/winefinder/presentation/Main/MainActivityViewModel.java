package com.winefinder.io.winefinder.presentation.Main;

import android.arch.lifecycle.ViewModel;
import android.support.v4.app.FragmentManager;

import com.google.firebase.auth.FirebaseAuth;
import com.winefinder.io.winefinder.R;
import com.winefinder.io.winefinder.presentation.home.HomeFragment;
import com.winefinder.io.winefinder.presentation.login.LoginFragment;

public class MainActivityViewModel extends ViewModel {


    public boolean isUserAuthenticated(FragmentManager fragmentManager, FirebaseAuth auth)
    {
        if(auth.getCurrentUser() != null){

            HomeFragment homeFragment = new HomeFragment();

            fragmentManager.beginTransaction()
                    .add(R.id.fragmentContainer,homeFragment).commit();

            return true;
        }else{

            LoginFragment loginFragment = new LoginFragment();

            fragmentManager.beginTransaction()
                    .add(R.id.fragmentContainer,loginFragment).commit();
            return false;
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
