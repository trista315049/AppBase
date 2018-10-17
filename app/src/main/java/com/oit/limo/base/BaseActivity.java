package com.oit.limo.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.gson.Gson;
import com.oit.limo.http.HttpCallBack;
import com.oit.limo.http.HttpData;
import com.oit.limo.util.BarUtil;
import com.oit.limo.weight.CustomProgressDialog;


import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * @author trista
 * @date 2018/9/12
 * @function 基类
 */
public abstract class BaseActivity extends AppCompatActivity {
    public Gson mGson;
    public Activity mContext;
    public CustomProgressDialog mLoading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BarUtil.setBar(this);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.mContext = this;
        if (mGson == null) {
            mGson = new Gson();
        }
        mLoading = CustomProgressDialog.getDialog(this);
        Log.e("-->ActivityName:", getClass().getSimpleName());
        if (hasEventBus()) {
            EventBus.getDefault().register(this);
        }

        if (hasR()) {
            setRootView();
            initView();
            initData();

        }
    }

    public abstract void setRootView();

    public abstract void initView();

    public abstract void initData();

    /**
     * 使用EventBus必须重写此方法，返回true
     */
    public boolean hasEventBus() {
        return false;
    }

    public void show() {
        try {
            if (!isFinishing() && !mLoading.isShowing()) {
                mLoading.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dismiss() {
        if (mLoading != null && mLoading.isShowing()) {
            mLoading.dismiss();
        }
    }

    /**
     * 防止抽象函数报空，此方法仅 BaseToolBarActivity 调用，其他子类无需实现
     *
     * @return
     */
    public boolean hasR() {
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        LimoApplication.addActivity(mContext);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LimoApplication.removeActivity(mContext);
    }


    public void startAct(Class class1) {
        Intent intent = new Intent(BaseActivity.this, class1);
        this.startActivity(intent);
    }

    public void startAct(Class class1, String name, String str) {
        Intent intent = new Intent(BaseActivity.this, class1);
        intent.putExtra(name, str);
        this.startActivity(intent);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     *
     * @param token
     */
    public void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @SuppressLint("CheckResult")
    public <T> void OkRequest(int method, String url, Map<String, String> map, Map<String, RequestBody> body, HttpCallBack<T> httpCallBack) {
        HttpData.getInstance().request(this, method, url, map, body, httpCallBack, null);
    }
}
