package com.oit.appbase.http;

/**
 * Created by trista on 2017/3/24.
 * 返回接口回调
 */

public abstract class HttpCallBack<T> {

    public abstract void onSuccess( int code, String message, T data);

    public abstract void onFail( int code, String msg);
}
