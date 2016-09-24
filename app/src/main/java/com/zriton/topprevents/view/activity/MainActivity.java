package com.zriton.topprevents.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zriton.topprevents.R;
import com.zriton.topprevents.view.adapter.MainPagerAdapter;
import com.zriton.topprevents.view.fragment.AboutFragment;
import com.zriton.topprevents.view.fragment.EventsFragment;
import com.zriton.topprevents.view.fragment.FavoritesFragment;
import com.zriton.topprevents.view.fragment.StatsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public Toolbar getToolbar() {
        return mToolbar;
    }

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.tabLayout) TabLayout mTabLayout;
    @BindView(R.id.viewPager) ViewPager mViewPager;

    public TextView getTvCount() {
        return tvCount;
    }

    TextView tvCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);
        setUpToolbar();
        setUpViewPager(mViewPager);
        setupTabIcons();
    }

    /**
     * Add toolbar to layout
     */
    private void setUpToolbar()
    {
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(false);
        ab.setDisplayShowHomeEnabled(false);
    }


    /**
     * Adding fragments to ViewPager
     *
     * @param pViewPager Viewpager which is to be set
     */
    private void setUpViewPager(ViewPager pViewPager) {
        MainPagerAdapter lMainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        lMainPagerAdapter.addFragment(EventsFragment.newInstance(),"Events");
        lMainPagerAdapter.addFragment(FavoritesFragment.newInstance(),"Favorites");
        lMainPagerAdapter.addFragment(StatsFragment.newInstance(),"Statistics");
        lMainPagerAdapter.addFragment(AboutFragment.newInstance(),"About");
        pViewPager.setAdapter(lMainPagerAdapter);

        mTabLayout.setupWithViewPager(pViewPager);
    }

    /**
     * Add tab icons on tabLayout
     */
    private void setupTabIcons()
    {
        View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_list_badge, null);
        tvCount = (TextView)v.findViewById(R.id.tvCount);
        mTabLayout.getTabAt(0).setCustomView(v);
        mTabLayout.getTabAt(1).setIcon(R.drawable.tab_icon_favorites);
        mTabLayout.getTabAt(2).setIcon(R.drawable.tab_icon_stats);
        mTabLayout.getTabAt(3).setIcon(R.drawable.tab_icon_about);
    }

    @Override
    public void onBackPressed() {
        if(mViewPager.getCurrentItem()==0)
        {
            finish();
        }
        else
        {
            mViewPager.setCurrentItem(0,true);
        }
    }

}
