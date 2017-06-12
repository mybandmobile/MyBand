package com.myband.myband;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.myband.myband.fragment.EventFragment;
import com.myband.myband.fragment.ProfileFragment;
import com.myband.myband.model.User;

import org.parceler.Parcels;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    User user;

    public PagerAdapter(FragmentManager fm, int NumOfTabs, Bundle bundle) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        user = (User) Parcels.unwrap(bundle.getParcelable("user"));
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                //ProfileFragment tab1 = new ProfileFragment();
                ProfileFragment tab1 = ProfileFragment.newInstance(user);
                return tab1;
            case 1:
                EventFragment tab2 = new EventFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
