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
import android.view.MotionEvent;

public class RotateGestureDetector {
  public static interface OnRotateGestureListener {
    boolean onRotate(float degrees, float focusX, float focusY);
  }

  public static class SimpleOnRotateGestureDetector implements OnRotateGestureListener {
    @Override
    public boolean onRotate(float degrees, float focusX, float focusY) {
      return false;
    }
  }

  private static float RADIAN_TO_DEGREES = (float) (180.0 / Math.PI);
  // private Context context;
  private OnRotateGestureListener listener;
  private float prevX = 0.0f;
  private float prevY = 0.0f;
  private float prevTan;

  public RotateGestureDetector(Context context, OnRotateGestureListener listener) {
    // this.context = context;
    this.listener = listener;
  }

  public boolean onTouchEvent(MotionEvent event) {
    if (event.getPointerCount() == 2 && event.getActionMasked() == MotionEvent.ACTION_MOVE) {
      boolean result = true;
      float x = event.getX(1) - event.getX(0);
      float y = event.getY(1) - event.getY(0);
      float focusX = (event.getX(1) + event.getX(0)) * 0.5f;
      float focusY = (event.getY(1) + event.getY(0)) * 0.5f;
      float tan = (float) Math.atan2(y, x);

      if (prevX != 0.0f && prevY != 0.0f) {
        result = listener.onRotate((tan - prevTan) * RADIAN_TO_DEGREES, focusX, focusY);
      }

      prevX = x;
      prevY = y;
      prevTan = tan;
      return result;
    } else {
      prevX = prevY = prevTan = 0.0f;
      return true;
    }
  }
}
