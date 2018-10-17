package com.oit.limo.presenter;

import android.app.Activity;
import android.text.TextUtils;

import com.blankj.utilcode.utils.ToastUtils;
import com.oit.limo.http.BasePresenter;
import com.oit.limo.http.BaseView;
import com.oit.limo.http.HttpCallBack;
import com.oit.limo.http.HttpData;
import com.oit.limo.util.SpConstants;
import com.oit.limo.util.SpUtil;


import java.util.HashMap;
import java.util.Map;

/**
 * @author trista
 * @date 2018/9/17
 * @function
 */
public class TestPresenter extends BasePresenter<BaseView<Object>> {

    public TestPresenter(BaseView<Object> view) {
        super(view);
    }


    /**
     * @param context
     * @param appKey
     * @param from
     * @param to
     */
    public void test(Activity context, String appKey, String from, String to) {

        Map<String, String> map = new HashMap<>();
        map.put("key", appKey);
        map.put("from", from);
        map.put("to", to);
        mView.showProgress();
        OkRequest(context, HttpData.GET, SpConstants.test, map, null, new HttpCallBack<Object>() {
            @Override
            public void onSuccess(int code, String message, Object data) {
                mView.hideProgress();
                ToastUtils.showLongToast(message);
                mView.onSucess(data);
            }

            @Override
            public void onFail(int code, String msg) {
                mView.hideProgress();
                mView.onError(msg);

            }
        });

    }


}
