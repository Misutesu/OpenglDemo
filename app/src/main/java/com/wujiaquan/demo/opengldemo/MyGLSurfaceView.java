package com.wujiaquan.demo.opengldemo;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.wujiaquan.demo.opengldemo.renderer.base.BaseRenderer;

public class MyGLSurfaceView extends GLSurfaceView implements View.OnTouchListener {

    private GestureDetector mGestureDetector;
    private ScaleGestureDetector mScaleGestureDetector;

    private BaseRenderer mRenderer;

    public MyGLSurfaceView(Context context) {
        super(context);
        init(context);
    }

    public MyGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                Log.d("TAG", "onDown");
                return super.onDown(e);
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                Log.d("TAG1", "onSingleTapConfirmed");
                return super.onSingleTapConfirmed(e);
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                Log.d("TAG1", "onDoubleTap");
                return super.onDoubleTap(e);
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                Log.d("TAG1", "onScroll " + distanceX + " " + distanceY);

                mRenderer.rotate(distanceX, distanceY);
                requestRender();

                return super.onScroll(e1, e2, distanceX, distanceY);
            }
        });

        mScaleGestureDetector = new ScaleGestureDetector(context, new ScaleGestureDetector.SimpleOnScaleGestureListener() {
            @Override
            public boolean onScaleBegin(ScaleGestureDetector detector) {
                return true;
            }

            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                mRenderer.scale(detector.getScaleFactor() - 1);
                requestRender();
                return true;
            }
        });

        setOnTouchListener(this);
    }

    public void setRenderer(BaseRenderer renderer) {
        super.setRenderer(renderer);
        mRenderer = renderer;
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int pointerCount = event.getPointerCount();
        if (pointerCount == 1) {
            mGestureDetector.onTouchEvent(event);
        } else {
            mScaleGestureDetector.onTouchEvent(event);
        }
        return true;
    }
}
