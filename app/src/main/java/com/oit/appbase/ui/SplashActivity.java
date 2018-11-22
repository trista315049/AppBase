package com.oit.appbase.ui;

import android.os.Handler;

import com.blankj.utilcode.utils.AppUtils;
import com.oit.appbase.R;
import com.oit.appbase.base.BaseActivity;
import com.oit.appbase.util.SpConstants;
import com.oit.appbase.util.SpUtil;


/**
 * @author trista
 * @date 2018/9/14
 * @function
 */
public class SplashActivity extends BaseActivity {
    private boolean isLogin;
    private boolean mustReLogin = false;      // 是否要求用户重新登录
    private boolean hasNewGuide = false;  //相对上一版，是否有更新 引导图，有，设置该值 为true

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        // 获取当前版本号
        int appVersionCode = AppUtils.getAppVersionCode(this);
        // 版本对比
        int spVersionCode = (int) SpUtil.get(SpConstants.SP_VERSION_CODE, 0);
        isLogin = (boolean) SpUtil.get(SpConstants.SP_IS_LOGIN, false);

        if (spVersionCode < appVersionCode) {  // 新安装APP   覆盖安装
            if (mustReLogin) {//覆盖安装时，如果新版要求重新登录，先将登录状态置否
                SpUtil.put(SpConstants.SP_IS_LOGIN, false);
                isLogin = false;
            }

            if (((spVersionCode == 0) || hasNewGuide) && isLogin == false) {
//                toWecome();     // 为 0 ，即新安装的 APP ，直接 去欢迎界面
                toLoginOrMain(mustReLogin);
            } else {
                toLoginOrMain(mustReLogin);
            }
            SpUtil.put(SpConstants.SP_VERSION_CODE, appVersionCode);
        } else {     // 正常启动
            toLoginOrMain(false);

        }

    }

    /**
     * 去登录还是 主页
     *
     * @param mustLogin
     */
    private void toLoginOrMain(boolean mustLogin) {
        if (mustLogin || !isLogin) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // 必须重新登录 或者  没登录过
                    startAct(LoginActivity.class);
                    finish();
                }
            }, 1000);


        } else {
            // 跳转到 主页
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startAct(MainActivity.class);
                    finish();
                }
            }, 1000);
        }
    }
}
