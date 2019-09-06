package com.rmit.twig.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.content.Intent;



import com.rmit.twig.controller.DataHolder;
import com.rmit.twig.model.EventPost;
import com.rmit.twig.model.Post;
import com.rmit.twig.R;
import com.squareup.picasso.Picasso;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
    private TextView eventtime;
    private TextView hourago;
    private TextView feedcat;
    private LinearLayout postaction;
    private Button going;
    private Button decidelater;


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
        eventtime=convertView.findViewById(R.id.eventtime);
        hourago=convertView.findViewById(R.id.hourago);
        feedcat=convertView.findViewById(R.id.feedcat);
        String cats="";
        for(String s:feed.getCategories()){
            cats=cats+"#"+s+"   ";
        }
        feedcat.setText(cats);
        Timestamp current = new Timestamp(System.currentTimeMillis());
        long currenttime=current.getTime();
        double creatediff=(double)(currenttime-feed.getCreatetime())/ (60 * 60 * 1000);
        int sethourago=(int)Math.round(creatediff);
        String houragotext=sethourago+" hour(s) ago";
        if(sethourago>24){
            sethourago=(int)Math.round(sethourago/24);
            houragotext=sethourago+" day(s) ago";
        }
        hourago.setText(houragotext);
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
        decidelater=convertView.findViewById(R.id.decidelater);
        going=convertView.findViewById(R.id.going);
        if (feed.getType().equals("event")){
            eventbuttons.setVisibility(View.VISIBLE);
            eventtime.setVisibility(View.VISIBLE);
            going.setVisibility(View.VISIBLE);
            decidelater.setVisibility(View.VISIBLE);
            Timestamp timestamp=new Timestamp(feed.getDate());
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy hh:mm a");
            eventtime.setText(simpleDateFormat.format(timestamp));
        }
        feedimage=convertView.findViewById(R.id.feed_image);
        postaction=convertView.findViewById(R.id.post_action);
        if(feed.getImageurl().size()>0){
            Picasso.with(context)
                    .load(feed.getImageurl().get(0))
                    .placeholder(R.drawable.noimage)
                    .error(R.drawable.noimage)
                    .fit().centerCrop()
                    .into(feedimage);
        }
        if(feed.getImageurl().size()==0){
            ((RelativeLayout.LayoutParams) content.getLayoutParams()).addRule(RelativeLayout.BELOW, R.id.feed_head);
            ((RelativeLayout.LayoutParams) postaction.getLayoutParams()).addRule(RelativeLayout.BELOW, R.id.feedcat);
            ((RelativeLayout.LayoutParams) eventbuttons.getLayoutParams()).addRule(RelativeLayout.BELOW, R.id.post_action);
            feedimage.setVisibility(View.INVISIBLE);
            ViewGroup.MarginLayoutParams params =
                    (ViewGroup.MarginLayoutParams)content.getLayoutParams();
            params.setMargins(params.leftMargin,40,params.rightMargin,params.bottomMargin);
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

                                break;
                            case R.id.delete:

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
