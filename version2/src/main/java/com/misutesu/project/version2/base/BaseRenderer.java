package com.misutesu.project.version2.base;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import com.misutesu.project.version2.R;
import com.misutesu.project.version2.bean.GLResult;
import com.misutesu.project.version2.program.ColorProgram;
import com.misutesu.project.version2.program.TextureProgram;
import com.misutesu.project.version2.utils.Geometry;
import com.misutesu.project.version2.utils.MatrixHelper;
import com.misutesu.project.version2.utils.ShaderHelper;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public abstract class BaseRenderer implements GLSurfaceView.Renderer {

    //最小滑动距离
    private final int INVALID_MOVE_DISTANCE = 2;
    //旋转速度
    private final int ROTATE_SPEED = 6;
    //X轴旋转角度范围
    private final int[] ROTATE_X_RANGE = {0, 360};

    //最小缩放倍数
    private final float MIN_SCALE = 0.8f;
    //最大缩放倍数
    private final float MAX_SCALE = Float.MAX_VALUE;

    //缩放速度
    private final float SCALE_SPEED = 0.03f;
    //位移速度
    private final float TRANSLATE_SPEED = 0.05f;

    protected Context mContext;

    //屏幕比例
    protected float mRatio;
    //X轴旋转角度
    protected float mAngleX;
    //Y轴旋转角度
    protected float mAngleY;
    //Y轴当次旋转角度
    protected float mNowAngleY;
    //缩放倍数
    protected float mScaleSize = 1.0f;
    //位移X距离
    protected float mTranslateX;
    //位移Y距离
    protected float mTranslateY;

    //本次旋转矩阵
    private final float[] mNowRotateMatrix = new float[16];
    //上次旋转矩阵
    private final float[] mLastRotateMatrix = new float[16];

    //本次位移矩阵
    private final float[] mNowTranslateMatrix = new float[16];
    //上次位移矩阵
    private final float[] mLastTranslateMatrix = new float[16];

    protected final float[] mInvertedViewProjectionMatrix = new float[16];

    protected final float[] mViewMatrix = new float[16];
    protected final float[] mViewProjectionMatrix = new float[16];
    protected final float[] mModelViewProjectionMatrix = new float[16];

    protected final float[] mProjectionMatrix = new float[16];
    protected final float[] mModelMatrix = new float[16];

    protected ColorProgram mColorProgram;
    protected TextureProgram mTextureProgram;

    protected GLResult mGLResult = new GLResult();

    public BaseRenderer(Context context) {
        mContext = context;

        Matrix.setIdentityM(mLastRotateMatrix, 0);
        Matrix.setIdentityM(mLastTranslateMatrix, 0);
    }

    protected float[] setBackgroundColor() {
        return new float[]{0.0f, 0.0f, 0.0f};
    }

    protected float[] setBrightness() {
        return new float[]{0.8f, 0.8f, 0.8f};
    }

    protected float[] setLightLocation() {
        return new float[]{0.0f, 0.0f, 5.0f};
    }

    protected float[] setLightColor() {
        return new float[]{0.0f, 0.0f, 0.0f};
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        float[] color = setBackgroundColor();
        if (color.length != 3) {
            color[0] = color[1] = color[2] = 0.0f;
        }
        GLES20.glClearColor(color[0], color[1], color[2], 1.0f);

        //开启抗锯齿
        GLES20.glEnable(GL10.GL_MULTISAMPLE);

        //开启剔除
        GLES20.glEnable(GLES20.GL_CULL_FACE);
        //设置剔除模式-剔除背面
        GLES20.glCullFace(GLES20.GL_BACK);

        //启用深度缓存 根据坐标的远近自动隐藏被遮住的图形
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        //设置深度缓存值
        GLES20.glClearDepthf(1.0f);
        //设置深度缓存比较函数，GL_LEQUAL表示新的像素的深度缓存值小于等于当前像素的深度缓存值（通过gl.glClearDepthf(1.0f)设置）时通过深度测试
        GLES20.glDepthFunc(GLES20.GL_LEQUAL);

        mColorProgram = new ColorProgram(mContext);
        mTextureProgram = new TextureProgram(mContext);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        mRatio = (float) width / height;

        MatrixHelper.perspectiveM(mProjectionMatrix, 45, mRatio, 1f, 100f);
        //创建视图矩阵
        Matrix.setLookAtM(mViewMatrix, 0, 0f, 0f, 5.0f, 0f, 0f, 0f, 0f, 1f, 0f);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        //清除屏幕和深度缓存
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        //重置模型(变换)矩阵
        Matrix.setIdentityM(mModelMatrix, 0);
        Matrix.scaleM(mModelMatrix, 0, mScaleSize, mScaleSize, mScaleSize);
        rotateMatrix(mModelMatrix);
        translateMatrix(mModelMatrix);

        Matrix.multiplyMM(mModelViewProjectionMatrix, 0, mViewProjectionMatrix, 0, mModelMatrix, 0);
        Matrix.multiplyMM(mViewProjectionMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
        Matrix.invertM(mInvertedViewProjectionMatrix, 0, mViewProjectionMatrix, 0);

        mGLResult.draw(mModelViewProjectionMatrix, mModelMatrix, setLightLocation(), setLightColor(), setBrightness(), mColorProgram, mTextureProgram);
    }

    //合并模型矩阵和旋转矩阵
    protected void rotateMatrix(float[] modelMatrix) {
        Matrix.rotateM(modelMatrix, 0, mAngleX, 0.0f, 1.0f, 0.0f);

        Matrix.setIdentityM(mNowRotateMatrix, 0);
        Matrix.rotateM(mNowRotateMatrix, 0, mNowAngleY, 1.0f, 0.0f, 0.0f);

        mNowAngleY = 0.0f;

        Matrix.multiplyMM(mLastRotateMatrix, 0, mNowRotateMatrix, 0, mLastRotateMatrix, 0);
        Matrix.multiplyMM(modelMatrix, 0, mLastRotateMatrix, 0, modelMatrix, 0);
    }

    protected void translateMatrix(float[] modelMatrix) {
        Matrix.setIdentityM(mNowTranslateMatrix, 0);
        Matrix.translateM(mNowTranslateMatrix, 0, mTranslateX, 0, mTranslateY);

        mTranslateX = mTranslateY = 0.0f;

        Matrix.multiplyMM(mLastTranslateMatrix, 0, mNowTranslateMatrix, 0, mLastTranslateMatrix, 0);
        Matrix.multiplyMM(modelMatrix, 0, mLastTranslateMatrix, 0, modelMatrix, 0);
    }

    public float getRatio() {
        return mRatio;
    }

    public void rotate(float distanceX, float distanceY) {
        if (Math.abs(distanceX) > INVALID_MOVE_DISTANCE) {
            if (Math.abs(distanceX) > ROTATE_SPEED) {
                if (distanceX > 0) {
                    distanceX = ROTATE_SPEED;
                } else if (distanceX < 0) {
                    distanceX = -ROTATE_SPEED;
                }
            }
            mAngleX -= distanceX;
        }

        if (Math.abs(distanceY) > INVALID_MOVE_DISTANCE) {
            if (Math.abs(distanceY) > ROTATE_SPEED) {
                if (distanceY > 0) {
                    distanceY = ROTATE_SPEED;
                } else if (distanceY < 0) {
                    distanceY = -ROTATE_SPEED;
                }
            }

            if (mNowAngleY == 0.0f) {
                float angle = mAngleY - distanceY;
                if (angle < ROTATE_X_RANGE[0]) {
                    angle = ROTATE_X_RANGE[0];
                } else if (angle > ROTATE_X_RANGE[1]) {
                    angle = ROTATE_X_RANGE[1];
                }
                mNowAngleY = angle - mAngleY;
                mAngleY = angle;
            }
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

    public void translate(float translateX, float translateY) {
        if (translateX > 0) {
            if (translateX > TRANSLATE_SPEED) {
                translateX = TRANSLATE_SPEED;
            }
        } else if (translateX < 0) {
            if (translateX < -TRANSLATE_SPEED) {
                translateX = -TRANSLATE_SPEED;
            }
        }
        mTranslateX = translateX;
        if (translateY > 0) {
            if (translateY > TRANSLATE_SPEED) {
                translateY = TRANSLATE_SPEED;
            }
        } else if (translateY < 0) {
            if (translateY < -TRANSLATE_SPEED) {
                translateY = -TRANSLATE_SPEED;
            }
        }
        mTranslateY = translateY;
    }

    public void handleTouchPress(float normalizedX, float normalizedY) {

    }

    private void reductionTransformation(float scaleSize) {
        mTranslateX = 0.0f;
        mTranslateY = 0.0f;
        mScaleSize = scaleSize;
        mAngleX = 0.0f;
        mNowAngleY = 0.0f;
        mAngleY = 0.0f;
        Matrix.setIdentityM(mLastRotateMatrix, 0);
        Matrix.setIdentityM(mLastTranslateMatrix, 0);
    }

    protected Geometry.Ray convertNormalized2DPointToRay(float normalizedX, float normalizedY) {
        final float[] nearPointNdc = {normalizedX, normalizedY, -1, 1};
        final float[] farPointNdc = {normalizedX, normalizedY, 1, 1};

        final float[] nearPointWorld = new float[4];
        final float[] farPointWorld = new float[4];

        Matrix.multiplyMV(nearPointWorld, 0, mInvertedViewProjectionMatrix, 0, nearPointNdc, 0);
        Matrix.multiplyMV(farPointWorld, 0, mInvertedViewProjectionMatrix, 0, farPointNdc, 0);

        divideByW(nearPointWorld);
        divideByW(farPointWorld);

        Geometry.Point nearPointRay = new Geometry.Point(nearPointWorld[0], nearPointWorld[1], nearPointWorld[2]);
        Geometry.Point farPointRay = new Geometry.Point(farPointWorld[0], farPointWorld[1], farPointWorld[2]);

        return new Geometry.Ray(nearPointRay, Geometry.vectorBetween(nearPointRay, farPointRay));
    }

    /**
     * 撤销透视除法的影响
     *
     * @param vector
     */
    private void divideByW(float[] vector) {
        vector[0] /= vector[3];
        vector[1] /= vector[3];
        vector[2] /= vector[3];
    }
}
