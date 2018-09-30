package com.wujiaquan.demo.opengldemo.renderer;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.wujiaquan.demo.opengldemo.OpenGLUtils;
import com.wujiaquan.demo.opengldemo.VertexData;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class PointGLRenderer implements GLSurfaceView.Renderer {

    private static final String VERTEX_SHADER =
            "attribute vec4 a_Position;\n"
                    + "void main()\n"
                    + "{\n"
                    + " gl_Position = a_Position;\n"
                    + " gl_PointSize = 30.0;\n"
                    + "}";

    private static final String FRAGMENT_SHADER =
            "precision mediump float;\n"
                    + "void main() {\n"
                    + " gl_FragColor = vec4(1, 0, 0, 1);\n"
                    + "}";

    private final static float[] VERTEX = {
            0f, 0f
    };

    private int aPosition;

    private VertexData vertexData;

    private int positionCount = 2;

    public PointGLRenderer() {
        vertexData = new VertexData(VERTEX);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        int program = OpenGLUtils.buildProgram(VERTEX_SHADER, FRAGMENT_SHADER);
        if (program != 0) {
            GLES20.glUseProgram(program);

            aPosition = GLES20.glGetAttribLocation(program, "a_Position");

        }
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
    }
}
