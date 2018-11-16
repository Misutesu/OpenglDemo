package com.misutesu.project.version2.program.base;

import android.content.Context;
import android.opengl.GLES20;

import com.misutesu.project.version2.utils.ShaderHelper;
import com.misutesu.project.version2.utils.TextResourceReader;

public abstract class BaseProgram {

    protected static final String U_MATRIX = "u_Matrix";
    protected static final String U_MMATRIX = "u_MMatrix";
    protected static final String U_LIGHTLOCATION = "u_LightLocation";
    protected static final String A_POSITION = "a_Position";
    protected static final String A_BRIGHTNESS = "a_Brightness";
    protected static final String A_LIGHTCOLOR = "a_LightColor";

    protected int mProgram;

    protected final int uMatrix;
    protected final int uMMatrix;
    protected final int uLightLocation;
    protected final int aPosition;
    protected final int aBrightness;
    protected final int aLightColor;

    public BaseProgram(Context context) {
        int[] shader = bindShader();
        mProgram = ShaderHelper.buildProgram(TextResourceReader.readTextFileFromResource(context, shader[0]), TextResourceReader.readTextFileFromResource(context, shader[1]));

        uMatrix = GLES20.glGetUniformLocation(mProgram, U_MATRIX);
        uMMatrix = GLES20.glGetUniformLocation(mProgram, U_MMATRIX);
        uLightLocation = GLES20.glGetUniformLocation(mProgram, U_LIGHTLOCATION);

        aPosition = GLES20.glGetAttribLocation(mProgram, A_POSITION);
        aPosition = GLES20.glGetAttribLocation(mProgram, A_POSITION);
    }

    protected abstract int[] bindShader();
}
