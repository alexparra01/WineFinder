package com.winefinder.io.winefinder.presentation.Main;

import android.arch.lifecycle.ViewModelProviders;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.winefinder.io.winefinder.R;
import com.winefinder.io.winefinder.WineApplication;
import com.winefinder.io.winefinder.presentation.home.HomeFragment;
import com.winefinder.io.winefinder.presentation.login.LoginFragment;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    FirebaseAuth auth;

    public BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        ((WineApplication) getApplication()).getAppComponent().inject(this);

        MainActivityViewModel viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        if(findViewById(R.id.fragmentContainer) != null){

            boolean isAuthenticated = viewModel.isUserAuthenticated(getSupportFragmentManager(), auth);

            if(!isAuthenticated) bottomNavigationView.setVisibility(View.INVISIBLE);
        }
    }


}
