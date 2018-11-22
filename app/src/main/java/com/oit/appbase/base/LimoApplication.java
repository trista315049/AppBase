package com.oit.appbase.base;

import android.app.Activity;
import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;

import com.blankj.utilcode.utils.Utils;

import com.oit.appbase.util.SpConstants;
import com.oit.appbase.util.SpUtil;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author trista
 * @date 2018/9/12
 * @function
 */
public class LimoApplication extends MultiDexApplication {
    private static Context context;

    public static Context getContent() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();
        Utils.init(context);
        SpUtil.init(context);
        initBugly();

    }

    private void initBugly() {
        Context context = getApplicationContext();
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        // 初始化Bugly
        CrashReport.initCrashReport(context, SpConstants.BUGLY_ID, true, strategy);
    }


    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }


    public static List<Activity> activities;

    public static void addActivity(Activity activity) {
        if (activities == null) {
            //如果集合为空  则初始化
            activities = new ArrayList<>();
        }
        //将activity加入到集合中
        activities.add(activity);
    }

    //结束掉所有的Activity
    public static void finishAll() {
        //循环集合,  将所有的activity finish
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    public static void removeActivity(Activity activity) {
        //移除已经销毁的Activity
        activities.remove(activity);
    }

    /**
     * 退出应用程序
     */
    public static void appExit() {
        finishAll();
        System.exit(0);
    }
}
