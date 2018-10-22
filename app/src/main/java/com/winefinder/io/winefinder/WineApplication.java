package com.winefinder.io.winefinder;

import com.winefinder.io.winefinder.di.ApplicationComponent;
import com.winefinder.io.winefinder.di.DaggerApplicationComponent;
import com.winefinder.io.winefinder.di.NetModule;

public class WineApplication extends android.app.Application {

    private ApplicationComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerApplicationComponent.builder()
                .netModule(new NetModule())
                .build();
    }

    public ApplicationComponent getAppComponent(){
        return appComponent;
    }
}
