package com.oit.limo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.oit.limo.R;
import com.oit.limo.base.BaseActivity;
import com.oit.limo.http.BaseView;
import com.oit.limo.presenter.TestPresenter;

public class MainActivity extends BaseActivity implements BaseView<Object> {
    private String appKey = "150d903566863e4dbc9dccde43d4917d";
    private String from = "CNY";
    private String to = "HKD";
    private TextView mShow;
    private TestPresenter testPresenter;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_main);

    }

    @Override
    public void initView() {
        mShow = findViewById(R.id.show);

    }

    @Override
    public void initData() {
        testPresenter = new TestPresenter(this);
        mShow.setOnClickListener(v -> testPresenter.test(mContext,appKey,from,to));

    }

    @Override
    public void showProgress() {
        show();
    }

    @Override
    public void hideProgress() {
        dismiss();
    }

    @Override
    public void onSucess(Object data) {
        mShow.setText(data.toString());
    }

    @Override
    public void onError(String error) {
        ToastUtils.showLongToast(error);
    }

    @Override
    public void onDeleteCash(String error) {
        //处理无数据的情况
    }

    @Override
    public void showNetError() {
        ToastUtils.showLongToast(R.string.not_network);
    }
}
