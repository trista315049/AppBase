package com.oit.appbase.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;


/**
 * Created by ${trista} on 2017/6/19.
 */

public class CamerSelectPop {
    private static CamerSelectPop instance;
    public static CamerSelectPop getInstance(){
        if(instance == null){
            instance = new CamerSelectPop();
        }
        return instance;

    }
    private final static int REQUEST_CODE_ASK_PERMISSIONS = 100;

    public  boolean afterM(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }
    public   boolean requestCamera(Activity activity){
        if (afterM()){
            int hasPermission = activity.checkSelfPermission(Manifest.permission.CAMERA);
            if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                activity.requestPermissions(new String[]{Manifest.permission.CAMERA},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return false;
            }
        }
        return true;
    }

}
