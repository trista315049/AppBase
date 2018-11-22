package com.oit.appbase.http;

/**
 * @author trista
 * @date 2018/4/25
 * @function
 */
public interface BaseView<T> {
    void showProgress();
    void hideProgress();
    void onSucess(T data);
    void onError(String error);
    void onDeleteCash(String error); //201
    void showNetError();//无网络

}
