package com.misutesu.project.version2.program;

import android.content.Context;
import android.opengl.GLES20;

import com.misutesu.project.version2.R;
import com.misutesu.project.version2.program.base.BaseProgram;

public class TextureProgram extends BaseProgram {

    private static final String U_TEXTURE_UNIT = "u_TextureUnit";

    private static final String A_TEXTURE_COORDINATES = "a_TextureCoordinates";

    private final int uTextureUnit;

    private final int aTextureCoordinatesLocation;

    public TextureProgram(Context context) {
        super(context);

        uTextureUnit = GLES20.glGetUniformLocation(mProgram, U_TEXTURE_UNIT);
        aTextureCoordinatesLocation = GLES20.glGetAttribLocation(mProgram, A_TEXTURE_COORDINATES);
    }

    @Override
    protected int[] bindShader() {
        return new int[]{R.raw.textrue_vertex_shader, R.raw.texture_fragment_shader};
    }

    public int getuTextureUnit() {
        return uTextureUnit;
    }

    public int getaTextureCoordinatesLocation() {
        return aTextureCoordinatesLocation;
    }
}
