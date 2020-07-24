package com.wordpress.smartedudotin.www.firedataloader;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.facebook.login.LoginManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance ();

        Toolbar toolbar = findViewById (R.id.custom_toolbar_main);
        setSupportActionBar (toolbar);


        openFragment (ImageFragment.newInstance ("",""));
        BottomNavigationView bottomNavigationView = findViewById (R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener (new BottomNavigationView.OnNavigationItemSelectedListener () {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
             switch (item.getItemId ()){
                 case R.id.image_fragment_btn:
                     openFragment(ImageFragment.newInstance ("",""));
                     return true;

                 case R.id.video_fragment_btn:
                     openFragment (VideoFragment.newInstance ("",""));
                     return true;
             }
                return false;
            }
        });

        Toast.makeText (MainActivity.this, "Loading Content..", Toast.LENGTH_SHORT).show ();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater (this);
        menuInflater.inflate (R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId ()==R.id.logout_option){
            mAuth.signOut ();
            if (LoginManager.getInstance ()!=null){LoginManager.getInstance().logOut();}
            Toast.makeText (this, "LogOut complete", Toast.LENGTH_SHORT).show ();
            startActivity (new Intent (this,AuthActivity.class));
            return true;
        }else {  return false;}


    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}