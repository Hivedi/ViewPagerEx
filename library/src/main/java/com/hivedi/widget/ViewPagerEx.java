package com.hivedi.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import java.lang.reflect.Field;

/**
 * Created by Kenumir on 2015-10-28.
 *
 */
public class ViewPagerEx extends ViewPager {

    private boolean activated = true;
    private boolean useWrapContentToHeight = false;
    private int scrollAnimTime = 250;
    private ViewPagerScroller mViewPagerScroller;
    private Interpolator mScrollInterpolator;

    public ViewPagerEx(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ViewPagerEx(Context context) {
        super(context);
        init(context, null);
    }

    // http://stackoverflow.com/a/8038712/959086
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (useWrapContentToHeight) {
            int height = 0;
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                int h = child.getMeasuredHeight();
                if (h > height) height = h;
            }
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    public void setScrollAnimTime(int value) {
        scrollAnimTime = value;
        mViewPagerScroller = new ViewPagerScroller(getContext(), mScrollInterpolator, getScrollAnimTime());
        updateScroller();
    }

    public int getScrollAnimTime() {
        return scrollAnimTime;
    }

    public void activate() {
        activated = true;
    }

    public void deactivate() {
        activated = false;
    }

    public void setScrollInterpolator(Interpolator it) {
        mScrollInterpolator = it;
        mViewPagerScroller = new ViewPagerScroller(getContext(), mScrollInterpolator, getScrollAnimTime());
        updateScroller();
    }

    public Interpolator getScrollInterpolator() {
        return mScrollInterpolator;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (activated) {
            try {
                return super.onInterceptTouchEvent(event);
            } catch (Exception e) { // sometimes happens
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            return super.onTouchEvent(event);
        } catch (Exception e) {
            return true;
        }
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, new int[]{android.R.attr.layout_height});
            useWrapContentToHeight = ta.getLayoutDimension(0, LayoutParams.MATCH_PARENT) == LayoutParams.WRAP_CONTENT;
            ta.recycle();
        }

        mScrollInterpolator = new DecelerateInterpolator();
        mViewPagerScroller = new ViewPagerScroller(context, mScrollInterpolator, getScrollAnimTime());

        updateScroller();
    }

    private void updateScroller() {
        try {
            Class<?> viewpager = ViewPager.class;
            Field scroller = viewpager.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            scroller.set(this, mViewPagerScroller);
        } catch (Exception ignore)  {
        }
    }
}
