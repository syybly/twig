package com.rmit.twig.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rmit.twig.Model.EventPost;
import com.rmit.twig.Model.Post;
import com.rmit.twig.R;
import com.squareup.picasso.Picasso;

public class FeedlistAdapter extends ArrayAdapter<Post> {
    private Context context;
    private Post[] posts;
    private ImageView image;
    private TextView name;
    private TextView location;
    private TextView content;
    private LinearLayout eventbuttons;


    public FeedlistAdapter(Context context, Post[] posts) {
        super(context, 0,posts);
        this.context=context;
        this.posts=posts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Post feed=posts[position];
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
        location.setText(feed.getLocation());
        content=convertView.findViewById(R.id.feed_content);
        content.setText(feed.getContent());
        eventbuttons=convertView.findViewById(R.id.eventbuttons);
        eventbuttons.setVisibility(View.INVISIBLE);
        if (feed instanceof EventPost){
            eventbuttons.setVisibility(View.VISIBLE);
        }
        return convertView;
    }
}
