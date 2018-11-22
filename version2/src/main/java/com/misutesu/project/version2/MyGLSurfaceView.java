package com.misutesu.project.version2;

import android.content.Context;
import android.util.AttributeSet;

import com.misutesu.project.version2.base.BaseGLSurfaceView;

public class MyGLSurfaceView extends BaseGLSurfaceView<MyRenderer> {
    public MyGLSurfaceView(Context context) {
        super(context);
    }

    public MyGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
