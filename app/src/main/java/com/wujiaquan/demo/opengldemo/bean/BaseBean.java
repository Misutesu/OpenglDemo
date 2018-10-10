package com.wujiaquan.demo.opengldemo.bean;

public abstract class BaseBean {

    protected float centerX, centerY, centerZ;

    protected float[] mFloats;

    public void setCenterX(float centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(float centerY) {
        this.centerY = centerY;
    }

    public void setCenterZ(float centerZ) {
        this.centerZ = centerZ;
    }

    public void setCenter(float centerX, float centerY, float centerZ) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.centerZ = centerZ;
    }

    protected float[] convertFloats(Float[] f) {
        float[] floats = new float[f.length];
        for (int i = 0; i < f.length; i++) {
            floats[i] = f[i];
        }
        return floats;
    }

    public float[] getFloats() {
        return mFloats;
    }
}
