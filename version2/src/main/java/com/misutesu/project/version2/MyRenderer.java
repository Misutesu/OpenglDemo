package com.misutesu.project.version2;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.misutesu.project.version2.base.BaseRenderer;
import com.misutesu.project.version2.bean.GLResult;
import com.misutesu.project.version2.bean.shape.GLCircle;
import com.misutesu.project.version2.bean.shape.GLCube;
import com.misutesu.project.version2.bean.shape.GLRectangle;
import com.misutesu.project.version2.bean.shape.base.GLFace;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


public class MyRenderer extends BaseRenderer {

    public MyRenderer(Context mContext) {
        super(mContext);
    }

    @Override
    protected float[] setBackgroundColor() {
        return new float[]{0.2f, 0.2f, 0.2f};
    }

    @Override
    protected float[] setLightLocation() {
        return new float[]{0.0f, 0.0f, 5.0f};
    }

    @Override
    protected float[] setLightColor() {
        return new float[]{0.6f, 0.6f, 0.6f};
    }

    @Override
    protected float[] setBrightness() {
        return new float[]{0.8f, 0.8f, 0.8f};
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        super.onSurfaceCreated(gl, config);

//        mGLResult.addShape(255, 255, 255, GLRectangle.create(0.0f, 0.0f, 0.0f, 2.0f, 2.0f, GLFace.DIRECTION_Y_POSITIVE));
//        mGLResult.addShape(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.texture)
//                , GLRectangle.create(0.0f, 0.0f, 0.0f, 2.0f, 2.0f, GLFace.DIRECTION_Y_NEGATIVE, true));

//        mGLResult.addShape(255, 255, 255, GLCircle.create(0.0f, 0.0f, 0.0f, 0.2f, 36, GLFace.DIRECTION_Y_POSITIVE));
//        mGLResult.addShape(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.texture)
//                , GLCircle.create(0.0f, 0.0f, 0.0f, 0.2f, 36, GLFace.DIRECTION_Y_NEGATIVE, true));

        mGLResult.addShape(255, 255, 255, GLCube.create(-0.5f, 0.0f, 0.0f, 0.4f, 0.6f, 0.8f));
        mGLResult.addShape(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.texture)
                , GLCube.create(0.5f, 0.0f, 0.0f, 0.4f, 0.6f, 0.8f, true));

        mGLResult.create();
    }
}
