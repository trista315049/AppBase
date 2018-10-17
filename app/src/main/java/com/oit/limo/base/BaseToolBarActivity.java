package com.oit.limo.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oit.limo.R;


/**
 * @author trista
 * @date 2018/9/14
 * @function 基类带头部
 */
public abstract class BaseToolBarActivity extends BaseActivity {
    private View mContainer;
    protected Toolbar mToolbar;
    private ImageView mImg;
    private TextView mTvMunu;
    private TextView mToolbarTitle;
    private FrameLayout mActionbarContent;
    private ImageView ivBack;
    private LinearLayout backLl;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContainer = getLayoutInflater().inflate(R.layout.activity_base_toolbar, null);
        backLl = (LinearLayout) mContainer.findViewById(R.id.im_back_ll);

        ivBack = (ImageView) mContainer.findViewById(R.id.iv_back);
        mToolbar = (Toolbar) mContainer.findViewById(R.id.toolbar);
        mImg = (ImageView) mContainer.findViewById(R.id.img);

        mTvMunu = (TextView) mContainer.findViewById(R.id.tv_munu);
        mToolbarTitle = (TextView) mContainer.findViewById(R.id.toolbar_title);
        mActionbarContent = (FrameLayout) mContainer.findViewById(R.id.actionbar_content);

        mToolbar.setTitle("");
        backLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(v.getWindowToken());
                onBackPressed();
            }
        });
        setRootView();
        initView();
        initData();
    }

    @Override
    public abstract void setRootView();

    @Override
    public abstract void initView();

    @Override
    public abstract void initData();

    public void setContentView() {
        super.setContentView(mContainer);
    }

    @Override
    public void setContentView(int layoutResID) {
        mActionbarContent.addView(getLayoutInflater()
                .inflate(layoutResID, null), new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        super.setContentView(mContainer);
        setSupportActionBar(mToolbar);

    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        mToolbarTitle.setText(title);
    }

    public void setTitle(String title, int textColor) {
        mToolbarTitle.setText(title);
        mToolbarTitle.setTextColor(textColor);
    }

    public void setIvBack(int resId, View.OnClickListener mOnClickListener) {
        ivBack.setImageResource(resId);
        ivBack.setOnClickListener(mOnClickListener);
    }

    @Override
    public boolean hasR() {
        return false;
    }

    /**
     * 添加Toobar右边功能，传入文字
     *
     * @param textId
     */
    public void addMenuTextItme(int textId, View.OnClickListener mOnClickListener) {
        mImg.setVisibility(View.GONE);

        mTvMunu.setText(textId);
        mTvMunu.setTextSize(17);
        mTvMunu.setOnClickListener(mOnClickListener);
    }

    /**
     * 添加Toobar右边功能，传入文字
     *
     * @param textId
     */
    public void addMenuTextItmeColor(int textId, String color, View.OnClickListener mOnClickListener) {
        mImg.setVisibility(View.GONE);

        mTvMunu.setText(textId);
        mTvMunu.setTextSize(17);
        mTvMunu.setTextColor(Color.parseColor(color));
        mTvMunu.setOnClickListener(mOnClickListener);
    }


    @Override
    public void onBackPressed() {
        this.finish();

    }

    /**
     * 添加Toobar右边功能，传入图片
     *
     * @param resId
     */
    public void addMenuImageItme(int resId, View.OnClickListener mOnClickListener) {
        mTvMunu.setVisibility(View.GONE);
        mImg.setVisibility(View.VISIBLE);
        mImg.setImageResource(resId);
        mImg.setOnClickListener(mOnClickListener);
    }



}
