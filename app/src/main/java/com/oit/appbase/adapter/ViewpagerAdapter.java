package com.oit.appbase.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by ${trista} on 2017/6/6.
 */

public class ViewpagerAdapter extends PagerAdapter {
    private Context context;
    private List<View> viewPageListView;
    public ViewpagerAdapter(Context context, List<View> viewPageListView) {
        this.context = context;
        this.viewPageListView = viewPageListView;
    }

    @Override
    public int getCount() {
        return viewPageListView != null && viewPageListView.size() > 0 ? viewPageListView.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewPageListView.get(position));
        return viewPageListView.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void addData(List<View> viewPageListView) {
        this.viewPageListView = viewPageListView;
        notifyDataSetChanged();
    }
}
