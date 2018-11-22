package com.misutesu.project.version2.program.base;

import android.content.Context;
import android.opengl.GLES20;

import com.misutesu.project.version2.utils.ShaderHelper;
import com.misutesu.project.version2.utils.TextResourceReader;

public abstract class BaseProgram {

    protected static final String U_MATRIX = "u_Matrix";
    protected static final String U_MMATRIX = "u_MMatrix";
    protected static final String U_LIGHT_LOCATION = "u_LightLocation";
    protected static final String U_BRIGHTNESS = "u_Brightness";
    protected static final String U_LIGHT_COLOR = "u_LightColor";

    protected static final String A_POSITION = "a_Position";

    protected int mProgram;

    protected final int uMatrix;
    protected final int uMMatrix;
    protected final int uLightLocation;
    protected final int uBrightness;
    protected final int uLightColor;

    protected final int aPosition;

    public BaseProgram(Context context) {
        int[] shader = bindShader();
        mProgram = ShaderHelper.buildProgram(TextResourceReader.readTextFileFromResource(context, shader[0])
                , TextResourceReader.readTextFileFromResource(context, shader[1]));

        uMatrix = GLES20.glGetUniformLocation(mProgram, U_MATRIX);
        uMMatrix = GLES20.glGetUniformLocation(mProgram, U_MMATRIX);
        uLightLocation = GLES20.glGetUniformLocation(mProgram, U_LIGHT_LOCATION);
        uBrightness = GLES20.glGetUniformLocation(mProgram, U_BRIGHTNESS);
        uLightColor = GLES20.glGetUniformLocation(mProgram, U_LIGHT_COLOR);

        aPosition = GLES20.glGetAttribLocation(mProgram, A_POSITION);
    }

    protected abstract int[] bindShader();

    public int getProgram() {
        return mProgram;
    }

    public int getaPosition() {
        return aPosition;
    }

    public int getuMatrix() {
        return uMatrix;
    }

    public int getuMMatrix() {
        return uMMatrix;
    }

    public int getuLightLocation() {
        return uLightLocation;
    }

    public int getuBrightness() {
        return uBrightness;
    }

    public int getuLightColor() {
        return uLightColor;
    }
}
