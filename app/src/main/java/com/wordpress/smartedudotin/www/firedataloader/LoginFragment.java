package com.wordpress.smartedudotin.www.firedataloader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LoginFragment extends Fragment {
    private static final String TAG = "LoginFragment" ;
    private FirebaseAuth mAuth;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment ();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mAuth = FirebaseAuth.getInstance ();
        return inflater.inflate (R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);
        final NavController navController = Navigation.findNavController (view);

        final EditText emailEdit = view.findViewById (R.id.login_email_edit_txt);
        final EditText passwordEdit = view.findViewById (R.id.login_password_edit_txt);

        Button loginBtn = view.findViewById (R.id.login_btn);
        Button toSignUpBtn = view.findViewById (R.id.to_create_account_btn);

        loginBtn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                LoginMethod (emailEdit,passwordEdit);
            }
        });


        toSignUpBtn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                navController.navigate (R.id.action_loginFragment_to_signUpFragment);
            }
        });

    }

    public void LoginMethod(EditText email, EditText password){

//        Toast.makeText (getActivity (), ""+email.getText ().toString () + ", "+password.getText ().toString (), Toast.LENGTH_SHORT).show ();
        mAuth.signInWithEmailAndPassword (email.getText ().toString (), password.getText ().toString ())
                .addOnCompleteListener(getActivity (), new OnCompleteListener<AuthResult> () {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText (getContext (), "Authentication failed.", Toast.LENGTH_SHORT).show ();
                            updateUI(null);
                        }

                        // ...
                    }
                });

    }

    private void updateUI(FirebaseUser user) {
      if (user!=null){  startActivity (new Intent (getContext (),MainActivity.class));}
    }
}