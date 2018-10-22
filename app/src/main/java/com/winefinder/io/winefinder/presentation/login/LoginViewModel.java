package com.winefinder.io.winefinder.presentation.login;

import android.arch.lifecycle.ViewModel;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginViewModel extends ViewModel {
    // TODO: Implement the ViewModel


    public Task<AuthResult> signIn(FirebaseAuth auth, String user, String password){

        return auth.signInWithEmailAndPassword(user,password);

    }


}
