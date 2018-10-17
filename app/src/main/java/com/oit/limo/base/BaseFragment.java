package com.oit.limo.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.oit.limo.weight.CustomProgressDialog;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by trista on 2017/4/5.
 */

public abstract class BaseFragment extends Fragment {
    protected Context mContext;
    private boolean mIsFirstVisible = true;
    private View view;
    public CustomProgressDialog mLoading;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
        mLoading = CustomProgressDialog.getDialog(mContext);
        if (hasEventBus()) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (hasEventBus()) {
            EventBus.getDefault().unregister(this);
        }
        if (mLoading != null && mLoading.isShowing()) {
            mLoading.dismiss();
        }
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    /**
     * 使用EventBus必须重写此方法，返回true
     */
    public boolean hasEventBus() {
        return false;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isResumed()) {
            if (isVisibleToUser) {
                if (mIsFirstVisible) {
                    mIsFirstVisible = false;
                    onLazyLoad();
                }
            }
        }
    }


    /**
     * 延迟加载 仅在fragment第一次对用户可见时调用一次该方法（建议放网络请求）
     * 子类必须重写此方法
     */
    public abstract void onLazyLoad();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = setRootView(inflater, container, savedInstanceState);
        initView(view);


        initData();
        return view;
    }



    public abstract View setRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public abstract void initView(View view);


    public abstract void initData();


}