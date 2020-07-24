package com.wordpress.smartedudotin.www.firedataloader;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VideoFragment extends Fragment {

    public VideoFragment() {
        // Required empty public constructor
    }


    public static VideoFragment newInstance(String param1, String param2) {
        VideoFragment fragment = new VideoFragment ();
        Bundle args = new Bundle ();

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
        return inflater.inflate (R.layout.fragment_video, container, false);
    }

    ArrayList<Video> videoArrayList;
    VideoViewAdapter videoViewAdapter;
    GridView gridView;
    Video video;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance ();
        DatabaseReference databaseReference = firebaseDatabase.getReference ("videos");


        videoArrayList = new ArrayList<Video> ();

        videoViewAdapter = new VideoViewAdapter (getContext (),R.layout.video_layout,videoArrayList);

        gridView = view.findViewById (R.id.video_fragment_view);

        databaseReference.addValueEventListener (new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Toast.makeText (getContext (), "Video Loading...", Toast.LENGTH_SHORT).show ();

                for (DataSnapshot ds : snapshot.getChildren ()){

                    video = new Video (ds.child ("videoName").getValue (),ds.child ("videoUrl").getValue ());
//                    video = new Video (ds.child ("videoName").getValue (),"");

                    videoArrayList.add (video);
                }
                gridView.setAdapter (videoViewAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}