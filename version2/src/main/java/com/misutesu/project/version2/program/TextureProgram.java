package com.misutesu.project.version2.program;

import android.content.Context;

import com.misutesu.project.version2.R;
import com.misutesu.project.version2.program.base.BaseProgram;

public class TextureProgram extends BaseProgram {
    public TextureProgram(Context context) {
        super(context);
    }

    @Override
    protected int[] bindShader() {
        return new int[]{R.raw.textrue_vertex_shader, R.raw.texture_fragment_shader};
    }
}
