package com.wordpress.smartedudotin.www.firedataloader;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.ArrayList;

public class VideoViewAdapter extends ArrayAdapter<Video> {

    private Button playBtn;
    public VideoViewAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Video> objects) {
        super (context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable final View convertView, @NonNull ViewGroup parent) {

        View videoViewList = convertView;
        if (videoViewList==null){
            videoViewList = LayoutInflater.from (getContext ()).inflate (R.layout.video_layout,parent,false);
        }

        final Video currentVideo = getItem (position);

        TextView textView = videoViewList.findViewById (R.id.video_title);
        assert currentVideo != null;
        textView.setText (currentVideo.getmVideoName ());

        final VideoView videoView = videoViewList.findViewById (R.id.video_view);
        Uri uri = Uri.parse (currentVideo.getmVideoUri ());
        videoView.setVideoURI (uri);

        videoView.pause ();
        playBtn = videoViewList.findViewById (R.id.play_btn);

        videoView.setOnPreparedListener (new MediaPlayer.OnPreparedListener () {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                Toast.makeText (getContext (), "ready", Toast.LENGTH_SHORT).show ();
            }
        });

        final boolean[] isPlaying = {false};
        playBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if (isPlaying[0]) {
                   videoView.pause();
                }else{
                    videoView.requestFocus ();
                    videoView.start ();
                }
                isPlaying[0] = !isPlaying[0];

            }
        });


        return videoViewList;
     }
}
