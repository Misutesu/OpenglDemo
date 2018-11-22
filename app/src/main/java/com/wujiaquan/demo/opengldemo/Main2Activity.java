package com.wujiaquan.demo.opengldemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.misutesu.project.version2.MyGLSurfaceView;
import com.misutesu.project.version2.MyRenderer;

public class Main2Activity extends AppCompatActivity {

    private MyGLSurfaceView mGLSurfaceView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (OpenGLUtils.checkSupport(this)) {
            mGLSurfaceView = new MyGLSurfaceView(this);
            mGLSurfaceView.setRenderer(new MyRenderer(this));
            setContentView(mGLSurfaceView);
        } else {
            Toast.makeText(this, "Device cannot use OpenGL 2.0", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mGLSurfaceView != null) {
            mGLSurfaceView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mGLSurfaceView != null) {
            mGLSurfaceView.onPause();
        }
    }
}
