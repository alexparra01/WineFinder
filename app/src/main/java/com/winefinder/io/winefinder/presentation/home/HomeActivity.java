package com.winefinder.io.winefinder.presentation.home;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.winefinder.io.winefinder.R;
import com.winefinder.io.winefinder.presentation.login.LoginFragment;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        HomeActivityViewModel viewModel = ViewModelProviders.of(this).get(HomeActivityViewModel.class);

        if(findViewById(R.id.fragmentContainer) != null){

            LoginFragment loginFragment = new LoginFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer,loginFragment).commit();
        }
    }
}
