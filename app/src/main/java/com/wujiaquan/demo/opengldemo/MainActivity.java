package com.wujiaquan.demo.opengldemo;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.wujiaquan.demo.opengldemo.renderer.CubeGLRenderer;
import com.wujiaquan.demo.opengldemo.renderer.ShelfGLRenderer;
import com.wujiaquan.demo.opengldemo.renderer.TriangleGLRenderer;

public class MainActivity extends AppCompatActivity {

    private MyGLSurfaceView mGLSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("TAG", "check OpenGL 2.0 support : " + OpenGLUtils.checkSupport(this));

        if (OpenGLUtils.checkSupport(this)) {
            mGLSurfaceView = new MyGLSurfaceView(this);

//            mGLSurfaceView.setRenderer(new CubeGLRenderer());
            mGLSurfaceView.setRenderer(new ShelfGLRenderer());

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
