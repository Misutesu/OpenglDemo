package com.misutesu.project.version2.bean.shape;

import com.misutesu.project.version2.bean.shape.base.GLFace;

import java.util.ArrayList;
import java.util.List;

public class GLRectangle extends GLFace {

    public static GLRectangle create(float centerX, float centerY, float centerZ, float width, float height, int direction) {
        return create(centerX, centerY, centerZ, width, height, direction, false);
    }

    public static GLRectangle create(float centerX, float centerY, float centerZ, float width, float height, int direction, boolean isWithTexture) {
        GLRectangle glRectangle = new GLRectangle();
        List<Float> list = new ArrayList<>();

        switch (direction) {
            case DIRECTION_X_POSITIVE:
                list.add(centerX);
                list.add(centerY + height / 2);
                list.add(centerZ + width / 2);
                if (isWithTexture) {
                    list.add(0.0f);
                    list.add(1.0f);
                }
                list.add(centerX);
                list.add(centerY - height / 2);
                list.add(centerZ + width / 2);
                if (isWithTexture) {
                    list.add(0.0f);
                    list.add(0.0f);
                }
                list.add(centerX);
                list.add(centerY - height / 2);
                list.add(centerZ - width / 2);
                if (isWithTexture) {
                    list.add(1.0f);
                    list.add(0.0f);
                }
                list.add(centerX);
                list.add(centerY - height / 2);
                list.add(centerZ - width / 2);
                if (isWithTexture) {
                    list.add(1.0f);
                    list.add(0.0f);
                }
                list.add(centerX);
                list.add(centerY + height / 2);
                list.add(centerZ - width / 2);
                if (isWithTexture) {
                    list.add(1.0f);
                    list.add(1.0f);
                }
                list.add(centerX);
                list.add(centerY + height / 2);
                list.add(centerZ + width / 2);
                if (isWithTexture) {
                    list.add(0.0f);
                    list.add(1.0f);
                }
                break;
            case DIRECTION_X_NEGATIVE:
                list.add(centerX);
                list.add(centerY + height / 2);
                list.add(centerZ + width / 2);
                if (isWithTexture) {
                    list.add(0.0f);
                    list.add(1.0f);
                }
                list.add(centerX);
                list.add(centerY + height / 2);
                list.add(centerZ - width / 2);
                if (isWithTexture) {
                    list.add(1.0f);
                    list.add(1.0f);
                }
                list.add(centerX);
                list.add(centerY - height / 2);
                list.add(centerZ - width / 2);
                if (isWithTexture) {
                    list.add(1.0f);
                    list.add(0.0f);
                }
                list.add(centerX);
                list.add(centerY - height / 2);
                list.add(centerZ - width / 2);
                if (isWithTexture) {
                    list.add(1.0f);
                    list.add(0.0f);
                }
                list.add(centerX);
                list.add(centerY - height / 2);
                list.add(centerZ + width / 2);
                if (isWithTexture) {
                    list.add(0.0f);
                    list.add(0.0f);
                }
                list.add(centerX);
                list.add(centerY + height / 2);
                list.add(centerZ + width / 2);
                if (isWithTexture) {
                    list.add(0.0f);
                    list.add(1.0f);
                }
                break;
            case DIRECTION_Y_POSITIVE:
                list.add(centerX - width / 2);
                list.add(centerY);
                list.add(centerZ - height / 2);
                if (isWithTexture) {
                    list.add(0.0f);
                    list.add(1.0f);
                }
                list.add(centerX - width / 2);
                list.add(centerY);
                list.add(centerZ + height / 2);
                if (isWithTexture) {
                    list.add(0.0f);
                    list.add(0.0f);
                }
                list.add(centerX + width / 2);
                list.add(centerY);
                list.add(centerZ + height / 2);
                if (isWithTexture) {
                    list.add(1.0f);
                    list.add(0.0f);
                }
                list.add(centerX + width / 2);
                list.add(centerY);
                list.add(centerZ + height / 2);
                if (isWithTexture) {
                    list.add(1.0f);
                    list.add(0.0f);
                }
                list.add(centerX + width / 2);
                list.add(centerY);
                list.add(centerZ - height / 2);
                if (isWithTexture) {
                    list.add(1.0f);
                    list.add(1.0f);
                }
                list.add(centerX - width / 2);
                list.add(centerY);
                list.add(centerZ - height / 2);
                if (isWithTexture) {
                    list.add(0.0f);
                    list.add(1.0f);
                }
                break;
            case DIRECTION_Y_NEGATIVE:
                list.add(centerX - width / 2);
                list.add(centerY);
                list.add(centerZ - height / 2);
                if (isWithTexture) {
                    list.add(0.0f);
                    list.add(1.0f);
                }
                list.add(centerX + width / 2);
                list.add(centerY);
                list.add(centerZ - height / 2);
                if (isWithTexture) {
                    list.add(1.0f);
                    list.add(1.0f);
                }
                list.add(centerX + width / 2);
                list.add(centerY);
                list.add(centerZ + height / 2);
                if (isWithTexture) {
                    list.add(1.0f);
                    list.add(0.0f);
                }
                list.add(centerX + width / 2);
                list.add(centerY);
                list.add(centerZ + height / 2);
                if (isWithTexture) {
                    list.add(1.0f);
                    list.add(0.0f);
                }
                list.add(centerX - width / 2);
                list.add(centerY);
                list.add(centerZ + height / 2);
                if (isWithTexture) {
                    list.add(0.0f);
                    list.add(0.0f);
                }
                list.add(centerX - width / 2);
                list.add(centerY);
                list.add(centerZ - height / 2);
                if (isWithTexture) {
                    list.add(0.0f);
                    list.add(1.0f);
                }
                break;
            case DIRECTION_Z_POSITIVE:
                list.add(centerX - width / 2);
                list.add(centerY + height / 2);
                list.add(centerZ);
                if (isWithTexture) {
                    list.add(0.0f);
                    list.add(1.0f);
                }
                list.add(centerX - width / 2);
                list.add(centerY - height / 2);
                list.add(centerZ);
                if (isWithTexture) {
                    list.add(0.0f);
                    list.add(0.0f);
                }
                list.add(centerX + width / 2);
                list.add(centerY - height / 2);
                list.add(centerZ);
                if (isWithTexture) {
                    list.add(1.0f);
                    list.add(0.0f);
                }
                list.add(centerX + width / 2);
                list.add(centerY - height / 2);
                list.add(centerZ);
                if (isWithTexture) {
                    list.add(1.0f);
                    list.add(0.0f);
                }
                list.add(centerX + width / 2);
                list.add(centerY + height / 2);
                list.add(centerZ);
                if (isWithTexture) {
                    list.add(1.0f);
                    list.add(1.0f);
                }
                list.add(centerX - width / 2);
                list.add(centerY + height / 2);
                list.add(centerZ);
                if (isWithTexture) {
                    list.add(0.0f);
                    list.add(1.0f);
                }
                break;
            case DIRECTION_Z_NEGATIVE:
                list.add(centerX - width / 2);
                list.add(centerY + height / 2);
                list.add(centerZ);
                if (isWithTexture) {
                    list.add(0.0f);
                    list.add(1.0f);
                }
                list.add(centerX + width / 2);
                list.add(centerY + height / 2);
                list.add(centerZ);
                if (isWithTexture) {
                    list.add(1.0f);
                    list.add(1.0f);
                }
                list.add(centerX + width / 2);
                list.add(centerY - height / 2);
                list.add(centerZ);
                if (isWithTexture) {
                    list.add(1.0f);
                    list.add(0.0f);
                }
                list.add(centerX + width / 2);
                list.add(centerY - height / 2);
                list.add(centerZ);
                if (isWithTexture) {
                    list.add(1.0f);
                    list.add(0.0f);
                }
                list.add(centerX - width / 2);
                list.add(centerY - height / 2);
                list.add(centerZ);
                if (isWithTexture) {
                    list.add(0.0f);
                    list.add(0.0f);
                }
                list.add(centerX - width / 2);
                list.add(centerY + height / 2);
                list.add(centerZ);
                if (isWithTexture) {
                    list.add(0.0f);
                    list.add(1.0f);
                }
                break;
        }

        glRectangle.addVertextList(list);
        return glRectangle;
    }
}
