package com.wujiaquan.demo.opengldemo.bean;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public abstract class BaseOpenGLBean {
    protected float centerX, centerY, centerZ;

    protected float[] mFloats;

    protected FloatBuffer mFloatBuffer;

    protected float[] getFloats(Float[] f) {
        float[] floats = new float[f.length];
        for (int i = 0; i < f.length; i++) {
            floats[i] = f[i];
        }
        return floats;
    }

    protected FloatBuffer getFloatBuffer(float[] module) {
        for (int i = 0; i < module.length; i++) {
            switch (i % 3) {
                case 0:
                    module[i] += centerX;
                    break;
                case 1:
                    module[i] += centerY;
                    break;
                case 2:
                    module[i] += centerZ;
                    break;
            }
        }

        FloatBuffer floatBuffer = ByteBuffer
                .allocateDirect(module.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer()
                .put(module);
        floatBuffer.position(0);
        return floatBuffer;
    }

    public void setCenter(float centerX, float centerY, float centerZ) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.centerZ = centerZ;
    }
}
