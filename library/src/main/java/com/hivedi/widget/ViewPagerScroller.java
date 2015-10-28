package com.hivedi.widget;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * Created by Kenumir on 2015-10-28.
 *
 */
public class ViewPagerScroller extends Scroller {

    private int scrollAnimTime;

    public ViewPagerScroller(Context context, Interpolator it, int scrollTime) {
        super(context, it);
        scrollAnimTime = scrollTime;
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, scrollAnimTime);
    }

}
