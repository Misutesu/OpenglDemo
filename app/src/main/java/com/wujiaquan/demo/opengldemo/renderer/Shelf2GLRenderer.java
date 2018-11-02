package com.wujiaquan.demo.opengldemo.renderer;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;

import com.wujiaquan.demo.opengldemo.OpenGLUtils;
import com.wujiaquan.demo.opengldemo.R;
import com.wujiaquan.demo.opengldemo.bean.Shelf2;
import com.wujiaquan.demo.opengldemo.renderer.base.BaseRenderer2;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class Shelf2GLRenderer extends BaseRenderer2 {

    private static final String VERTEX_SHADER =
            "attribute vec4 a_Position;\n"
                    + "attribute vec2 a_TextureCoordinates;\n"
                    + "uniform mat4 u_Matrix;\n"
                    + "uniform mat4 u_ModelMatrix;\n"
                    + "varying vec2 v_TextureCoordinates;\n"
                    + "void main() {\n"
                    + "v_TextureCoordinates = a_TextureCoordinates;\n"
                    + "gl_Position = u_Matrix * a_Position;\n"
                    + "}";

    private static final String FRAGMENT_SHADER =
            "precision mediump float;\n"
//                    + "uniform vec4 u_Color;\n"
                    + "uniform sampler2D u_TextureUnit;\n"
                    + "varying vec2 v_TextureCoordinates;\n"
                    + "void main() {\n"
//                    + " gl_FragColor = u_Color;\n"
                    + "gl_FragColor = texture2D(u_TextureUnit,v_TextureCoordinates);\n"
                    + "}";

    private float[] TEXTURE;

    private Shelf2 mShelf;

    private FloatBuffer mVertexArray;
    private FloatBuffer mTextureArray;

    private int[] mTextureIds = new int[2];

    private int aPosition;
    //    private int uColor;
    private int uMatrix;
    private int uTextureUnit;
    private int aTextureCoordinates;

    public Shelf2GLRenderer(Context context) {
        super(context);
        mShelf = new Shelf2(0.8f, 2f, 1.6f, 0.02f, 0.02f);
        mShelf.setCenter(0.0f, 0.1f, 0.0f);

        TEXTURE = new float[mShelf.getFloats().length];
        for (int i = 0; i < TEXTURE.length; i += 3 * 4) {
            TEXTURE[i + 0] = 0f;
            TEXTURE[i + 1] = 0f;
            TEXTURE[i + 2] = 0f;

            TEXTURE[i + 3] = 0f;
            TEXTURE[i + 4] = 1f;
            TEXTURE[i + 5] = 0f;

            TEXTURE[i + 6] = 1f;
            TEXTURE[i + 7] = 0f;
            TEXTURE[i + 8] = 0f;

            TEXTURE[i + 9] = 1f;
            TEXTURE[i + 10] = 1f;
            TEXTURE[i + 11] = 0f;
        }

        mVertexArray = getFloatBuffer(mShelf.getFloats());
        mTextureArray = getFloatBuffer(TEXTURE);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        super.onSurfaceCreated(gl, config);

        int program = OpenGLUtils.buildProgram(VERTEX_SHADER, FRAGMENT_SHADER);
        if (program != 0) {
            GLES20.glUseProgram(program);

            aPosition = GLES20.glGetAttribLocation(program, "a_Position");
//            uColor = GLES20.glGetUniformLocation(program, "u_Color");
            uMatrix = GLES20.glGetUniformLocation(program, "u_Matrix");
            uTextureUnit = GLES20.glGetUniformLocation(program, "u_TextureUnit");
            aTextureCoordinates = GLES20.glGetAttribLocation(program, "a_TextureCoordinates");

            mTextureIds[0] = OpenGLUtils.loadTexture(mContext, R.drawable.texture);
            mTextureIds[1] = OpenGLUtils.loadTexture(mContext, R.drawable.lgq);

            GLES20.glUniform1i(uTextureUnit, 0);

            IntBuffer intBuffer = IntBuffer.allocate(1);
            GLES20.glGetIntegerv(GLES20.GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS, intBuffer);
        }
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        super.onSurfaceChanged(gl, width, height);
        mRatio = (float) width / height;
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        super.onDrawFrame(gl);

        Matrix.frustumM(mProjectMatrix, 0, -mRatio / mScaleSize, mRatio / mScaleSize, -1.0f / mScaleSize, 1.0f / mScaleSize, 1f, 1000.0f);

        Matrix.setLookAtM(mViewMatrix, 0, 0.0f, 0.0f, 5.0f, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        Matrix.setIdentityM(mModelMatrix, 0);
        //scale
//        Matrix.scaleM(mModelMatrix, 0, mScaleSize, mScaleSize, mScaleSize);
        //rotate
        rotateMatrix(mModelMatrix);

        Matrix.multiplyMM(mMVPMatrix, 0, mViewMatrix, 0, mModelMatrix, 0);
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectMatrix, 0, mMVPMatrix, 0);

        GLES20.glUniformMatrix4fv(uMatrix, 1, false, mMVPMatrix, 0);

        //color
//        GLES20.glUniform4f(uColor, 1.0f, 1.0f, 1.0f, 1.0f);

        GLES20.glVertexAttribPointer(aPosition, 3, GLES20.GL_FLOAT, false, 0, mVertexArray);
        GLES20.glEnableVertexAttribArray(aPosition);

        GLES20.glVertexAttribPointer(aTextureCoordinates, 3, GLES20.GL_FLOAT, false, 0, mTextureArray);
        GLES20.glEnableVertexAttribArray(aTextureCoordinates);

        long time = System.currentTimeMillis();

        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureIds[0]);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, mShelf.getFloats().length / 3);

        Log.d("TAG", "=============================================");
        Log.d("TAG", "time : " + (System.currentTimeMillis() - time));

        GLES20.glDisableVertexAttribArray(aPosition);
        GLES20.glDisableVertexAttribArray(aTextureCoordinates);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
    }
}
