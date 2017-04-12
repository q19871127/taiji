package com.vane.clickarea.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.vane.clickarea.R;

public class MenuViewItem extends ImageView {

    private Drawable drawable;
    private Bitmap bm;
    private int w;
    private int h;

    public MenuViewItem(Context context) {
        super(context);
    }

    public MenuViewItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MenuViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        //根据自定义属性 获取图片id
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.item);
        int id = a.getResourceId(R.styleable.item_img, 0);
        //获取到图片 但是并不给控件设置图片
        drawable = getResources().getDrawable(id);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //在onMeasure方法中才给控件设置图片
        //要根据按下的坐标获取图片的像素 如果发生放大或缩小的图片获取像素的时候会出问题
        //所以 构造方法获取到的 图片 要根据控件大小 转换成相应大小的bitmap防止发生崩溃

        //获取到控件的宽高
        w = MeasureSpec.getSize(widthMeasureSpec);
        h = MeasureSpec.getSize(heightMeasureSpec);

        //根据构造方法获取的图片 得到bitmap
        Bitmap.Config config =
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565;
        bm = Bitmap.createBitmap(w, h, config);
        Canvas canvas = new Canvas(bm);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        //给控件设置图片
        setImageBitmap(bm);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN://按下事件
                int x = (int) event.getX();
                int y = (int) event.getY();
                if (x < 0 || y < 0 || x >= w || y >= h) {
                    return false;
                }
                //根据按下的坐标获取当前坐标的 像素
                //如果是透明 就不拦截事件
                int pixel = bm.getPixel(x, y);
                if (Color.TRANSPARENT == pixel) {
                    return false;
                }
            break;
        }
        return super.onTouchEvent(event);
    }
}
