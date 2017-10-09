package com.LeelaGroup.AgrawalFedration.medical;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.LeelaGroup.AgrawalFedration.R;

public class Medical_browse extends AppCompatActivity {

    ViewPager simpleViewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_browse);



        //**********************TAB LAYOUT***********************************************************

        // get the reference of ViewPager and TabLayout
        simpleViewPager = (ViewPager) findViewById(R.id.simpleViewPager);
        tabLayout = (TabLayout) findViewById(R.id.simpleTabLayout);
        //Create a new Tab named "First"
        TabLayout.Tab firstTab = tabLayout.newTab();
        firstTab.setText("Top Medical"); // set the Text for the first Tab
        firstTab.setIcon(R.drawable.ic_camera); // set an icon for the
        // first tab
        tabLayout.addTab(firstTab); // add  the tab at in the TabLayout
        // Create a new Tab named "Second"
        TabLayout.Tab secondTab = tabLayout.newTab();
        secondTab.setText("Top Hospital"); // set the Text for the second Tab
        secondTab.setIcon(R.drawable.ic_gallery); // set an icon for the second tab
        tabLayout.addTab(secondTab); // add  the tab  in the TabLayout
//         Create a new Tab named "Third"
        TabLayout.Tab thirdTab = tabLayout.newTab();
        thirdTab.setText("Top Doctor"); // set the Text for the first Tab
        thirdTab.setIcon(R.drawable.ic_send); // set an icon for the first tab
        tabLayout.addTab(thirdTab); // add  the tab at in the TabLayout


        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        simpleViewPager.setAdapter(adapter);
//         addOnPageChangeListener event change the tab on slide
        simpleViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                simpleViewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {

            }
        });


    }
    //*****************************************END TAB****************************************************

}

