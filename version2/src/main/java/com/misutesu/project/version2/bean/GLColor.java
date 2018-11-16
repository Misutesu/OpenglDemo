package com.misutesu.project.version2.bean;

import com.misutesu.project.version2.bean.base.GLMaterial;

public class GLColor extends GLMaterial {

    private final static int MAX_VALUE = 255;

    private int a;
    private int r;
    private int g;
    private int b;

    public GLColor(int a, int r, int g, int b) {
        this.a = a;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public GLColor(int r, int g, int b) {
        this.a = MAX_VALUE;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public GLColor(float a, float r, float g, float b) {
        this.a = (int) (MAX_VALUE * a);
        this.r = (int) (MAX_VALUE * r);
        this.g = (int) (MAX_VALUE * g);
        this.b = (int) (MAX_VALUE * b);
    }

    public GLColor(float r, float g, float b) {
        this.a = MAX_VALUE;
        this.r = (int) (MAX_VALUE * r);
        this.g = (int) (MAX_VALUE * g);
        this.b = (int) (MAX_VALUE * b);
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public boolean isSame(GLColor color) {
        if (a != color.getA()) return false;
        if (r != color.getR()) return false;
        if (g != color.getG()) return false;
        if (b != color.getB()) return false;
        return true;
    }

    public boolean isSame(int r, int g, int b) {
        return isSame(MAX_VALUE, r, g, b);
    }

    public boolean isSame(int a, int r, int g, int b) {
        if (this.a != a) return false;
        if (this.r != r) return false;
        if (this.g != g) return false;
        if (this.b != b) return false;
        return true;
    }
}
