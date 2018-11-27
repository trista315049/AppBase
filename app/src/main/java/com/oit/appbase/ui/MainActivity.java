package com.oit.appbase.ui;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.utils.LogUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.oit.appbase.R;
import com.oit.appbase.adapter.CommonRecyclerAdapter;
import com.oit.appbase.base.BaseActivity;
import com.oit.appbase.http.BaseView;
import com.oit.appbase.presenter.TestPresenter;
import com.oit.appbase.weight.CardPickerDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements BaseView<Object>,CardPickerDialog.ClickListener {
    private String appKey = "150d903566863e4dbc9dccde43d4917d";
    private String from = "CNY";
    private String to = "HKD";
    private TestPresenter testPresenter;

    private DrawerLayout mRoot;
    private LRecyclerView mIdLvLeftMenu;
    private List<String> list;
    private CommonRecyclerAdapter<String> adapter;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
        mRoot = findViewById(R.id.root);
        mIdLvLeftMenu = findViewById(R.id.id_lv_left_menu);
    }

    @Override
    public void initData() {
        testPresenter = new TestPresenter(this);
        View mHeadView = LayoutInflater.from(this).inflate(R.layout.head_main,null,false);

        list =  new ArrayList<>();
        list.add("切换主题");
        list.add("请求数据");
        list.add("SpannableString");
        list.add("Transition");
        list.add("滤镜");
        list.add("音视频合成");
        mIdLvLeftMenu.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommonRecyclerAdapter<String>(this, R.layout.item_list, list) {
            @Override
            protected void convert(ViewHolder holder, String o, int position) {
                TextView view = (TextView) holder.getView(R.id.text1);
                view.setText(o);
            }
        };
        LRecyclerViewAdapter lRecyclerViewAdapter =  new LRecyclerViewAdapter(adapter);
        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mRoot.closeDrawers();
                switch (position){
                    case 0:
                        CardPickerDialog dialog = new CardPickerDialog();
                        dialog.setClickListener(MainActivity.this);
                        dialog.show(getSupportFragmentManager(), "theme");
                        break;
                    case 1:
                        testPresenter.test(mContext,appKey,from,to);
                        break;
                    case 2:
                        startAct(SpannableStringActivity.class);
                        break;
                    case 3:
                        startAct(TransitionActivity.class);
                        break;
                    case 4:
                        startAct(FilterActivity.class);
                        break;
                    case 5:
                        startAct(FilterActivity.class);
                        break;
                }

            }
        });
        lRecyclerViewAdapter.addHeaderView(mHeadView);
        mIdLvLeftMenu.setAdapter(lRecyclerViewAdapter);
        mIdLvLeftMenu.setPullRefreshEnabled(false);
        mIdLvLeftMenu.setLoadMoreEnabled(false);

    }





    @Override
    public void showProgress() {
        show();
    }

    @Override
    public void hideProgress() {
        dismiss();
    }

    @Override
    public void onSucess(Object data) {
        LogUtils.e("onSucess--",data.toString());
    }

    @Override
    public void onError(String error) {
        ToastUtils.showLongToast(error);
    }

    @Override
    public void onDeleteCash(String error) {
        //处理无数据的情况
    }

    @Override
    public void showNetError() {
        ToastUtils.showLongToast(R.string.not_network);
    }

    @Override
    public void onConfirm(int currentTheme) {
        changeTheme(currentTheme);
    }






}
