package com.winefinder.io.winefinder.di;

import com.winefinder.io.winefinder.presentation.Main.MainActivity;
import com.winefinder.io.winefinder.presentation.login.LoginFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NetModule.class})
public interface ApplicationComponent {

    void inject(MainActivity activity);
    void inject(LoginFragment fragment);

}
