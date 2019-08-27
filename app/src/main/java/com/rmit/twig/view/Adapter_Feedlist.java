package com.rmit.twig.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rmit.twig.model.EventPost;
import com.rmit.twig.model.Post;
import com.rmit.twig.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_Feedlist extends ArrayAdapter<Post> {
    private Context context;
    private ArrayList<Post> posts;
    private ImageView image;
    private TextView name;
    private TextView location;
    private TextView content;
    private LinearLayout eventbuttons;
    private ImageView feedimage;


    public Adapter_Feedlist(Context context, ArrayList<Post> posts) {
        super(context, 0,posts);
        this.context=context;
        this.posts=posts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Post feed=posts.get(position);
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.feedlist_items, parent, false);
        }
        image=convertView.findViewById(R.id.feed_userphoto);
        Picasso.with(context)
                .load(feed.getUser().getPhotourl())
                .placeholder(R.drawable.nophoto)
                .error(R.drawable.nophoto)
                .into(image);
        name=convertView.findViewById(R.id.feed_name);
        name.setText(feed.getUser().getFullname());
        location=convertView.findViewById(R.id.feed_location);
        if(feed.getLocation()!=null) {
            location.setText(feed.getLocation());
        }
        content=convertView.findViewById(R.id.feed_content);
        content.setText(feed.getContent());
        eventbuttons=convertView.findViewById(R.id.eventbuttons);
        eventbuttons.setVisibility(View.INVISIBLE);
        if (feed instanceof EventPost){
            eventbuttons.setVisibility(View.VISIBLE);
        }
        feedimage=convertView.findViewById(R.id.feed_image);
        if(feed.getImageurl()!=null){
            Picasso.with(context)
                    .load(feed.getImageurl())
                    .placeholder(R.drawable.noimage)
                    .error(R.drawable.noimage)
                    .fit().centerCrop()
                    .into(feedimage);
        }
        return convertView;
    }
}
