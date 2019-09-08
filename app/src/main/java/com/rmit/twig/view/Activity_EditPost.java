package com.rmit.twig.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rmit.twig.R;
import com.rmit.twig.controller.ClickListener_EditPost;
import com.rmit.twig.controller.DataHolder;
import com.rmit.twig.model.Post;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;


public class Activity_EditPost extends AppCompatActivity {
    private Activity activity;
    public RelativeLayout categorylayout;
    public static HashSet<String> categories;
    private ImageView userphoto;
    private TextView name;
    private TextView postContent;
    public static TextView location;
    private ImageButton addimage;
    private ImageView postaddimage1;
    private ImageView postaddimage2;
    private ImageView postaddimage3;
    private File photoFile = null;
    public static TextView cats;
    private TextView edit;
    private Post p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);
        activity = this;

        String postID = getIntent().getStringExtra("postID");

        for (Post post : DataHolder.posts) {
            if (postID.equals(post.getPostID())) {
                p = post;
            }
        }

        name = findViewById(R.id.feed_name);
        name.setText(DataHolder.users.get(DataHolder.currentuser).getFullname());

        postContent = findViewById(R.id.editpostcontent);
        postContent.setText(p.getContent());

        userphoto = findViewById(R.id.feed_userphoto);
        Picasso.with(this)
                .load(p.getUser().getPhotourl())
                .placeholder(R.drawable.nophoto)
                .error(R.drawable.nophoto)
                .into(userphoto);

        categories = p.getCategories();
        categorylayout = findViewById(R.id.addcategory);
        edit = findViewById(R.id.edit);
        final Post finalP = p;
        categorylayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent catintent = new Intent(Activity_EditPost.this, Activity_PostCategory.class);
                catintent.putExtra("postID", finalP.getPostID());
                startActivity(catintent);
            }
        });

        cats = findViewById(R.id.cats);

        String catsText = "";
        for (String s : p.getCategories()) {
            catsText = catsText + "#" + s + "   ";
        }

        cats.setText(catsText);

        location = findViewById(R.id.feed_location);
        location.setText(p.getLocation());

        edit.setOnClickListener(new ClickListener_EditPost(activity, p));

        TextView cancelButton = findViewById(R.id.cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity_EditPost.this.finish();
            }
        });

        addimage = findViewById(R.id.addimage);
        postaddimage1 = findViewById(R.id.addpostimage1);
        postaddimage2 = findViewById(R.id.addpostimage2);
        postaddimage3 = findViewById(R.id.addpostimage3);

        ArrayList<ImageView> postImageViews = new ArrayList<ImageView>();
        postImageViews.add(postaddimage1);
        postImageViews.add(postaddimage2);
        postImageViews.add(postaddimage3);

        ArrayList<String> imageUrls = p.getImageurl();

        for (int i = 0; i < imageUrls.size(); i++) {
            Picasso.with(this)
                    .load(imageUrls.get(i))
                    .placeholder(R.drawable.nophoto)
                    .error(R.drawable.nophoto)
                    .into(postImageViews.get(i));
            postImageViews.get(i).setVisibility(View.VISIBLE);
        }


        final String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu imagepop = new PopupMenu(activity, v);
                imagepop.getMenuInflater().inflate(R.menu.imagemenu, imagepop.getMenu());
                imagepop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.choosefromalbum:

                                if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                    ActivityCompat.requestPermissions(activity, PERMISSIONS, 113);
                                } else {
                                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                    intent.setType("image/*");
                                    startActivityForResult(Intent.createChooser(intent, "Complete action using"), 1);
                                }
                                break;

                            case R.id.camera:
                                if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, 114);
                                } else {
                                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                    try {
                                        photoFile = createImageFile();
                                    } catch (IOException ex) {

                                    }
                                    Uri photoURI = FileProvider.getUriForFile(activity,
                                            "com.example.android.fileprovider",
                                            photoFile);
                                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                                    startActivityForResult(cameraIntent, 2);
                                }
                        }
                        return false;
                    }
                });
                imagepop.show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check which request we're responding to
        int imagenum = p.getNewpostimages().size() + p.getImageurl().size();
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                Uri imageUri = data.getData();
                String imagepath = imageUri.getPath();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(imageUri,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imagepath = cursor.getString(columnIndex);
                cursor.close();
                File imagefile = new File(imagepath);
                p.getNewpostimages().add(imagefile);

                switch (imagenum) {
                    case 0:
                        postaddimage1.setVisibility(View.VISIBLE);
                        postaddimage1.setImageURI(data.getData());
                        break;
                    case 1:
                        postaddimage2.setVisibility(View.VISIBLE);
                        postaddimage2.setImageURI(data.getData());
                        break;
                    case 2:
                        postaddimage3.setVisibility(View.VISIBLE);
                        postaddimage3.setImageURI(data.getData());
                        break;
                }
            }
        }

        if (requestCode == 2 && resultCode == RESULT_OK) {
            Bitmap imageBitmap = BitmapFactory.decodeFile(photoFile.getPath());
            p.getNewpostimages().add(photoFile);
            switch (imagenum) {
                case 0:
                    postaddimage1.setVisibility(View.VISIBLE);
                    postaddimage1.setImageBitmap(imageBitmap);
                    break;
                case 1:
                    postaddimage2.setVisibility(View.VISIBLE);
                    postaddimage2.setImageBitmap(imageBitmap);
                    break;
                case 2:
                    postaddimage3.setVisibility(View.VISIBLE);
                    postaddimage3.setImageBitmap(imageBitmap);
                    break;
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".png",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        return image;
    }

}