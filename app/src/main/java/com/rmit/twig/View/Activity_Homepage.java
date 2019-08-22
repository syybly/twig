package com.rmit.twig.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.Menu;

import com.rmit.twig.Model.Post;
import com.rmit.twig.R;

public class Activity_Homepage extends AppCompatActivity {
    private BottomNavigationView navView;
    private Context context;
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
                    View v = findViewById(R.id.navigation_addnew);
                    PopupMenu pm = new PopupMenu(context, v);
                    pm.getMenuInflater().inflate(R.menu.create_menu, pm.getMenu());
                    pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId())   {
                                case R.id.createpost:
                                    Intent genralpost=new Intent(context,Activity_CreateGenralPost.class);
                                    startActivity(genralpost);
                                    break;
                                case R.id.createevent:
                                    Intent event=new Intent(context,Activity_CreateEvent.class);
                                    startActivity(event);
                                    break;
                                case R.id.createactivity:
                                    Intent oppo=new Intent(context,Activity_CreateOppo.class);
                                    startActivity(oppo);
                                    break;
                                default:
                                    break;
                            }
                            return true;
                        }
                    });
                    pm.show();
//                    SwitchToAddNew();

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
        context=this;
        navView = findViewById(R.id.nav_view);
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
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
