package com.pvj.xlibrary.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Build;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;




/**
 *   图片旋转 类   旋转角度可传
 */
public class BitmapRotateTransformation extends BitmapTransformation {

    // 旋转角度
    private float rotateRotationAngle = 0f;

    // 上下文 旋转角度
    public BitmapRotateTransformation(Context context, float rotateRotationAngle) {
        super(context);

        this.rotateRotationAngle = rotateRotationAngle;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        Matrix matrix = new Matrix();
        matrix.postRotate(rotateRotationAngle);
        Bitmap result = Bitmap.createBitmap(toTransform, 0, 0, toTransform.getWidth(), toTransform.getHeight(), matrix, true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            result.setConfig(Bitmap.Config.ARGB_8888);
        }
        return result;
    }

    @Override
    public String getId() {
        return getClass().getName();
    }
}
