package com.misutesu.project.version2.program;

import android.content.Context;

import com.misutesu.project.version2.R;
import com.misutesu.project.version2.program.base.BaseProgram;

public class ColorProgram extends BaseProgram {
    public ColorProgram(Context context) {
        super(context);
    }

    @Override
    protected int[] bindShader() {
        return new int[]{R.raw.color_vertex_shader, R.raw.color_fragment_shader};
    }
}
