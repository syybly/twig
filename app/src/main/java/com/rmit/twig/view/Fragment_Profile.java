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
    private CircleImageView userphoto;

    private TextView name;
    private Context context;
    private RelativeLayout myPost;
    private TextView type;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        userphoto=view.findViewById(R.id.feed_userphoto);
        Picasso.with(context)
                .load(DataHolder.users.get(DataHolder.currentuser).getPhotourl())
                .placeholder(R.drawable.nophoto)
                .error(R.drawable.nophoto)
                .into(userphoto);

        name=view.findViewById(R.id.feed_name);
        name.setText(DataHolder.users.get(DataHolder.currentuser).getFullname());
        type=view.findViewById(R.id.type);
        type.setText(DataHolder.users.get(DataHolder.currentuser).getType());


        return view;




    }
}
