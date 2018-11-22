package com.misutesu.project.version2.program;

import android.content.Context;
import android.opengl.GLES20;

import com.misutesu.project.version2.R;
import com.misutesu.project.version2.program.base.BaseProgram;

public class ColorProgram extends BaseProgram {

    private static final String U_COLOR = "u_Color";

    private final int uColor;

    public ColorProgram(Context context) {
        super(context);

        uColor = GLES20.glGetUniformLocation(mProgram, U_COLOR);
    }

    @Override
    protected int[] bindShader() {
        return new int[]{R.raw.color_vertex_shader, R.raw.color_fragment_shader};
    }

    public int getuColor() {
        return uColor;
    }
}
