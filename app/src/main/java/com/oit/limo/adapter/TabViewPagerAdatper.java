package com.oit.limo.adapter;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by ${trista} on 2017/6/8.
 */

public class TabViewPagerAdatper extends FragmentStatePagerAdapter {
    private List<Fragment> listFragment;                         //fragment列表
    private List<String> listTitle;                              //tab名的列表
    public TabViewPagerAdatper(FragmentManager fm, List<Fragment> listFragment, List<String> listTitle) {
        super(fm);
        this.listFragment = listFragment;
        this.listTitle = listTitle;
    }
    public void addData(List<Fragment> listFragment){
        this.listFragment = listFragment;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment != null && listFragment.size() > 0 ? listFragment.size() : 0;
    }
    //此方法用来显示tab上的名字
    @Override
    public CharSequence getPageTitle(int position) {

        return listTitle.get(position % listTitle.size());
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {

    }
}
