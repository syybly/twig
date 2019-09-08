package com.rmit.twig.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.rmit.twig.R;
import com.rmit.twig.com.AsyncTask_GetUser;

public class Activity_Homepage extends AppCompatActivity {
    private BottomNavigationView navView;
    private Context context;
    private AppBarLayout appBarLayout;
    private TextView toolbartitle;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toolbartitle.setText("twig");
                    SwitchToHomepage();
                    toolbartitle.setText("twig");
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

                    return true;
                case R.id.navigation_chats:


                    SwitchToChats();

                    return true;
                case R.id.navigation_profile:
                    toolbartitle.setText("profile");
                    SwitchToProfile();

                    return true;

                    default:
                        SwitchToHomepage();
                        return true;

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        appBarLayout=findViewById(R.id.app_bar);
        toolbartitle=findViewById(R.id.toolbartitle);
        toolbartitle.setText("twig");
        context=this;
        navView = findViewById(R.id.nav_view);
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
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

    public void SwitchToChats(){

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.homepage_container, new Fragment_Chats()).commit();
    }

    public void SwitchToProfile(){

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.homepage_container, new Fragment_Profile()).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
