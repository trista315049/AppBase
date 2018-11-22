package com.oit.appbase.ui;

import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.oit.appbase.R;
import com.oit.appbase.base.BaseToolBarActivity;

/**
 * @author trista
 * @date 2018/11/22
 * @function
 */
public class ShareElementsActivity extends BaseToolBarActivity{
    private ImageView mImageview;
    private ImageView mHeader;
    private TextView mTvInfo;



    @Override
    public void setRootView() {
        setContentView(R.layout.activity_share_element);
        setIvBack(R.drawable.return_button,v ->  supportFinishAfterTransition());
        setTitle("ShareElements");
    }

    @Override
    public void initView() {
        mImageview = findViewById(R.id.imageview);
        mHeader = findViewById(R.id.header);
        mTvInfo = findViewById(R.id.tv_info);
        mTvInfo.setText(getIntent().getStringExtra("content"));
    }

    @Override
    public void initData() {

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == android.R.id.home) {
            //If you use 'finish();' you will not get the animation effect,
            //you can use the following methods instead.
            supportFinishAfterTransition();
        }
        return super.onOptionsItemSelected(item);
    }
}
