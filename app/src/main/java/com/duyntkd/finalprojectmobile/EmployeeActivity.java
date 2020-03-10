package com.duyntkd.finalprojectmobile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.duyntkd.finalprojectmobile.fragments.employee.HistoryFragment;
import com.duyntkd.finalprojectmobile.fragments.employee.ManageTaskFragment;
import com.duyntkd.finalprojectmobile.fragments.ProfileFragment;
import com.google.android.material.tabs.TabLayout;

public class EmployeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        EmployeePagerAdapter employeePagerAdapter = new EmployeePagerAdapter(this.getSupportFragmentManager());
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(employeePagerAdapter);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private class EmployeePagerAdapter extends FragmentPagerAdapter {

        public EmployeePagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new ManageTaskFragment();
                case 1:
                    return new HistoryFragment();
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
                    return "Manage Tasks";
                case 1:
                    return "View History";
                case 2:
                    return "Profile";
            }
            return "";
        }
    }
}
