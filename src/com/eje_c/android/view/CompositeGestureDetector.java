/*
 * Copyright (c) 2014 eje inc.
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.eje_c.android.view;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;

import com.eje_c.android.view.RotateGestureDetector.OnRotateGestureListener;

public class CompositeGestureDetector {
	public static interface OnCompositeGestureListener extends OnGestureListener, OnDoubleTapListener, OnScaleGestureListener, OnRotateGestureListener {
		void onUp(MotionEvent event);
	}

	public static class SimpleOnCompositeGestureListener implements OnCompositeGestureListener {

		@Override
		public boolean onDown(MotionEvent e) {
			return false;
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			return false;
		}

		@Override
		public void onLongPress(MotionEvent e) {
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
			return false;
		}

		@Override
		public void onShowPress(MotionEvent e) {
		}

		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			return false;
		}

		@Override
		public boolean onDoubleTap(MotionEvent e) {
			return false;
		}

		@Override
		public boolean onDoubleTapEvent(MotionEvent e) {
			return false;
		}

		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {
			return false;
		}

		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			return false;
		}

		@Override
		public boolean onScaleBegin(ScaleGestureDetector detector) {
			return true;
		}

		@Override
		public void onScaleEnd(ScaleGestureDetector detector) {
		}

		@Override
		public boolean onRotate(float degrees, float focusX, float focusY) {
			return false;
		}

		@Override
		public void onUp(MotionEvent event) {
		}
	}

	private GestureDetectorCompat gestureDetector;
	private ScaleGestureDetector scaleGestureDetector;
	private RotateGestureDetector rotateGestureDetector;
	private OnCompositeGestureListener listener;
	private MotionEvent currentEvent;
	private MotionEvent previousEvent;

	public CompositeGestureDetector(Context context, OnCompositeGestureListener listener) {
		this.listener = listener;
		gestureDetector = new GestureDetectorCompat(context, listener);
		scaleGestureDetector = new ScaleGestureDetector(context, listener);
		rotateGestureDetector = new RotateGestureDetector(context, listener);
	}

	public boolean onTouchEvent(MotionEvent event) {
		currentEvent = event;
		gestureDetector.onTouchEvent(event);
		scaleGestureDetector.onTouchEvent(event);
		rotateGestureDetector.onTouchEvent(event);

		if (event.getAction() == MotionEvent.ACTION_UP) {
			listener.onUp(event);
		}

		previousEvent = event;
		return true;
	}

	public MotionEvent getCurrentEvent() {
		return currentEvent;
	}

	public MotionEvent getPreviousEvent() {
		return previousEvent;
	}
}
