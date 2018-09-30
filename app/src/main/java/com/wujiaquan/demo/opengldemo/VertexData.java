package com.wujiaquan.demo.opengldemo;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class VertexData {

    private final int BYTES_PER_FLOAT = 4;

    private FloatBuffer vertexData;

    public VertexData(float[] vertex) {
        vertexData = ByteBuffer
                .allocateDirect(vertex.length * BYTES_PER_FLOAT)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer()
                .put(vertex);
        vertexData.position(0);
    }

    public FloatBuffer getVertexData() {
        return vertexData;
    }
}
