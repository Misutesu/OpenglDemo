package com.misutesu.project.version2.bean;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.List;

public class GLShape {

    private static final int BYTE_FLOAT_SIZE = 4;

    private List<Float> mVertexList;
    private FloatBuffer mFloatBuffer;

    public GLShape(List<Float> mVertexList) {
        this.mVertexList = mVertexList;
    }

    public void addShape(GLShape shape) {
        mVertexList.addAll(shape.mVertexList);
    }

    public void createBuffer() {
        mFloatBuffer = ByteBuffer.allocateDirect(mVertexList.size() * BYTE_FLOAT_SIZE)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        mFloatBuffer.position(0);
    }
}
