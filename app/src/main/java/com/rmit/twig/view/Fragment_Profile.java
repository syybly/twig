package com.rmit.twig.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.rmit.twig.R;
import com.rmit.twig.controller.DataHolder;
import com.rmit.twig.model.Post;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.ArrayList;
import java.io.File;


public class Fragment_Profile extends Fragment {
    private CircleImageView userphoto;

    private TextView name;
    private Context context;
    private TextView type;
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;
    private ImageView image5;
    private ImageView image6;
    private ImageView image7;
    private ImageView image8;
    private RelativeLayout postLayout;
    private ArrayList<String> imageURI = new ArrayList<>();



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
        postLayout=view.findViewById(R.id.post_image);
        ArrayList<ImageView> imageViews= new ArrayList<ImageView>();

        image1 = view.findViewById(R.id.image1);
        image2 = view.findViewById(R.id.image2);
        image3 = view.findViewById(R.id.image3);
        image4 = view.findViewById(R.id.image4);
        image5 = view.findViewById(R.id.image5);
        image6 = view.findViewById(R.id.image6);
        image7 = view.findViewById(R.id.image7);
        image8 = view.findViewById(R.id.image8);

        imageViews.add(image1);
        imageViews.add(image2);
        imageViews.add(image3);
        imageViews.add(image4);
        imageViews.add(image5);
        imageViews.add(image6);
        imageViews.add(image7);
        imageViews.add(image8);

//        imageViews.add(image1);
//        imageViews.add(image2);
//        imageViews.add(image3);
//        imageViews.add(image4);
//        imageViews.add(image5);
//        imageViews.add(image6);
//        imageViews.add(image7);
//        imageViews.add(image8);


        for (Post p : DataHolder.posts) {

                if (p.getAuthor().equals(DataHolder.currentuser)&&(p.getImageurl()!=null)) {

                    imageURI.add(p.getImageurl());
                }
                }

        if(imageURI.size()<8) {

            for (int i = 0; i < imageURI.size(); i++) {
                Picasso.with(context)
                        .load(imageURI.get(i))
                        .placeholder(R.drawable.nophoto)
                        .error(R.drawable.nophoto)
                        .into(imageViews.get(i));
            }
        }else {
            for(int i = 0; i < 8; i++){
                Picasso.with(context)
                        .load(imageURI.get(i))
                        .placeholder(R.drawable.nophoto)
                        .error(R.drawable.nophoto)
                        .into(imageViews.get(i));
            }
        }
//        Picasso.with(context)
//                .load(imageURI.get(0))
//                .placeholder(R.drawable.nophoto)
//                .error(R.drawable.nophoto)
//                .into(image1);
//
//        Picasso.with(context)
//                .load(imageURI.get(1))
//                .placeholder(R.drawable.nophoto)
//                .error(R.drawable.nophoto)
//                .into(image2);
//
//        Picasso.with(context)
//                .load(imageURI.get(2))
//                .placeholder(R.drawable.nophoto)
//                .error(R.drawable.nophoto)
//                .into(image3);
//
//        Picasso.with(context)
//                .load(imageURI.get(3))
//                .placeholder(R.drawable.nophoto)
//                .error(R.drawable.nophoto)
//                .into(image4);
//
//        Picasso.with(context)
//                .load(imageURI.get(4))
//                .placeholder(R.drawable.nophoto)
//                .error(R.drawable.nophoto)
//                .into(image5);
//
//        Picasso.with(context)
//                .load(imageURI.get(5))
//                .placeholder(R.drawable.nophoto)
//                .error(R.drawable.nophoto)
//                .into(image6);
//
//        Picasso.with(context)
//                .load(imageURI.get(6))
//                .placeholder(R.drawable.nophoto)
//                .error(R.drawable.nophoto)
//                .into(image7);
//
//        Picasso.with(context)
//                .load(imageURI.get(7))
//                .placeholder(R.drawable.nophoto)
//                .error(R.drawable.nophoto)
//                .into(image8);






//        Bitmap bm1 = BitmapFactory.decodeFile(imageURI.get(0));
//        image1.setImageBitmap(bm1);
//        Bitmap bm2 = BitmapFactory.decodeFile(imageURI.get(1));
//        image2.setImageBitmap(bm2);
//        Bitmap bm3 = BitmapFactory.decodeFile(imageURI.get(2));
//        image3.setImageBitmap(bm3);
//        Bitmap bm4 = BitmapFactory.decodeFile(imageURI.get(3));
//        image4.setImageBitmap(bm4);
//        Bitmap bm5 = BitmapFactory.decodeFile(imageURI.get(4));
//        image5.setImageBitmap(bm5);
//        Bitmap bm6 = BitmapFactory.decodeFile(imageURI.get(5));
//        image6.setImageBitmap(bm6);
//        Bitmap bm7 = BitmapFactory.decodeFile(imageURI.get(6));
//        image7.setImageBitmap(bm7);
//        Bitmap bm8 = BitmapFactory.decodeFile(imageURI.get(7));
//        image8.setImageBitmap(bm8);



//         Uri uri1= Uri.fromFile(new File(imageURI.get(0)));
//         image1.setImageURI(uri1);
//
//         Uri uri2= Uri.fromFile(new File(imageURI.get(1)));
//         image2.setImageURI(uri2);
//         Uri uri3= Uri.fromFile(new File(imageURI.get(2)));
//         image3.setImageURI(uri3);
//         Uri uri4= Uri.fromFile(new File(imageURI.get(3)));
//         image4.setImageURI(uri4);
//         Uri uri5=Uri.fromFile(new File(imageURI.get(4)));
//         image5.setImageURI(uri5);
//         Uri uri6= Uri.fromFile(new File(imageURI.get(5)));
//         image6.setImageURI(uri6);
//         Uri uri7= Uri.fromFile(new File(imageURI.get(6)));
//         image7.setImageURI(uri7);
//         Uri uri8= Uri.fromFile(new File(imageURI.get(7)));
//         image8.setImageURI(uri8);

        return view;

            }

        }







