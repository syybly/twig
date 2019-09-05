package com.rmit.twig.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.rmit.twig.R;

public class TabAdapter extends FragmentStatePagerAdapter {
    private Context context;
    TabAdapter(Context context,FragmentManager fm) {
        super(fm);
        this.context=context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new LoginFragment();
        }
        else{
            return new SignupFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return context.getString(R.string.Login);
            case 1:
                return context.getString(R.string.SignUp);
            default:
                return null;
        }
    }
}
