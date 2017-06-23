package view.chenyu.com.freedom;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Method;

/**
 * Created by wxl19 on 2017/6/10.
 */
public class RadioView extends View implements View.OnLayoutChangeListener {
    public static final String TAG = "RadioView";

    private int screenWidth;//屏幕宽高
    private int screenHeight;

    private int mLastX;
    private int mLastY;
    private int mLeft;
    private int mTop;
    private int mRight;
    private int mBottom;
    private int navigationBarHeight;
    private int statusBarHeight;

    public RadioView(Context context) {
        super(context);
        setBitmap();
    }

    public RadioView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setBitmap();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.icon_addcomment_normal),0,0,new Paint());
    }

    private void setBitmap() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;//去除table栏高度

        navigationBarHeight = 0;
        Resources rs = getResources();
        int id = rs.getIdentifier("navigation_bar_height", "dimen", "android");
        if (id > 0 && checkDeviceHasNavigationBar()) {
            navigationBarHeight = rs.getDimensionPixelSize(id);
        }

        int resourceId = this.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        statusBarHeight = this.getResources().getDimensionPixelSize(resourceId)-4;

    }

    private boolean checkDeviceHasNavigationBar() {

        boolean hasNavigationBar = false;
        Resources rs = getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {
            Log.w(TAG, e);
        }
        return hasNavigationBar;
    }

    public RadioView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBitmap();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int measuredHeight = this.getMeasuredHeight();
        int measuredWidth = this.getMeasuredWidth();
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastX = (int) event.getRawX();
                mLastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:

                int dx = (int) event.getRawX() - mLastX;
                int dy = (int) event.getRawY() - mLastY;

                //得到控件坐标距离父控件原点(左上角，坐标（0，0）)的x轴距离
                mLeft = this.getLeft() + dx;
                //以此类推
                mTop = this.getTop() + dy;
                mRight = this.getRight() + dx;
                mBottom = this.getBottom() + dy;
                Log.e(TAG, "onTouchEvent: left:" + mLeft + ";top:" + mTop + ";right:" + mRight + ";bottom:" + mBottom);
                if (mLeft < 0) {//不使控件 超出屏幕的位置
                    mLeft = 0;
                    mRight = measuredHeight;
                }
                if (mRight > screenWidth) {
                    mRight = screenWidth;
                    mLeft = mRight - measuredWidth;
                }
                if (mTop < 0) {
                    mTop = 0;
                    mBottom = measuredHeight;
                }
                int temp = (screenHeight - navigationBarHeight) - statusBarHeight - measuredHeight;
                if (temp < mBottom) {
                    mBottom = temp;
                    mTop = mBottom - measuredHeight;
                }
                int marginRight = screenWidth - mRight;
                int marginBottom = screenHeight - navigationBarHeight - statusBarHeight - mBottom;

                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) this.getLayoutParams();
                params.rightMargin = marginRight;
                params.bottomMargin = marginBottom - measuredHeight;
                this.layout(mLeft, mTop, mRight, mBottom);

                mLastX = (int) event.getRawX();
                mLastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }


    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        Log.e(TAG, "onLayoutChange: View " + v + ", int mleft：" + left + ", int mtop：" + top + ", int mright" + right + ", int mbottom:" + bottom + ", int oldLeft:" + oldLeft + ", int oldTop:" + oldTop + ", int oldRight:" + oldRight + ", int oldBottom:" + oldBottom);
    }

}
