package com.roundmelon.iamjosephvarghese.bharatham_2k18;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Schedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("CHAVARA HALL"));
        tabLayout.addTab(tabLayout.newTab().setText("GALLERY HALL"));
        tabLayout.addTab(tabLayout.newTab().setText("PAREEKSHA BHAVAN H1"));
        tabLayout.addTab(tabLayout.newTab().setText("PAREEKSHA BHAVAN H2"));
        tabLayout.addTab(tabLayout.newTab().setText("PAREEKSHA BHAVAN H3"));
        tabLayout.addTab(tabLayout.newTab().setText("PAREEKSHA BHAVAN H4"));
        tabLayout.addTab(tabLayout.newTab().setText("PAREEKSHA BHAVAN H5"));
        tabLayout.addTab(tabLayout.newTab().setText("OPEN STAGE"));
        tabLayout.addTab(tabLayout.newTab().setText("LECTURE HALL"));
        tabLayout.addTab(tabLayout.newTab().setText("LECTURE HALL"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);



        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
