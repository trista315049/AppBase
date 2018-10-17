package com.oit.limo.http;


import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.utils.ToastUtils;
import com.google.gson.Gson;
import com.oit.limo.ui.LoginActivity;
import com.oit.limo.ui.MainActivity;
import com.oit.limo.R;
import com.oit.limo.base.LimoApplication;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * @author trista
 * @date 2018/4/25
 * @function 接口请求管理
 */
public class HttpData extends RetrofitUtils {
    private static HttpData instance;
    String simyBaseUrl = "http://op.juhe.cn/";
    protected ApiService apiService = getRetrofit(simyBaseUrl).create(ApiService.class);
    private static Gson mGson;
    public final static int GET = 0;
    public final static int POST = 1;
    public final static int UP = 2;
    public final static int DELETE = 3;

    private HttpData() {
        mGson = new Gson();
    }

    public static HttpData getInstance() {
        if (instance == null) {
            synchronized (HttpData.class) {
                if (instance == null) {
                    instance = new HttpData();
                }
            }
        }
        return instance;
    }


    public <T> void request(Context context, int method, String url, Map<String, String> map,
                            Map<String, RequestBody> body, HttpCallBack<T> httpCallBack, BaseView mView) {

        switch (method) {
            case GET:
                apiService.get(url, map).compose(RxUtils.rxSchedulerHelper())
                        .subscribeWith(new BaseObserver<String>(mView) {
                            @Override
                            public void onNext(String data) {
                                onSuccessBack(context, httpCallBack, data, mView);
                            }
                        });
                break;
            case POST:

                apiService.post(url, map).compose(RxUtils.rxSchedulerHelper())
                        .subscribeWith(new BaseObserver<String>(mView) {
                            @Override
                            public void onNext(String data) {
                                onSuccessBack(context, httpCallBack, data, mView);
                            }
                        });


                break;
            case UP:
                apiService.upFiles(url, map, body).compose(RxUtils.rxSchedulerHelper())
                        .subscribeWith(new BaseObserver<String>(mView) {
                            @Override
                            public void onNext(String data) {
                                onSuccessBack(context, httpCallBack, data, mView);
                            }
                        });
                break;
            case DELETE:
                apiService.delete(url, map).compose(RxUtils.rxSchedulerHelper())
                        .subscribeWith(new BaseObserver<String>(mView) {
                            @Override
                            public void onNext(String data) {
                                onSuccessBack(context, httpCallBack, data, mView);
                            }
                        });
                break;

        }
    }


    private <T> void onSuccessBack(Context context, HttpCallBack<T> httpCallBack, String response, BaseView mView) {
        try {


            if (httpCallBack == null) {
                return;
            }
            Log.e("OK success", response);
            ToastUtils.showLongToast(response);
            Type type = getTType(httpCallBack.getClass());

            OkHttpResult result = mGson.fromJson(response, OkHttpResult.class);
            if (result != null) {

                if (result.getCode() == 200) {
                    //成功
                    String json = mGson.toJson(result.getResult());
                    if (type == String.class) {//泛型是String，返回结果json字符串
                        httpCallBack.onSuccess(result.getCode(), result.getClientMsg(), (T) result.getResult());
                    } else {//泛型是实体或者List<>
                        T t = mGson.fromJson(json, type);
                        httpCallBack.onSuccess(result.getCode(), result.getClientMsg(), t);
                    }
                } else if (result.getCode() == 202) {
                    //退出app
                    ToastUtils.showLongToast(result.getClientMsg());
                    context.startActivity(new Intent(context, LoginActivity.class));
                    LimoApplication.finishAll();
                } else if (result.getCode() == 201) {
                    httpCallBack.onFail(result.getCode(), result.getClientMsg());
                    if (mView!= null)                     mView.onDeleteCash(result.getClientMsg());
                } else if (result.getCode() == 203) {
                    httpCallBack.onFail(result.getCode(), result.getClientMsg());
                    if (mView!= null) mView.onDeleteCash(result.getClientMsg());
                } else {
                    if (!TextUtils.isEmpty(result.getClientMsg())) {
                        Log.e("onFail------", result.getClientMsg());
                        if (result.getClientMsg().toString().contains("500") || result.getClientMsg().toString().contains(simyBaseUrl)) {
                            httpCallBack.onFail(result.getCode(), context.getString(R.string.not_network));
                        } else {
                            httpCallBack.onFail(result.getCode(), result.getClientMsg());
                        }
                    } else {
                        httpCallBack.onFail(result.getCode(), context.getString(R.string.not_data));
                    }
                }


            } else {
                httpCallBack.onFail(500, context.getString(R.string.not_network));

            }
        } catch (Exception e)

        {
            e.printStackTrace();
        }

    }


    private Type getTType(Class<?> clazz) {
        Type mySuperClassType = clazz.getGenericSuperclass();
        Type[] types = ((ParameterizedType) mySuperClassType).getActualTypeArguments();
        if (types != null && types.length > 0) {
            return types[0];
        }
        return null;
    }



}
