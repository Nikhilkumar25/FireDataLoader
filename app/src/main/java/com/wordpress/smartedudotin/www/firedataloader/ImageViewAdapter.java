package com.wordpress.smartedudotin.www.firedataloader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageViewAdapter extends ArrayAdapter<Image> {
    public ImageViewAdapter(@NonNull Context context, int resource, @NonNull List<Image> objects) {
        super (context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View imageListItem = convertView;
        if (imageListItem==null){
            imageListItem = LayoutInflater.from (getContext ()).inflate (R.layout.image_layout,parent,false);
        }

        final Image currentImage = getItem (position);

        TextView imageTitle = imageListItem.findViewById (R.id.image_title);
        imageTitle.setText (currentImage.getmImageTitle ());
        ImageView imageView = (ImageView) imageListItem.findViewById (R.id.imageView1);
        Picasso.get ().load (currentImage.getmImageUri ()).into (imageView);

        return imageListItem; }
}
