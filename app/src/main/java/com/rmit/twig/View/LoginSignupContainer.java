package com.rmit.twig.View;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.rmit.twig.R;

public class LoginSignupContainer extends AppCompatActivity {
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signinup_container);
        TabLayout tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewPager);
        TabAdapter tabAdapter = new TabAdapter(this,getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
