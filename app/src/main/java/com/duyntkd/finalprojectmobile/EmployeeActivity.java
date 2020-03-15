package com.duyntkd.finalprojectmobile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.duyntkd.finalprojectmobile.fragments.TaskManagementFragment;
import com.duyntkd.finalprojectmobile.fragments.employee.HistoryFragment;
import com.duyntkd.finalprojectmobile.fragments.employee.ManageCurrentTaskFragment;
import com.duyntkd.finalprojectmobile.fragments.ProfileFragment;
import com.google.android.material.tabs.TabLayout;

public class EmployeeActivity extends AbstractUserActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        userId = this.getIntent().getExtras().getInt(LoginActivity.USER_ID_TEXT);
        role = this.getIntent().getExtras().getString(LoginActivity.USER_ROLE_TEXT);
        groupId = this.getIntent().getExtras().getInt(LoginActivity.USER_GROUP_ID_TEXT);

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
                    return new ManageCurrentTaskFragment();
                case 1:
                    return new HistoryFragment();
                case 2:
                    return new TaskManagementFragment();
                case 3:
                    return new ProfileFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Current Tasks";
                case 1:
                    return "View History";
                case 2:
                    return "Self Tasks";
                case 3:
                    return "Profile";
            }
            return "";
        }
    }
}
