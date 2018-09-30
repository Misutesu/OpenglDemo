package com.wujiaquan.demo.opengldemo.renderer.base;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public abstract class BaseRenderer implements GLSurfaceView.Renderer {

    private final String TAG = getClass().getSimpleName();

    private final int INVALID_MOVE_DISTANCE = 6;
    private final int ROTATE_SPEED = 3;

    private final float MIN_SCALE = 0.2f;
    private final float MAX_SCALE = Float.MAX_VALUE;

    private final float SCALE_SPEED = 0.025f;

    private float angleX;
    private float angleY;

    private float scaleSize = 1.0f;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        initFloatBuffer();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);//设置清屏时背景的颜色，R，G，B，A

        gl.glEnable(GL10.GL_DEPTH_TEST); //启用深度缓存 根据坐标的远近自动隐藏被遮住的图形（材料）
        gl.glEnable(GL10.GL_CULL_FACE);  //启用背面剪裁 根据函数glCullFace要求启用隐藏图形材料的面。
        gl.glClearDepthf(1.0f);    // 设置深度缓存值
        gl.glDepthFunc(GL10.GL_LEQUAL);  // 设置深度缓存比较函数，GL_LEQUAL表示新的像素的深度缓存值小于等于当前像素的深度缓存值（通过gl.glClearDepthf(1.0f)设置）时通过深度测试
        gl.glShadeModel(GL10.GL_SMOOTH);// 设置阴影模式GL_SMOOTH
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height); //设置视窗
        gl.glMatrixMode(GL10.GL_PROJECTION); // 设置投影矩阵
        gl.glLoadIdentity();  //设置矩阵为单位矩阵，相当于重置矩阵

        defaultPerspective(gl, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);// 清除屏幕和深度缓存

        gl.glMatrixMode(GL10.GL_MODELVIEW);   //切换至模型观察矩阵
        gl.glLoadIdentity();// 重置当前的模型观察矩阵

        GLU.gluLookAt(gl
                , 0, 0, 6
                , 0, 0, 0
                , 0, 1, 0);//设置视点和模型中心位置

        rotate(gl);
        scale(gl);
    }

    protected abstract void initFloatBuffer();

    protected FloatBuffer getFloatBuffer(Float[] module) {
        float[] floats = new float[module.length];
        for (int i = 0; i < module.length; i++) {
            floats[i] = module[i];
        }
        return getFloatBuffer(floats);
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

    protected void defaultPerspective(GL10 gl, int width, int height) {
        GLU.gluPerspective(gl, 45.0f, ((float) width) / height, 0.1f, 10f);//设置透视范围
    }

    protected final void rotate(GL10 gl) {
        gl.glRotatef(angleX, 0, 1, 0);
        gl.glRotatef(angleY, 1, 0, 0);
    }

    protected final void scale(GL10 gl) {
        gl.glScalef(scaleSize, scaleSize, scaleSize);
    }

    //开启旋转功能 在glVertexPointer之后glDrawArrays之前调用
    public void rotate(float distanceX, float distanceY) {
        if (Math.abs(distanceX) > INVALID_MOVE_DISTANCE) {
            angleX += distanceX < 0 ? ROTATE_SPEED : -ROTATE_SPEED;
        }
        if (Math.abs(distanceY) > INVALID_MOVE_DISTANCE) {
            angleY += distanceY < 0 ? ROTATE_SPEED : -ROTATE_SPEED;
        }
    }

    //开启缩放功能 在glVertexPointer之后glDrawArrays之前调用
    public void scale(float size) {
        if (size < 0 && scaleSize <= MIN_SCALE) {
            return;
        }
        if (size > 0 && scaleSize >= MAX_SCALE) {
            return;
        }
        scaleSize += size < 0f ? -SCALE_SPEED : SCALE_SPEED;
    }
}
