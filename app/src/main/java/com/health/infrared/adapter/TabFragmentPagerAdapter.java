package com.health.infrared.adapter;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import java.util.List;

/**
 * tab 适配器
 * Created by beifeitu on 16/11/15.
 */
public class TabFragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {
    /**
     * tab 标题
     * Created by beifeitu on 16/11/15.
     */
    private String[] titles;

    /**
     * tab 视图
     * Created by beifeitu on 16/11/15.
     */
    private List<Fragment> fragments;

    /**
     * 构造tab适配器
     *
     * @param tabLayout
     * @param viewPager
     * @param fm
     * @param titles
     * @param fragments
     */
    public TabFragmentPagerAdapter(TabLayout tabLayout, ViewPager viewPager, FragmentManager fm, String[] titles, List<Fragment> fragments) {
        super(fm);
        this.titles = titles;
        this.fragments = fragments;
        //给ViewPager设置适配器
        viewPager.setAdapter(this);
        //tablayout与Viewpager绑定
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
