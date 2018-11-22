package com.misutesu.project.version2.bean.base;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

public abstract class GLShape {

    private static final int BYTE_FLOAT_SIZE = 4;

    private List<Float> mVertexList = new ArrayList<>();
    private FloatBuffer mFloatBuffer;

    public void addVertextList(List<Float> list) {
        mVertexList.addAll(list);
    }

    public void addShape(GLShape shape) {
        mVertexList.addAll(shape.mVertexList);
    }

    public void createBuffer() {
        float[] module = new float[mVertexList.size()];
        for (int i = 0; i < mVertexList.size(); i++) {
            module[i] = mVertexList.get(i);
        }
        mFloatBuffer = ByteBuffer
                .allocateDirect(module.length * BYTE_FLOAT_SIZE)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer()
                .put(module);
        mFloatBuffer.position(0);
    }

    public int getSize() {
        return mVertexList.size();
    }

    public FloatBuffer getFloatBuffer() {
        return mFloatBuffer;
    }
}
