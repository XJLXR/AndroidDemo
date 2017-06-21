package com.example.carson_ho.toptabbar;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by XiongRun on 2017/1/12.
 */

public class MyViewPager extends ViewPager {

	private boolean scrollble = false;

	public MyViewPager(Context context) {
		super(context);
	}

	public MyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}


	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (!scrollble) {
			return true;
		}
		return super.onTouchEvent(ev);
	}


	public boolean isScrollble() {
		return scrollble;
	}

	public void setScrollble(boolean scrollble) {
		this.scrollble = scrollble;
	}
}
