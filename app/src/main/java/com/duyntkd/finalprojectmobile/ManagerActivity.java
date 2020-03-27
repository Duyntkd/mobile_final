package com.duyntkd.finalprojectmobile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.duyntkd.finalprojectmobile.fragments.PendingTaskManagementFragment;
import com.duyntkd.finalprojectmobile.fragments.ProfileFragment;
import com.duyntkd.finalprojectmobile.fragments.manager.ManageGroupTaskFragment;
import com.google.android.material.tabs.TabLayout;

public class ManagerActivity extends AbstractUserActivity {

    public int getGroupId() {
        return groupId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        userId = this.getIntent().getExtras().getInt(LoginActivity.USER_ID_TEXT);
        role = this.getIntent().getExtras().getString(LoginActivity.USER_ROLE_TEXT);
        groupId = this.getIntent().getExtras().getInt(LoginActivity.USER_GROUP_ID_TEXT);

        ManagerPagerAdapter employeePagerAdapter = new ManagerPagerAdapter(this.getSupportFragmentManager());
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(employeePagerAdapter);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private class ManagerPagerAdapter extends FragmentPagerAdapter {


        public ManagerPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new ManageGroupTaskFragment();
                case 1:
                    return new PendingTaskManagementFragment();
                case 2:
                    return new ProfileFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Manage group task";
                case 1:
                    return "Manage pending task";
                case 2:
                    return "Profile";
            }
            return super.getPageTitle(position);
        }
    }
}
