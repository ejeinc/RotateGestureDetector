# RotateGestureDetector

(and helper class CompositeGestureDetector.)

RotateGestureDetector can detect two fingure rotation. You can implement rotation function such as Google Map.

## Code sample

You can use RotateGestureDetector as same as [GestureDetector](http://developer.android.com/reference/android/view/GestureDetector.html).

```Java
private RotateGestureDetector mRotateGestureDetector;

...

// onCreate etc.
OnRotateGestureListener listener = new OnRotateGestureListener() {
  @Override
  public boolean onRotate(float degrees, float focusX, float focusY) {
    // implement your behavior here
    return true;
  }
};
mRotateGestureDetector = new RotateGestureDetector(context, listener);

...

@Override
public void onTouchEvent(MotionEvent e){
  mRotateGestureDetector.onTouchEvent(e);  
}
```

## How to use

Simply copy directories in `src` to your source tree.

## CompositeGestureDetector

If you want to use GestureDetector, ScaleGestureDetector and RotateGestureDetector together, it's time to use CompositeGestureDetector.

**NOTE : ** CompositeGestureDetector needs android-support-v4.jar.


```Java
private CompositeGestureDetector gd;

...

// onCreate etc.
SimpleOnCompositeGestureListener listener = new SimpleOnCompositeGestureListener() {
  @Override
  public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
    // put your code
    return true;
  }
  
  @Override
  public boolean onScale(ScaleGestureDetector detector) {
    // put your code
    return true;
  }
  
  @Override
  public boolean onRotate(float degrees, float focusX, float focusY) {
    // put your code
    return true;
  }
  
  // ... and other methods
};
gd = new CompositeGestureDetector(context, listener);

...

@Override
public void onTouchEvent(MotionEvent e){
  gd.onTouchEvent(e);  
}
```

## License

MIT License. See the LICENSE file for more info.
