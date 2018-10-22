package com.winefinder.io.winefinder.di;

import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NetModule {

    @Provides
    @Singleton
    FirebaseAuth ProvideFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }
}
