package com.oit.appbase.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.oit.appbase.R;
import com.oit.appbase.adapter.CommonRecyclerAdapter;
import com.oit.appbase.base.BaseToolBarActivity;
import com.oit.appbase.weight.GrayFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author trista
 * @date 2018/11/27
 * @function
 */
public class FilterActivity extends BaseToolBarActivity {
    private ImageView mImage;
    private RecyclerView mList;
    private SeekBar mSaturationSeekBar;
    private SeekBar mLightSeekBar;
    private SeekBar mCheckSeekBar;




    private List<float[]> floats = new ArrayList<>();
    private List<Bitmap> bitmaps = new ArrayList<>();
    float[] float1 = {0.393f, 0.769f, 0.189f, 0, 0,
            0.349f, 0.686f, 0.168f, 0, 0,
            0.272f, 0.534f, 0.131f, 0, 0,
            0, 0, 0, 1, 0};
    float[] float2 = {1.438f, -0.062f, -0.062f, 0, 0,
            -0.122f, 1.378f, -0.122f, 0, 0,
            -0.016f, -0.016f, 1.483f, 0, 0,
            -0.03f, 0.05f, -0.02f, 1, 0};
    float[] float3 =  {2, 0, 0, 0, 0,
            0, 1, 0, 0, 0,
            0, 0, 1, 0, 0,
            0, 0, 0, 1, 0};
    float[] float4 = {1, 0, 0, 0, 0,
            0, 1.4f, 0, 0, 0,
            0, 0, 1, 0, 0,
            0, 0, 0, 1, 0};
    float[] float5 =  {1, 0, 0, 0, 0,
            0, 1, 0, 0, 0,
            0, 0, 1.6f, 0, 0,
            0, 0, 0, 1, 0};
    float[] float6 = {1, 0, 0, 0, 50,
            0, 1, 0, 0, 50,
            0, 0, 1, 0, 0,
            0, 0, 0, 1, 0};
    private Bitmap newBitmap;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_filter);
        setTitle("滤镜");
    }

    @Override
    public void initView() {

        mImage = findViewById(R.id.image);
        mList = findViewById(R.id.list);
        mSaturationSeekBar = findViewById(R.id.saturation_seek_bar);
        mLightSeekBar = findViewById(R.id.light_seek_bar);
        mCheckSeekBar = findViewById(R.id.check_seek_bar);

    }

    @Override
    public void initData() {
        List<String> list = new ArrayList<>();
        list.add("宝丽来");
        list.add("怀旧");
        list.add("泛红");
        list.add("荧光绿");
        list.add("宝石蓝");
        list.add("泛黄");
        floats.add(float1);
        floats.add(float2);
        floats.add(float3);
        floats.add(float4);
        floats.add(float5);
        floats.add(float6);
        bitmaps.add(GrayFilter.changeToGray(BitmapFactory.decodeResource(getResources(), R.drawable.galata), 0, floats.get(0)));
        bitmaps.add(GrayFilter.changeToGray(BitmapFactory.decodeResource(getResources(), R.drawable.galata), 1, floats.get(1)));
        bitmaps.add(GrayFilter.changeToGray(BitmapFactory.decodeResource(getResources(), R.drawable.galata), 2, floats.get(2)));
        bitmaps.add(GrayFilter.changeToGray(BitmapFactory.decodeResource(getResources(), R.drawable.galata), 3, floats.get(3)));
        bitmaps.add(GrayFilter.changeToGray(BitmapFactory.decodeResource(getResources(), R.drawable.galata), 4, floats.get(4)));
        bitmaps.add(GrayFilter.changeToGray(BitmapFactory.decodeResource(getResources(), R.drawable.galata), 5, floats.get(5)));


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mList.setLayoutManager(linearLayoutManager);
        CommonRecyclerAdapter<String> adapter = new CommonRecyclerAdapter<String>(mContext, R.layout.item_filter, list) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                ImageView mIcon = (ImageView) holder.getView(R.id.icon);
                TextView mName = (TextView) holder.getView(R.id.name);
                mName.setText(s);
                mIcon.setOnClickListener(v -> setFilter(position));
                mIcon.setImageBitmap(bitmaps.get(position));
            }
        };
        mList.setAdapter(adapter);

        mSaturationSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (newBitmap!= null){
                    mImage.setImageBitmap(GrayFilter.setSaturation(newBitmap,progress,0));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mLightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (newBitmap!= null){
                    mImage.setImageBitmap(GrayFilter.setSaturation(newBitmap,progress,1));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mCheckSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (newBitmap!= null){
                    mImage.setImageBitmap(GrayFilter.setSaturation(newBitmap,progress,2));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    private void setFilter(int i) {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.galata);
        newBitmap = GrayFilter.changeToGray(bitmap, i, floats.get(i));

        //把添加滤镜后的效果显示在imageview上
        mImage.setImageBitmap(newBitmap);
    }
}
