package com.wujiaquan.demo.opengldemo.renderer;

import com.wujiaquan.demo.opengldemo.renderer.base.BaseRenderer;

import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class CubeGLRenderer extends BaseRenderer {

//    private float box[] = new float[]{
//            // FRONT
//            -1.0f, -1.0f, 1.0f,
//            1.0f, -1.0f, 1.0f,
//            1.0f, 1.0f, 1.0f,
//            -1.0f, 1.0f, 1.0f,
//            // BACK
//            -1.0f, -1.0f, -1.0f,
//            -1.0f, 1.0f, -1.0f,
//            1.0f, 1.0f, -1.0f,
//            1.0f, -1.0f, -1.0f,
//            // LEFT
//            -1.0f, -1.0f, 1.0f,
//            -1.0f, 1.0f, 1.0f,
//            -1.0f, 1.0f, -1.0f,
//            -1.0f, -1.0f, -1.0f,
//            // RIGHT
//            1.0f, -1.0f, -1.0f,
//            1.0f, 1.0f, -1.0f,
//            1.0f, 1.0f, 1.0f,
//            1.0f, -1.0f, 1.0f,
//            // TOP
//            -1.0f, 1.0f, 1.0f,
//            1.0f, 1.0f, 1.0f,
//            1.0f, 1.0f, -1.0f,
//            -1.0f, 1.0f, -1.0f,
//            // BOTTOM
//            -1.0f, -1.0f, 1.0f,
//            -1.0f, -1.0f, -1.0f,
//            1.0f, -1.0f, -1.0f,
//            1.0f, -1.0f, 1.0f,
//    };

    private float box[] = new float[]{
            // FRONT
            -0.5f, -0.5f, 0.5f,
            0.5f, -0.5f, 0.5f,
            -0.5f, 0.5f, 0.5f,
            0.5f, 0.5f, 0.5f,
            // BACK
            -0.5f, -0.5f, -0.5f,
            -0.5f, 0.5f, -0.5f,
            0.5f, -0.5f, -0.5f,
            0.5f, 0.5f, -0.5f,
            // LEFT
            -0.5f, -0.5f, 0.5f,
            -0.5f, 0.5f, 0.5f,
            -0.5f, -0.5f, -0.5f,
            -0.5f, 0.5f, -0.5f,
            // RIGHT
            0.5f, -0.5f, -0.5f,
            0.5f, 0.5f, -0.5f,
            0.5f, -0.5f, 0.5f,
            0.5f, 0.5f, 0.5f,
            // TOP
            -0.5f, 0.5f, 0.5f,
            0.5f, 0.5f, 0.5f,
            -0.5f, 0.5f, -0.5f,
            0.5f, 0.5f, -0.5f,
            // BOTTOM
            -0.5f, -0.5f, 0.5f,
            -0.5f, -0.5f, -0.5f,
            0.5f, -0.5f, 0.5f,
            0.5f, -0.5f, -0.5f,
    };

    private FloatBuffer mFloatBuffer;

    @Override
    protected void initFloatBuffer() {
        mFloatBuffer = getFloatBuffer(box);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        super.onDrawFrame(gl);

        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mFloatBuffer);//设置顶点数据
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

//        gl.glColor4f(1.0f, 0, 0, 1.0f);
//
//        gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, 4);
//        gl.glDrawArrays(GL10.GL_LINE_LOOP, 4, 4);
//
//        gl.glDrawArrays(GL10.GL_LINE_LOOP, 8, 4);
//        gl.glDrawArrays(GL10.GL_LINE_LOOP, 12, 4);
//
//        gl.glDrawArrays(GL10.GL_LINE_LOOP, 16, 4);
//        gl.glDrawArrays(GL10.GL_LINE_LOOP, 20, 4);

        gl.glColor4f(1.0f, 0, 0, 1.0f);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 4, 4);

        gl.glColor4f(0, 1.0f, 0, 1.0f);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 8, 4);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 12, 4);

        gl.glColor4f(0, 0, 1.0f, 1.0f);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 16, 4);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 20, 4);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
}
