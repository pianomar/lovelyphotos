package com.omarhezi.lovelyphotos.General.Widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class FullScreenImageViewPager extends ViewPager {
    public FullScreenImageViewPager(@NonNull Context context) {
        super(context);
    }

    public FullScreenImageViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * There are some ViewGroups (ones that utilize onInterceptTouchEvent) that throw exceptions
     * when a PhotoView is placed within them, most notably ViewPager and DrawerLayout.
     * This is a framework issue that has not been resolved.
     * Simply catching the error resolves the issue
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}