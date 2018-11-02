package com.wujiaquan.demo.opengldemo.renderer.base;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public abstract class BaseRenderer2 implements GLSurfaceView.Renderer {

    private final String TAG = getClass().getSimpleName();

    private final int INVALID_MOVE_DISTANCE = 6;
    private final int ROTATE_SPEED = 4;

    private final float MIN_SCALE = 0.2f;
    private final float MAX_SCALE = Float.MAX_VALUE;

    private final float SCALE_SPEED = 0.05f;

    protected final float[] mViewMatrix = new float[16];
    protected final float[] mProjectMatrix = new float[16];
    protected final float[] mMVPMatrix = new float[16];
    protected final float[] mModelMatrix = new float[16];

    //本次旋转矩阵
    private final float[] mNowRotateMatrix = new float[16];
    //上次旋转矩阵
    private final float[] mLastRotateMatrix = new float[16];

    protected Context mContext;

    protected float mRatio;

    protected float mAngleX;
    protected float mAngleY;
    protected float mScaleSize = 1.0f;

    public BaseRenderer2(Context context) {
        mContext = context;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);

        Matrix.setIdentityM(mLastRotateMatrix, 0);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
    }

    protected FloatBuffer getFloatBuffer(float[] module) {
        FloatBuffer floatBuffer = ByteBuffer
                .allocateDirect(module.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer()
                .put(module);
        floatBuffer.position(0);
        return floatBuffer;
    }

    //合并模型矩阵和旋转矩阵
    protected void rotateMatrix(float[] modelMatrix) {
        Matrix.setIdentityM(mNowRotateMatrix, 0);
        Matrix.rotateM(mNowRotateMatrix, 0, mAngleX, 0.0f, 1.0f, 0.0f);
        Matrix.rotateM(mNowRotateMatrix, 0, mAngleY, 1.0f, 0.0f, 0.0f);

        mAngleX = mAngleY = 0.0f;

        Matrix.multiplyMM(mLastRotateMatrix, 0, mNowRotateMatrix, 0, mLastRotateMatrix, 0);
        Matrix.multiplyMM(modelMatrix, 0, mLastRotateMatrix, 0, modelMatrix, 0);
    }

    public void rotate(float distanceX, float distanceY) {
        if (Math.abs(distanceX) > INVALID_MOVE_DISTANCE) {
            mAngleX = distanceX < 0 ? ROTATE_SPEED : -ROTATE_SPEED;
        }
        if (Math.abs(distanceY) > INVALID_MOVE_DISTANCE) {
            mAngleY = distanceY < 0 ? ROTATE_SPEED : -ROTATE_SPEED;
        }
    }

    public void scale(float size) {
        if (size < 0 && mScaleSize <= MIN_SCALE) {
            return;
        }
        if (size > 0 && mScaleSize >= MAX_SCALE) {
            return;
        }
        mScaleSize += size < 0f ? -SCALE_SPEED : SCALE_SPEED;
    }
}
