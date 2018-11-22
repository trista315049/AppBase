package com.oit.appbase.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.utils.LogUtils;
import com.oit.appbase.R;
import com.oit.appbase.base.BaseFragment;

/**
 * @author trista
 * @date 2018/11/21
 * @function
 */
public class MainFragmet extends BaseFragment {

    private Toolbar mToolbar;
    @Override
    public void onLazyLoad() {
    }
    @Override
    public View setRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }
    @Override
    public void initView(View view) {
        mToolbar = view.findViewById(R.id.toolbar);
    }

    @Override
    public void initData() {

    }
    @Override
    public void changeTheme(int currentTheme) {
        LogUtils.e("MainFragmet---changeTheme", currentTheme+"");
        updateColor(currentTheme);
    }
}
