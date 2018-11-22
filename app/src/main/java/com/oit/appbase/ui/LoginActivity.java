package com.oit.appbase.ui;

import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.utils.SPUtils;
import com.oit.appbase.R;
import com.oit.appbase.base.BaseActivity;
import com.oit.appbase.util.SpConstants;
import com.oit.appbase.util.SpUtil;

public class LoginActivity extends BaseActivity {
    private TextView mLogin;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_login);

    }

    @Override
    public void initView() {
        mLogin = findViewById(R.id.login);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpUtil.put(SpConstants.SP_IS_LOGIN,true);
                startAct(MainActivity.class);
            }
        });

    }

    @Override
    public void initData() {

    }
}
