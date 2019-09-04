package com.rmit.twig.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rmit.twig.R;
import com.rmit.twig.controller.DataHolder;
import com.squareup.picasso.Picasso;


public class Fragment_Profile extends Fragment {

    private ImageView image;
    private TextView name;
    private Context context;
    private RelativeLayout myPost;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        image=view.findViewById(R.id.feed_userphoto);
        Picasso.with(context)
                .load(DataHolder.users.get(DataHolder.currentuser).getPhotourl())
                .placeholder(R.drawable.nophoto)
                .error(R.drawable.nophoto)
                .into(image);

        name=view.findViewById(R.id.feed_name);
        name.setText(DataHolder.users.get(DataHolder.currentuser).getFullname());
        myPost=view.findViewById(R.id.my_post);
        myPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent postIntent = new Intent(context, Activity_MyPost.class);
                postIntent.putExtra("userID", "userID");
                startActivity(postIntent);
            }
        });
        return view;




    }
}
