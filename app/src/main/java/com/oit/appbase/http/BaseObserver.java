package com.oit.appbase.http;

import android.text.TextUtils;

import io.reactivex.observers.ResourceObserver;
import retrofit2.HttpException;

/**
 * @author quchao
 * @date 2017/11/27
 *
 * @param <T>
 */

public abstract class BaseObserver<T> extends ResourceObserver<T> {

    private BaseView mView;
    private String mErrorMsg;
    private boolean isShowError = true;

    protected BaseObserver(BaseView view){
        this.mView = view;
    }

    protected BaseObserver(BaseView view, String errorMsg){
        this.mView = view;
        this.mErrorMsg = errorMsg;
    }

    protected BaseObserver(BaseView view, boolean isShowError){
        this.mView = view;
        this.isShowError = isShowError;
    }

    protected BaseObserver(BaseView view, String errorMsg, boolean isShowError){
        this.mView = view;
        this.mErrorMsg = errorMsg;
        this.isShowError = isShowError;
    }

    public BaseObserver() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        if (mView == null) {
            return;
        }
        mView.hideProgress();
        if (mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg)) {
            mView.onError(mErrorMsg);
        }else if (e instanceof HttpException) {
               mView.showNetError();
        } else {
            mView.onError("网络开小差啦，请稍等！");
        }
    }
}
