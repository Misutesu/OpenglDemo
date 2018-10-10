package com.wujiaquan.demo.opengldemo.renderer;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import com.wujiaquan.demo.opengldemo.OpenGLUtils;
import com.wujiaquan.demo.opengldemo.VertexData;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.glUniformMatrix4fv;

public class TriangleGLRenderer implements GLSurfaceView.Renderer {

    private static final String VERTEX_SHADER =
            "attribute vec4 a_Position;\n"
                    + "uniform mat4 u_Matrix;\n"
                    + "uniform mat4 u_ProMatrix;\n"
                    + "void main() {\n"
//                    + " gl_Position = a_Position;\n"
                    + " gl_Position = u_Matrix * u_ProMatrix * a_Position;\n"
                    + "}";

    private static final String FRAGMENT_SHADER =
            "precision mediump float;\n"
                    + "uniform vec4 u_Color;\n"
                    + "void main() {\n"
                    + " gl_FragColor = u_Color;\n"
                    + "}";

    private final static float[] VERTEX = {
            0, 1, 0,
            -1, -1, 0,
            1, -1, 0
    };

    private VertexData vertexData;

    private int aPosition;
    private int uColor;
    private int uMatrix;
    private int uProMatrix;

    protected float[] modelMatrix = new float[16];
    protected float[] viewMatrix = new float[16];
    protected float[] projectionMatrix = new float[16];
    protected float[] mvpMatrix = new float[16];

    public TriangleGLRenderer() {
        vertexData = new VertexData(VERTEX);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);

        int program = OpenGLUtils.buildProgram(VERTEX_SHADER, FRAGMENT_SHADER);
        if (program != 0) {
            GLES20.glUseProgram(program);

            aPosition = GLES20.glGetAttribLocation(program, "a_Position");
            uColor = GLES20.glGetUniformLocation(program, "u_Color");
            uMatrix = GLES20.glGetUniformLocation(program, "u_Matrix");
            uProMatrix = GLES20.glGetUniformLocation(program, "u_ProMatrix");

            GLES20.glEnableVertexAttribArray(aPosition);
            GLES20.glVertexAttribPointer(aPosition, 3, GLES20.GL_FLOAT, false, 0, vertexData.getVertexData());

            Matrix.setIdentityM(modelMatrix, 0);
        }
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);

        float aspectRatio = width > height ? (float) width / (float) height : (float) height / (float) width;

        if (width > height) {
            Matrix.orthoM(projectionMatrix, 0, -aspectRatio, aspectRatio, -1f, 1f, 0f, 10f);
        } else {
            Matrix.orthoM(projectionMatrix, 0, -1f, 1f, -aspectRatio, aspectRatio, 0f, 10f);
        }
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        GLES20.glUniform4f(uColor, 1.0f, 1.0f, 1.0f, 1.0f);

        GLES20.glUniformMatrix4fv(uMatrix, 1, false, modelMatrix, 0);
        GLES20.glUniformMatrix4fv(uProMatrix, 1, false, projectionMatrix, 0);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);
    }
}
