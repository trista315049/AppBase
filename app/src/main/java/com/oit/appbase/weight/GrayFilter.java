package com.oit.appbase.weight;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

public class GrayFilter {
    public static Bitmap setSaturation(Bitmap bitmap, int progress ,int flag) {
        // 创建一个相同尺寸的可变的位图区,用于绘制调色后的图片
        int width, height;
        width = bitmap.getWidth();
        height = bitmap.getHeight();
        Bitmap grayBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(grayBitmap);
        ColorMatrix cMatrix = new ColorMatrix();
        if (flag == 0){
            // 设置饱和度
            cMatrix.setSaturation((float) (progress / 100.0));
        }else if (flag == 1){
            int brightness = progress - 127;
            cMatrix.set(new float[]{1, 0, 0, 0, brightness, 0, 1,
                    0, 0, brightness,// 改变亮度
                    0, 0, 1, 0, brightness, 0, 0, 0, 1, 0});
        } else {
            float contrast = (float) ((progress + 64) / 128.0);
            cMatrix.set(new float[]{contrast, 0, 0, 0, 0, 0,
                    contrast, 0, 0, 0,// 改变对比度
                    0, 0, contrast, 0, 0, 0, 0, 0, 1, 0});
        }


        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(cMatrix));
        // 在Canvas上绘制一个已经存在的Bitmap。这样，dstBitmap就和srcBitmap一摸一样了
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return grayBitmap;
    }


    // 黑白效果函数
    public static Bitmap changeToGray(Bitmap bitmap, int in, float[] array) {

        int width, height;
        width = bitmap.getWidth();
        height = bitmap.getHeight();

        Bitmap grayBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(grayBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true); // 设置抗锯齿

        //一，数组矩阵的方法
        /*//泛黄的值
        float[] array = {1, 0, 0, 0, 100,
                         0, 1, 0, 0, 100,
						 0, 0, 1, 0, 0,
						 0, 0, 0, 1, 0};
		ColorMatrix colorMatrix = new ColorMatrix(array);*/
        ColorMatrix colorMatrix = new ColorMatrix(array);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);

        paint.setColorFilter(filter);
        canvas.drawBitmap(bitmap, 0, 0, paint);

        return grayBitmap;
    }
}