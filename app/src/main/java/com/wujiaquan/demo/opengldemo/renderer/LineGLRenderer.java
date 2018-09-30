package com.wujiaquan.demo.opengldemo.renderer;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.wujiaquan.demo.opengldemo.OpenGLUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class LineGLRenderer implements GLSurfaceView.Renderer {

    private static final int BYTES_PER_FLOAT = 4;

    private static final String VERTEX_SHADER =
            "attribute vec4 a_Position;\n"
                    + "void main(){\n"
                    + " gl_Position = a_Position;\n"
                    + "}";

    private static final String FRAGMENT_SHADER =
            "precision mediump float;\n"
                    + "void main() {\n"
                    + " gl_FragColor = vec4(1, 0, 0, 1);\n"
                    + "}";

    private final static float[] VERTEX = {
            -0.5f, 0.5f,
            0.5f, -0.5f
    };

    private FloatBuffer vertexData;

    private int vPosition;

    public LineGLRenderer() {
        vertexData = ByteBuffer
                .allocateDirect(VERTEX.length * BYTES_PER_FLOAT)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer()
                .put(VERTEX);
        vertexData.position(0);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0f, 0f, 0f, 0f);

        int program = OpenGLUtils.buildProgram(VERTEX_SHADER, FRAGMENT_SHADER);
        if (program != 0) {
            GLES20.glUseProgram(program);

            vPosition = GLES20.glGetAttribLocation(program, "vPosition");

            GLES20.glEnableVertexAttribArray(vPosition);
            GLES20.glVertexAttribPointer(vPosition, 3, GLES20.GL_FLOAT, false, 12, vertexData);
        }
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 2);
    }
}
