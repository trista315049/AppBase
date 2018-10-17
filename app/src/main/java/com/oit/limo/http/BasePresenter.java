package com.oit.limo.http;


import android.annotation.SuppressLint;
import android.content.Context;

import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import okhttp3.RequestBody;

/**
 * @author trista
 * @date 2018/4/25
 * @function
 */
public abstract class BasePresenter<V> {
    private CompositeDisposable compositeDisposable;

    protected V mView;

    public BasePresenter(V view) {
        attachView(view);
    }

    public void attachView(V view) {
        mView = view;
    }

    public void detachView() { mView= null; }

    protected void addSubscribe(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }
    @SuppressLint("CheckResult")
    public <T> void OkRequest(Context context, int method, String url, Map<String, String> map, Map<String, RequestBody> body, HttpCallBack<T> httpCallBack) {
        HttpData.getInstance().request(context,method, url, map,body,httpCallBack,(BaseView) mView);
    }

}