package com.oit.appbase.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.oit.appbase.http.HttpCallBack;
import com.oit.appbase.http.HttpData;
import com.oit.appbase.ui.AppBaseStateListener;
import com.oit.appbase.util.BarUtil;
import com.oit.appbase.util.SpConstants;
import com.oit.appbase.weight.CustomProgressDialog;
import com.wuyr.rippleanimation.RippleAnimation;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * @author trista
 * @date 2018/9/12
 * @function 基类
 */
public abstract class BaseActivity extends AppCompatActivity  {
    public Gson mGson;
    public Activity mContext;
    public CustomProgressDialog mLoading;
    private ArrayList<AppBaseStateListener> mMusicListener = new ArrayList<>();

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
    public void changeTheme(int currentTheme) {
        for (final AppBaseStateListener listener : mMusicListener) {
            if (listener != null) {
                listener.changeTheme(currentTheme);
            }
        }
    }

    public void updateColor(ViewGroup view,int currentTheme) {
        View[] mChildViews = new View[ view.getChildCount()];
        for (int i = 0; i < mChildViews.length; i++) {
            mChildViews[i] = view.getChildAt(i);
        }
        //关键代码
        RippleAnimation.create(view).setDuration(250).start();
        int color = Color.RED;
        switch (currentTheme) {
            case SpConstants.CARD_RED:
                color = Color.RED;
                break;
            case SpConstants.CARD_v:
                color = Color.parseColor("#FF0ED1");
                break;
            case SpConstants.CARD_BULE:
                color = Color.BLUE;
                break;
            case SpConstants.CARD_GREEN:
                color = Color.GREEN;
                break;

        }

        for (View view1 : mChildViews) {
            if (view1 instanceof TextView) {
                ((TextView) view1).setTextColor(color);
            } else {
                view1.setBackgroundColor(color);
            }
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
    protected void onResume() {
        super.onResume();

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




    public void setAppBaseStateListener(final AppBaseStateListener status) {
        if (status == this) {
            throw new UnsupportedOperationException("Override the method, don't add a listener");
        }

        if (status != null) {
            mMusicListener.add(status);
        }
    }

    public void removeAppBaseStateListener(final AppBaseStateListener status) {
        if (status != null) {
            mMusicListener.remove(status);
        }
    }

}
