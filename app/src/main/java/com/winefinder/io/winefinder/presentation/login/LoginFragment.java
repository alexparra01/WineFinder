package com.winefinder.io.winefinder.presentation.login;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.winefinder.io.winefinder.R;
import com.winefinder.io.winefinder.WineApplication;
import com.winefinder.io.winefinder.presentation.Main.MainActivity;
import com.winefinder.io.winefinder.presentation.home.HomeFragment;

import java.util.Objects;

import javax.inject.Inject;

public class LoginFragment extends Fragment implements View.OnClickListener, FirebaseAuth.AuthStateListener {

    private LoginViewModel loginViewModel;
    private EditText usernameEditText;
    private EditText passwordEditTex;

    @Inject
    FirebaseAuth auth;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((WineApplication)getActivity().getApplication()).getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        usernameEditText = view.findViewById(R.id.username);
        passwordEditTex = view.findViewById(R.id.password);

        String user = usernameEditText.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        String pass = passwordEditTex.getText().toString().trim();

        //validation

        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(!user.matches(emailPattern) &&  s.length()>0){
                    Snackbar.make(Objects.requireNonNull(getView()),getResources().getString(R.string.ValidUserName),Snackbar.LENGTH_LONG)
                            .show();
                }

            }
        });

        passwordEditTex.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(pass.getBytes().length > 8)
                    Snackbar.make(Objects.requireNonNull(getView()),getResources().getString(R.string.ValidPassword),Snackbar.LENGTH_LONG)
                            .show();

            }
        });

        Button loginButton = view.findViewById(R.id.loginbutton);
        loginButton.setOnClickListener(this);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
    }

    @Override
    public void onClick(View v) {

        if(!usernameEditText.getText().toString().isEmpty() && !passwordEditTex.getText().toString().isEmpty()){

            loginViewModel.signIn(auth,usernameEditText.getText().toString(),passwordEditTex.getText().toString())
                    .addOnCompleteListener(task -> {
                        if(!task.isSuccessful()){
                            Snackbar.make(Objects.requireNonNull(getView()),task.getException().getMessage(),Snackbar.LENGTH_LONG)
                                    .show();
                        }else{

                            LoginFragment loginFragment = new LoginFragment();

                            getFragmentManager().beginTransaction()
                                    .remove(loginFragment).commit();

                            HomeFragment homeFragment = new HomeFragment();
                            getFragmentManager().beginTransaction()
                                    .replace(R.id.loginContainer,homeFragment).commitAllowingStateLoss();
                            getFragmentManager().beginTransaction().addToBackStack(null);
                        }
                    });
        }
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

        if(firebaseAuth.getCurrentUser().isEmailVerified()){
            HomeFragment homeFragment = new HomeFragment();

            getActivity().getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer,homeFragment).commit();
        }else {
            Snackbar.make(Objects.requireNonNull(getView()),getResources().getString(R.string.ConfirmUser),Snackbar.LENGTH_LONG)
                    .show();
        }

    }
}
