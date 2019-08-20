package com.rmit.twig.View;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.Menu;

import com.rmit.twig.Model.Post;
import com.rmit.twig.R;

public class HomepageActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private ImageView imageView;
    private TextView textview;
    private Context context;
    private Menu Menu;
    private BottomNavigationView navView;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    SwitchToHomepage();

                    return true;
                case R.id.navigation_explore:

                    SwitchToExplore();

                    return true;
                case R.id.navigation_addnew:


                    SwitchToAddNew();

                    return true;
                case R.id.navigation_chats:


                    SwitchToChats();

                    return true;
                case R.id.navigation_profile:

                    SwitchToProfile();

                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        navView = findViewById(R.id.nav_view);

        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.setItemIconTintList(null);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.homepage_container, new Fragment_Homepage()).commit();
    }


    public void SwitchToHomepage(){
        FragmentManager manager = getSupportFragmentManager();

        manager.beginTransaction().replace(R.id.homepage_container, new Fragment_Homepage()).commit();
    }

    public void SwitchToExplore(){

        FragmentManager manager = getSupportFragmentManager();

        manager.beginTransaction().replace(R.id.homepage_container, new Fragment_Explore()).commit();
    }

    public void SwitchToAddNew(){

        FragmentManager manager = getSupportFragmentManager();

        manager.beginTransaction().replace(R.id.homepage_container, new Fragment_AddNew()).commit();
    }

    public void SwitchToChats(){

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.homepage_container, new Fragment_Chats()).commit();
    }

    public void SwitchToProfile(){

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.homepage_container, new Fragment_Profile()).commit();
    }




}
