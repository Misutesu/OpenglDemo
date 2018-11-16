package com.misutesu.project.version2.base;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;

public abstract class BaseGLSurfaceView<T extends BaseRenderer> extends GLSurfaceView implements View.OnTouchListener {

    //最大刷新帧数
    private final int MAX_FPS = 60;
    //双指滑动触发距离
    private final int MIN_TWO_MOVE = 20;

    //单指触控监听
    private GestureDetector mGestureDetector;
    //双指滑动监听
    private TwoFingerMoveDetector mTwoFingerMoveDetector;
    //双指缩放监听
    private ScaleGestureDetector mScaleGestureDetector;

    protected T mRenderer;

    protected boolean rendererSet = false;

    private long lastRefreshTime;

    public BaseGLSurfaceView(Context context) {
        super(context);
        init(context);
    }

    public BaseGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        setEGLContextClientVersion(2);

        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                //按下
                return super.onDown(e);
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                //单击
                final float normalizedX = (e.getX() / (float) getWidth()) * 2 - 1;
                final float normalizedY = -((e.getY() / (float) getHeight()) * 2 - 1);
                queueEvent(() -> mRenderer.handleTouchPress(normalizedX, normalizedY));
                return super.onSingleTapConfirmed(e);
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                //双击
                return super.onDoubleTap(e);
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                //单指滑动
                //e1 初次onDown Event
                //e1 当前 Event
                //distanceX distanceY 与上次onScroll e2的距离
                mRenderer.rotate(distanceX, distanceY);
                requestRender();
                return true;
            }
        });

        mTwoFingerMoveDetector = new TwoFingerMoveDetector(MIN_TWO_MOVE) {
            @Override
            public boolean onTwoFingerMove(MotionEvent e, int moveDirection, float distanceX, float distanceY) {
                if (moveDirection == TwoFingerMoveDetector.MOVE_DIRECTION_X) {
                    mRenderer.translate(distanceX, distanceY);
                    requestRender();
                    return true;
                }
                return false;
            }
        };

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

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int pointerCount = event.getPointerCount();
        if (pointerCount == 1) {
            mGestureDetector.onTouchEvent(event);
        } else {
            if (mTwoFingerMoveDetector.onTouchEvent(event)) {
                return true;
            }
            mScaleGestureDetector.onTouchEvent(event);
        }
        return true;
    }

    public void setRenderer(T renderer) {
        setEGLConfigChooser(new MSAAConfigChooser());
        super.setRenderer(renderer);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        mRenderer = renderer;
        rendererSet = true;
    }

    @Override
    public void onPause() {
        if (rendererSet) {
            super.onPause();
        }
    }

    @Override
    public void onResume() {
        if (rendererSet) {
            super.onResume();
        }
    }

    @Override
    public void requestRender() {
        long nowTime = System.currentTimeMillis();
        if (nowTime - lastRefreshTime >= (1000 / MAX_FPS)) {
            super.requestRender();
            lastRefreshTime = nowTime;
        }
    }

    private class MSAAConfigChooser implements EGLConfigChooser {
        @Override
        public EGLConfig chooseConfig(EGL10 egl, EGLDisplay display) {
            int attribs[] = {
                    EGL10.EGL_LEVEL, 0,
                    EGL10.EGL_RENDERABLE_TYPE, 4,
                    EGL10.EGL_COLOR_BUFFER_TYPE, EGL10.EGL_RGB_BUFFER,
                    EGL10.EGL_RED_SIZE, 8,
                    EGL10.EGL_GREEN_SIZE, 8,
                    EGL10.EGL_BLUE_SIZE, 8,
//                    EGL10.EGL_ALPHA_SIZE, 8,
                    EGL10.EGL_DEPTH_SIZE, 24,
                    EGL10.EGL_SAMPLE_BUFFERS, 1,
                    EGL10.EGL_SAMPLES, 4,  // 在这里修改MSAA的倍数，4就是4xMSAA，再往上开程序可能会崩
                    EGL10.EGL_NONE
            };
            EGLConfig[] configs = new EGLConfig[1];
            int[] configCounts = new int[1];
            egl.eglChooseConfig(display, attribs, configs, 1, configCounts);

            if (configCounts[0] == 0) {
                return null;
            } else {
                return configs[0];
            }
        }
    }
}
