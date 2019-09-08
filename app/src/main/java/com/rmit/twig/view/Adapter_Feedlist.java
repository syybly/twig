package com.rmit.twig.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.content.Intent;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;


import com.rmit.twig.controller.DataHolder;
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
    private ImageButton deletebutton;
    private Post feed;



    public Adapter_Feedlist(Context context, ArrayList<Post> posts) {
        super(context, 0,posts);
        this.context=context;
        this.posts=posts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        feed=posts.get(position);
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
        deletebutton=convertView.findViewById(R.id.delete_edit);
        deletebutton.setVisibility(View.INVISIBLE);
        if(feed.getAuthor().equals(DataHolder.currentuser)){
            deletebutton.setVisibility(View.VISIBLE);
        }
        deletebutton.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(context, v);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.delete_edit_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit:
                                Intent editintent = new Intent(context, Activity_Edit.class);
                                editintent.putExtra("postID","postID");
                                context.startActivity(editintent);
                                break;
                            case R.id.delete:
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setTitle("delete post");
                                builder.setMessage("Are you sure you want to delete it?");

                                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(context,"Delete successfully",Toast.LENGTH_SHORT).show();

                                    }

                                });
                                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(context,"Cancel",Toast.LENGTH_SHORT).show();
                                    }
                                });
                                builder.show();
                                break;
                            default:
                                break;
                        }
                        return true;
                    }
                });

                popup.show();


            }
        });
        return convertView;

    }
}
