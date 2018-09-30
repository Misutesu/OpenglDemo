package com.wujiaquan.demo.opengldemo.renderer;

import com.wujiaquan.demo.opengldemo.bean.Shelf;
import com.wujiaquan.demo.opengldemo.renderer.base.BaseRenderer;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class ShelfGLRenderer extends BaseRenderer {

    private float floor[] = new float[]{
            //Top
            -1.0f, -0.8f, 1.0f,
            1.0f, -0.8f, 1.0f,
            -1.0f, -0.8f, -1.0f,
            1.0f, -0.8f, -1.0f,
            //Bottom
            -1.0f, -0.8f, 1.0f,
            -1.0f, -0.8f, -1.0f,
            1.0f, -0.8f, 1.0f,
            1.0f, -0.8f, -1.0f,
    };

    private Shelf mShelf;

    private FloatBuffer mFloatBuffer;

    @Override
    protected void initFloatBuffer() {
        mFloatBuffer = getFloatBuffer(floor);

        mShelf = new Shelf(1.0f, 1.6f, 1.6f, 0.05f, 0.05f);
        mShelf.setCenter(0.1f, 0.1f, 0.0f);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        super.onDrawFrame(gl);

        //draw shelf
        mShelf.draw(gl);

        //draw floor
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mFloatBuffer);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 4, 4);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
}
