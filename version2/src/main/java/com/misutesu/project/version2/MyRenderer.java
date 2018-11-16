package com.misutesu.project.version2;

import android.content.Context;
import android.opengl.Matrix;

import com.misutesu.project.version2.base.BaseRenderer;
import com.misutesu.project.version2.utils.MatrixHelper;

import javax.microedition.khronos.opengles.GL10;


public class MyRenderer extends BaseRenderer {

    public MyRenderer(Context mContext) {
        super(mContext);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        super.onSurfaceChanged(gl, width, height);
        MatrixHelper.perspectiveM(mProjectionMatrix, 45, mRatio, 1f, 100f);
        //创建视图矩阵
        Matrix.setLookAtM(mViewMatrix, 0, 0f, 0f, 5.0f, 0f, 0f, 0f, 0f, 1f, 0f);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        super.onDrawFrame(gl);

        //重置模型(变换)矩阵
        Matrix.setIdentityM(mModelMatrix, 0);
        Matrix.scaleM(mModelMatrix, 0, mScaleSize, mScaleSize, mScaleSize);
        rotateMatrix(mModelMatrix);
        translateMatrix(mModelMatrix);

        Matrix.multiplyMM(mModelViewProjectionMatrix, 0, mViewProjectionMatrix, 0, mModelMatrix, 0);
        Matrix.multiplyMM(mViewProjectionMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
        Matrix.invertM(mInvertedViewProjectionMatrix, 0, mViewProjectionMatrix, 0);
    }
}
