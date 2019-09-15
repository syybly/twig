package com.rmit.twig.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.rmit.twig.controller.DataHolder;
import com.rmit.twig.R;

public class LoginSignupContainer extends AppCompatActivity {
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signinup_container);
        DataHolder dataHolder=new DataHolder();
        TabLayout tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewPager);
        TabAdapter tabAdapter = new TabAdapter(this,getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        DataHolder.posts.clear();
        DataHolder.bookmarks.clear();
        DataHolder.users.clear();
    }

    public void switchToHomepahe() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.homepage_container, new Fragment_Homepage()).commit();
    }
}
