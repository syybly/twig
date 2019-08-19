package com.rmit.twig.View;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.rmit.twig.Model.Post;
import com.rmit.twig.R;

public class HomepageActivity extends AppCompatActivity {
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    SwitchToHomepage();
                    return true;
                case R.id.navigation_dashboard:
                    SwitchToProfile();
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.homepage_container, new Fragment_Homepage()).commit();
    }

    public void SwitchToProfile(){
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.homepage_container, new Fragment_Profile()).commit();
    }

    public void SwitchToHomepage(){
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.homepage_container, new Fragment_Homepage()).commit();
    }

}
