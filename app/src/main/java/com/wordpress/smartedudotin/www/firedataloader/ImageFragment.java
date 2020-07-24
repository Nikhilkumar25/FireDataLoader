package com.wordpress.smartedudotin.www.firedataloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ImageFragment extends Fragment {

    Image image;
    ArrayList<Image> imageArrayList;
    GridView gridView;
    ImageViewAdapter imageViewAdapter;
    public ImageFragment() {
        // Required empty public constructor
    }


    public static ImageFragment newInstance(String param1, String param2) {
        ImageFragment fragment = new ImageFragment ();
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
        return inflater.inflate (R.layout.fragment_image, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);
        FirebaseStorage storage = FirebaseStorage.getInstance();


        FirebaseDatabase database = FirebaseDatabase.getInstance ();
        DatabaseReference databaseReference = database.getReference ("images");

        imageArrayList = new ArrayList<Image> ();

         imageViewAdapter= new ImageViewAdapter (getContext (), R.layout.image_layout,imageArrayList);

         gridView= view.findViewById (R.id.image_fragment_view);
        databaseReference.addValueEventListener (new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Toast.makeText (getContext (), "Loading Images", Toast.LENGTH_SHORT).show ();

                for (DataSnapshot ds : snapshot.getChildren ()){

                    image = new Image (ds.child ("imageName").getValue (),ds.child ("imageUrl").getValue ());
                    imageArrayList.add (image);

                }

                gridView.setAdapter (imageViewAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






    }



}