package com.oit.limo.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.oit.limo.R;
import com.oit.limo.weight.CircleTransform;
import com.oit.limo.weight.GlideRoundTransform;

import java.io.ByteArrayOutputStream;
import java.io.File;

import static com.blankj.utilcode.utils.ConvertUtils.dp2px;

/**
 * @author trista
 * @date 2018/9/17
 * @function
 */
public class GlideUtil {
    /**
     * 普通加载
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void show(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url)
                .placeholder(R.color.bg_f9)
                .error(R.color.bg_f9)
                .centerCrop()
                .into(imageView);


    }

    public static void showSize(Context context, String url, ImageView imageView, int w, int h) {

        Glide.with(context).load(url)
                .placeholder(R.color.bg_f9)
                .error(R.color.bg_f9)
                .centerCrop()
                .override(dp2px(w), dp2px(h))
                .into(imageView);


    }

    /**
     * 普通加载  File
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void showFile(Context context, String url, ImageView imageView) {
        Glide.with(context).load(new File(url))
                .placeholder(R.color.bg_f9)
                .error(R.color.bg_f9)
                .centerCrop()
                .into(imageView);

    }

    public static void showFileSize(Context context, String url, ImageView imageView, int w, int h) {
        Glide.with(context).load(new File(url))
                .placeholder(R.color.bg_f9)
                .error(R.color.bg_f9)
                .centerCrop()
                .override(dp2px(w), dp2px(h))
                .into(imageView);

    }

    /**
     * 圆形图
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void showCircle(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .transform(new CircleTransform(context))
                .placeholder(R.color.bg_f9)
                .error(R.color.bg_f9)
                .override(dp2px(45), dp2px(45))
                .into(imageView);


    }

    public static void showCircle(Context context, Bitmap url, ImageView imageView) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        url.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes = baos.toByteArray();

        Glide.with(context)
                .load(bytes)
                .transform(new CircleTransform(context))
                .placeholder(R.color.bg_f9)
                .error(R.color.bg_f9)
                .override(dp2px(45), dp2px(45))
                .into(imageView);


    }


    /**
     * 圆角图
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void showCorner(Context context, String url, final ImageView imageView, int dp) {

        Glide.with(context)
                .load(url)
                .transform(new CenterCrop(context), new GlideRoundTransform(context, dp))//todo 不能设置图片方式 CenterCrop
                .placeholder(R.color.bg_f9)
                .error(R.color.bg_f9)
                .into(imageView);

    }

    public static void showCorner(Context context, String url, final ImageView imageView, int dp, int w, int h) {

        Glide.with(context)
                .load(url)
                .transform(new CenterCrop(context), new GlideRoundTransform(context, dp))//todo 不能设置图片方式 CenterCrop
                .placeholder(R.color.bg_f9)
                .error(R.color.bg_f9)
                .override(dp2px(w), dp2px(h))
                .into(imageView);

    }

}
