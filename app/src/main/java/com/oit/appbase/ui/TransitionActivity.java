package com.oit.appbase.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.oit.appbase.R;
import com.oit.appbase.adapter.CommonRecyclerAdapter;
import com.oit.appbase.base.BaseToolBarActivity;
import com.oit.appbase.util.SpacingItemDecoration;
import com.oit.appbase.util.TransitionHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author trista
 * @date 2018/11/22
 * @function
 */
public class TransitionActivity extends BaseToolBarActivity {
    private RecyclerView mList;
    private List<String> list;
    private CommonRecyclerAdapter<String> adapter;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_transition);
        setTitle("TransitionAnimation");
    }

    @Override
    public void initView() {
        mList = findViewById(R.id.list);
        mList.setLayoutManager(new GridLayoutManager(this, 2));
        mList.addItemDecoration(new SpacingItemDecoration(2,5,false));

        list = new ArrayList<>();
        adapter = new CommonRecyclerAdapter<String>(this, R.layout.item_transition, list) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {

                ImageView mIcon = (ImageView) holder.getView(R.id.icon);
                ImageView mHead =  (ImageView) holder.getView(R.id.head);
                TextView mContent =  (TextView) holder.getView(R.id.content);
                mContent.setText(s);


                mIcon.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onClick(View v) {
                        ActivityOptionsCompat activityOptionsCompat;
                        if (holder.getAdapterPosition() % 2 == 0) {
                            Pair<View, String> pair1 = new Pair<View, String>(mIcon, mContext.getString(R.string.share_element_imageview));
                            Pair<View, String> pair2 = new Pair<View, String>(mHead, mContext.getString(R.string.share_element_header));
                            Pair<View, String> pair3 = new Pair<View, String>(mContent, mContext.getString(R.string.share_element_tv_info));
                            activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation( mContext, pair1, pair2, pair3);
                        } else {
                            final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(
                                    mContext, true,
                                    new Pair<>(mIcon, mContext.getString(R.string.share_element_imageview)),
                                    new Pair<>(mHead, mContext.getString(R.string.share_element_header)),
                                    new Pair<>(mContent, mContext.getString(R.string.share_element_tv_info)));
                            activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(((Activity) mContext), pairs);
                        }

                        Intent intent = new Intent(mContext, ShareElementsActivity.class);
                        intent.putExtra("content",list.get(position));
                        mContext.startActivity(intent, activityOptionsCompat.toBundle());
                    }
                });
            }
        };
        mList.setAdapter(adapter);
    }

    @Override
    public void initData() {
        for (int i = 0; i < 10; i++) {
            list.add("test" + i);
        }
        adapter.addData(list);
    }
}
