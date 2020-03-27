package com.duyntkd.finalprojectmobile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.duyntkd.finalprojectmobile.fragments.ProfileFragment;
import com.duyntkd.finalprojectmobile.fragments.admin.ManageGroupFragment;
import com.duyntkd.finalprojectmobile.fragments.admin.ManageUserFragment;
import com.google.android.material.tabs.TabLayout;

public class AdminActivity extends AbstractUserActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        userId = this.getIntent().getExtras().getInt(LoginActivity.USER_ID_TEXT);
        role = this.getIntent().getExtras().getString(LoginActivity.USER_ROLE_TEXT);
        groupId = this.getIntent().getExtras().getInt(LoginActivity.USER_GROUP_ID_TEXT);

        AdminPagerAdapter adminPagerAdapter = new AdminPagerAdapter(this.getSupportFragmentManager());

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(adminPagerAdapter);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private class AdminPagerAdapter extends FragmentPagerAdapter {


        public AdminPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: return new ManageUserFragment();
                case 2: return new ProfileFragment();
                case 1: return new ManageGroupFragment();
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
                case 0: return "Manage user";
                case 2: return "Profile";
                case 1: return "Manage group";
            }
            return super.getPageTitle(position);
        }
    }
}
