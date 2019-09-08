package com.rmit.twig.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Transformers.BaseTransformer;
import com.rmit.twig.controller.DataHolder;
import com.rmit.twig.model.Post;
import com.rmit.twig.R;
import com.squareup.picasso.Picasso;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Adapter_Feedlist extends RecyclerView.Adapter<Adapter_Feedlist.GeneralViewHolder> {
    private Context context;
    private ArrayList<Post> posts;
    private Post feed;
    private final static int GENERAL=1;
    private final static int EVENT=2;
    private final static int IMAGE=3;
    private final static int NOIMAGE=4;


    public Adapter_Feedlist(Context context, ArrayList<Post> posts) {
        this.context=context;
        this.posts=posts;
    }

    public static class GeneralViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private ImageView image;
        private TextView name;
        private TextView location;
        private TextView content;
        private LinearLayout eventbuttons;
        private SliderLayout feedimage;
        private ImageButton deletebutton;
        private TextView eventtime;
        private TextView hourago;
        private TextView feedcat;
        private LinearLayout postaction;
        private Button going;
        private Button decidelater;
        private TextView title;
        public GeneralViewHolder(View convertView) {
            super(convertView);
            eventtime=convertView.findViewById(R.id.eventtime);
            hourago=convertView.findViewById(R.id.hourago);
            feedcat=convertView.findViewById(R.id.feedcat);
            image=convertView.findViewById(R.id.feed_userphoto);
            name=convertView.findViewById(R.id.feed_name);
            location=convertView.findViewById(R.id.feed_location);
            content=convertView.findViewById(R.id.feed_content);
            eventbuttons=convertView.findViewById(R.id.eventbuttons);
            decidelater=convertView.findViewById(R.id.decidelater);
            going=convertView.findViewById(R.id.going);
            feedimage=convertView.findViewById(R.id.feed_image);
            postaction=convertView.findViewById(R.id.post_action);
            deletebutton=convertView.findViewById(R.id.delete_edit);
            title=convertView.findViewById(R.id.titletext);
        }
    }

//    public static class EventViewHolder extends RecyclerView.ViewHolder {
//        // each data item is just a string in this case
//        private ImageView image;
//        private TextView name;
//        private TextView location;
//        private TextView content;
//        private LinearLayout eventbuttons;
//        private ImageView feedimage;
//        private ImageButton deletebutton;
//        private TextView eventtime;
//        private TextView hourago;
//        private TextView feedcat;
//        private LinearLayout postaction;
//        private Button going;
//        private Button decidelater;
//        public EventViewHolder(View convertView) {
//            super(convertView);
//            eventtime=convertView.findViewById(R.id.eventtime);
//            hourago=convertView.findViewById(R.id.hourago);
//            feedcat=convertView.findViewById(R.id.feedcat);
//            image=convertView.findViewById(R.id.feed_userphoto);
//            name=convertView.findViewById(R.id.feed_name);
//            location=convertView.findViewById(R.id.feed_location);
//            content=convertView.findViewById(R.id.feed_content);
//            eventbuttons=convertView.findViewById(R.id.eventbuttons);
//            decidelater=convertView.findViewById(R.id.decidelater);
//            going=convertView.findViewById(R.id.going);
//            feedimage=convertView.findViewById(R.id.feed_image);
//            postaction=convertView.findViewById(R.id.post_action);
//            deletebutton=convertView.findViewById(R.id.delete_edit);
//
//
//        }
//    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


    @NonNull
    @Override
    public GeneralViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.feedlist_items, parent, false);
        return new GeneralViewHolder(v);
//        switch (viewType){
//            case GENERAL:
//                return new GeneralViewHolder(v);
//            case EVENT:
//
//                default:
//                    return null;
//        }

    }

    @Override
    public void onBindViewHolder(@NonNull GeneralViewHolder holder, int position) {
        feed=posts.get(position);
        Picasso.with(context)
                .load(feed.getUser().getPhotourl())
                .placeholder(R.drawable.nophoto)
                .error(R.drawable.nophoto)
                .into(holder.image);
        holder.name.setText(feed.getUser().getFullname());
        if(feed.getLocation()!=null) {
            holder.location.setText(feed.getLocation());
        }
        holder.content.setText(feed.getContent());
        String cats="";
        for(String s:feed.getCategories()){
            cats=cats+"#"+s+"   ";
        }
        holder.feedcat.setText(cats);
        Timestamp current = new Timestamp(System.currentTimeMillis());
        long currenttime=current.getTime();
        double creatediff=(double)(currenttime-feed.getCreatetime())/ (60 * 60 * 1000);
        int sethourago=(int)Math.round(creatediff);
        String houragotext=sethourago+" hour(s) ago";
        if(sethourago>24){
            sethourago=(int)Math.round(sethourago/24);
            houragotext=sethourago+" day(s) ago";
        }
        holder.hourago.setText(houragotext);
        if(feed.getImageurl().size()>0){
            for(String url:feed.getImageurl()){
                DefaultSliderView textSliderView = new DefaultSliderView(context);
                textSliderView
                        .image(url);
                holder.feedimage.addSlider(textSliderView);
            }
            if(feed.getImageurl().size()==1){
                holder.feedimage.setPagerTransformer(false, new BaseTransformer() {
                    @Override
                    public void onTransform(View view, float position) {
                    }

                });
            }
            holder.feedimage.stopAutoCycle();
            holder.feedimage.setVisibility(View.VISIBLE);
        }
        if (feed.getType().equals("event")) {
            Timestamp timestamp = new Timestamp(feed.getDate());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
            holder.eventtime.setText(simpleDateFormat.format(timestamp));
            holder.eventbuttons.setVisibility(View.VISIBLE);
            holder.eventtime.setVisibility(View.VISIBLE);
            holder.going.setVisibility(View.VISIBLE);
            holder.decidelater.setVisibility(View.VISIBLE);
            holder.title.setText(feed.getTitle());
            holder.title.setVisibility(View.VISIBLE);
        }
        if(feed.getImageurl().size()==0){
            ((RelativeLayout.LayoutParams) holder.title.getLayoutParams()).addRule(RelativeLayout.BELOW, R.id.feed_head);
            ((RelativeLayout.LayoutParams) holder.postaction.getLayoutParams()).addRule(RelativeLayout.BELOW, R.id.feedcat);
            ((RelativeLayout.LayoutParams) holder.eventbuttons.getLayoutParams()).addRule(RelativeLayout.BELOW, R.id.post_action);
            ViewGroup.MarginLayoutParams params =
                    (ViewGroup.MarginLayoutParams)holder.content.getLayoutParams();
            params.setMargins(params.leftMargin,40,params.rightMargin,params.bottomMargin);
        }
        holder.deletebutton.setVisibility(View.INVISIBLE);
        if(feed.getAuthor().equals(DataHolder.currentuser)){
            holder.deletebutton.setVisibility(View.VISIBLE);
        }
        holder.deletebutton.setOnClickListener(new View.OnClickListener () {
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
//        switch (holder.getItemViewType()){
//            case GENERAL:
//                break;
//            case EVENT:

//        }

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }




}
