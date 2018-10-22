package com.winefinder.io.winefinder.di;

import android.app.Application;
import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.winefinder.io.winefinder.WineApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final WineApplication wineApplication;


    public ApplicationModule(WineApplication wineApplication) {
        this.wineApplication = wineApplication;
    }

    @Provides
    @Singleton
    Application provideApplication(){
       return  wineApplication;
    }
}
