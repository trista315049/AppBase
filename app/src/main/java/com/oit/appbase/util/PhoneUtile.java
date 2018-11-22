package com.oit.appbase.util;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * @author trista
 * @date 2018/8/10
 * @function
 */
public class PhoneUtile {
    private static PhoneUtile instance;

    public static PhoneUtile getInstance() {
        if (instance == null) {
            instance = new PhoneUtile();
        }
        return instance;
    }

    public void request(Activity mContext, String phone) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(mContext, new String[]{Manifest.permission.CALL_PHONE},
                        101);
                return;
            } else {
                callPhone(mContext,phone);
            }
        } else {
            callPhone(mContext,phone);
        }


    }

    public void callPhone(Activity mContext,String phone) {
        //拨打打电话
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phone);
        intent.setData(data);
        mContext.startActivity(intent);
    }
}
