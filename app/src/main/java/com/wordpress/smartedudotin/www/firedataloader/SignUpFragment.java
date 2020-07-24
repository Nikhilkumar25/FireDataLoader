package com.wordpress.smartedudotin.www.firedataloader;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
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

public class SignUpFragment extends Fragment {


    private static final String TAG ="SignUpFragment" ;
    private FirebaseAuth mAuth;

    public SignUpFragment() {
        // Required empty public constructor
    }

    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment ();
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
        return inflater.inflate (R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);

        mAuth = FirebaseAuth.getInstance ();
        final NavController navController = Navigation.findNavController (view);
        Button toLoginBtn = view.findViewById (R.id.to_login_account_btn);

        final EditText getEmailEdit = view.findViewById (R.id.signup_email_edit_txt);
        final EditText getPasswordEdit = view.findViewById (R.id.signup_password_edit_txt);

        Button signUpBtn = view.findViewById (R.id.signup_btn);
        signUpBtn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                SignUpMethod (getEmailEdit,getPasswordEdit);
            }
        });

        toLoginBtn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                NavOptions navOptions = new NavOptions.Builder ().setPopUpTo (R.id.loginFragment, true).build ();
                navController.navigate (R.id.action_signUpFragment_to_loginFragment, null, navOptions);
            }
        });
    }

    public void SignUpMethod(EditText email, EditText password){

//        Toast.makeText (getActivity (), ""+email.getText ().toString () + ", "+password.getText ().toString (), Toast.LENGTH_SHORT).show ();
        mAuth.createUserWithEmailAndPassword(email.getText ().toString (), password.getText ().toString ())
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