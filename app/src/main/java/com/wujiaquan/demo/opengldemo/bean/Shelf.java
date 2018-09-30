package com.wujiaquan.demo.opengldemo.bean;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

public class Shelf extends BaseOpenGLBean {
    private float width, length, height;
    private float columnThick, plateThick;

    private Column[] columns = new Column[4];
    private Plate[] plats = new Plate[4];

    public Shelf(float width, float length, float height, float columnThick, float plateThick) {
        this.width = width;
        this.length = length;
        this.height = height;
        this.columnThick = columnThick;
        this.plateThick = plateThick;

        for (int i = 0; i < columns.length; i++) {
            columns[i] = new Column(columnThick);
        }

        for (int i = 0; i < plats.length; i++) {
            plats[i] = new Plate(plateThick);
        }

        initFloat();
    }

    private void initFloat() {
        float[] columnLeftFront = {-length / 2, height / 2, width / 2};
        float[] columnLeftBack = {-length / 2, height / 2, -width / 2};
        float[] columnRightFront = {length / 2, height / 2, width / 2};
        float[] columnRightBack = {length / 2, height / 2, -width / 2};

        List<Float> list = new ArrayList<>();
        list.addAll(columns[0].create(columnLeftFront));
        list.addAll(columns[1].create(columnLeftBack));
        list.addAll(columns[2].create(columnRightFront));
        list.addAll(columns[3].create(columnRightBack));

        list.addAll(plats[0].create(-0.4f, columnThick, columnLeftFront, columnLeftBack, columnRightFront, columnRightBack));
        list.addAll(plats[1].create(-0.1f, columnThick, columnLeftFront, columnLeftBack, columnRightFront, columnRightBack));
        list.addAll(plats[2].create(0.2f, columnThick, columnLeftFront, columnLeftBack, columnRightFront, columnRightBack));
        list.addAll(plats[3].create(0.5f, columnThick, columnLeftFront, columnLeftBack, columnRightFront, columnRightBack));

        mFloats = getFloats(list.toArray(new Float[0]));
        mFloatBuffer = getFloatBuffer(mFloats);
    }

    public void draw(GL10 gl) {
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mFloatBuffer);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);

        for (int i = 0; i < mFloats.length / 12; i++) {
            gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, i * 4, 4);
        }

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }

    class Column {
        private float thick;

        Column(float thick) {
            this.thick = thick;
        }

        List<Float> create(float[] point) {
            Float[] f = {
                    //top
                    point[0] - thick, point[1], point[2] + thick,
                    point[0] + thick, point[1], point[2] + thick,
                    point[0] - thick, point[1], point[2] - thick,
                    point[0] + thick, point[1], point[2] - thick,
                    //bottom
                    point[0] - thick, -point[1], point[2] + thick,
                    point[0] - thick, -point[1], point[2] - thick,
                    point[0] + thick, -point[1], point[2] + thick,
                    point[0] + thick, -point[1], point[2] - thick,
                    //left
                    point[0] - thick, -point[1], point[2] - thick,
                    point[0] - thick, -point[1], point[2] + thick,
                    point[0] - thick, +point[1], point[2] - thick,
                    point[0] - thick, +point[1], point[2] + thick,
                    //right
                    point[0] + thick, -point[1], point[2] - thick,
                    point[0] + thick, +point[1], point[2] - thick,
                    point[0] + thick, -point[1], point[2] + thick,
                    point[0] + thick, +point[1], point[2] + thick,
                    //front
                    point[0] - thick, +point[1], point[2] + thick,
                    point[0] - thick, -point[1], point[2] + thick,
                    point[0] + thick, +point[1], point[2] + thick,
                    point[0] + thick, -point[1], point[2] + thick,
                    //bottom
                    point[0] - thick, -point[1], point[2] - thick,
                    point[0] - thick, +point[1], point[2] - thick,
                    point[0] + thick, -point[1], point[2] - thick,
                    point[0] + thick, +point[1], point[2] - thick
            };
            return Arrays.asList(f);
        }
    }

    class Plate {
        private float plateThick;

        Plate(float plateThick) {
            this.plateThick = plateThick;
        }

        List<Float> create(float height, float columnThick, float[] leftFront, float[] leftBack, float[] rightFront, float[] rightBack) {
            Float[] f = {
                    //front
                    leftFront[0] - columnThick, height + plateThick, leftFront[2] + columnThick,
                    leftFront[0] - columnThick, height - plateThick, leftFront[2] + columnThick,
                    rightFront[0] + columnThick, height + plateThick, leftFront[2] + columnThick,
                    rightFront[0] + columnThick, height - plateThick, leftFront[2] + columnThick,
                    //back
                    leftBack[0] - columnThick, height - plateThick, leftBack[2] - columnThick,
                    leftBack[0] - columnThick, height + plateThick, leftBack[2] - columnThick,
                    rightBack[0] + columnThick, height - plateThick, rightBack[2] - columnThick,
                    rightBack[0] + columnThick, height + plateThick, rightBack[2] - columnThick,
                    //left
                    leftBack[0] - columnThick, height + plateThick, leftBack[2] - columnThick,
                    leftBack[0] - columnThick, height - plateThick, leftBack[2] - columnThick,
                    leftFront[0] - columnThick, height + plateThick, leftFront[2] + columnThick,
                    leftFront[0] - columnThick, height - plateThick, leftFront[2] + columnThick,
                    //right
                    rightFront[0] + columnThick, height + plateThick, rightFront[2] + columnThick,
                    rightFront[0] + columnThick, height - plateThick, rightFront[2] + columnThick,
                    rightBack[0] + columnThick, height + plateThick, rightBack[2] - columnThick,
                    rightBack[0] + columnThick, height - plateThick, rightBack[2] - columnThick,
                    //top
                    leftBack[0] - columnThick, height + plateThick, leftBack[2] - columnThick,
                    leftFront[0] - columnThick, height + plateThick, leftFront[2] + columnThick,
                    rightBack[0] + columnThick, height + plateThick, rightBack[2] - columnThick,
                    rightFront[0] + columnThick, height + plateThick, rightFront[2] + columnThick,
                    //bottom
                    leftFront[0] - columnThick, height - plateThick, leftFront[2] + columnThick,
                    leftBack[0] - columnThick, height - plateThick, leftBack[2] - columnThick,
                    rightFront[0] + columnThick, height - plateThick, rightFront[2] + columnThick,
                    rightBack[0] + columnThick, height - plateThick, rightBack[2] - columnThick,
            };
            return Arrays.asList(f);
        }
    }
}
