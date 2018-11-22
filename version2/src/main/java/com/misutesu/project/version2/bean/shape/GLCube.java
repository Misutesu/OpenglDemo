package com.misutesu.project.version2.bean.shape;

import com.misutesu.project.version2.bean.base.GLShape;
import com.misutesu.project.version2.bean.shape.base.GLFace;

public class GLCube extends GLShape {
    public static GLCube create(float centerX, float centerY, float centerZ, float width, float length, float height) {
        return create(centerX, centerY, centerZ, width, length, height, false);
    }

    public static GLCube create(float centerX, float centerY, float centerZ, float width, float length, float height, boolean isWithTexture) {
        GLCube glCube = new GLCube();

        //Front
        glCube.addShape(GLRectangle.create(centerX, centerY, centerZ + width / 2, length, height, GLFace.DIRECTION_Z_POSITIVE, isWithTexture));
        //Back
        glCube.addShape(GLRectangle.create(centerX, centerY, centerZ - width / 2, length, height, GLFace.DIRECTION_Z_NEGATIVE, isWithTexture));

        //Top
        glCube.addShape(GLRectangle.create(centerX, centerY + height / 2, centerZ, length, width, GLFace.DIRECTION_Y_POSITIVE, isWithTexture));
        //Bottom
        glCube.addShape(GLRectangle.create(centerX, centerY - height / 2, centerZ, length, width, GLFace.DIRECTION_Y_NEGATIVE, isWithTexture));

        //Left
        glCube.addShape(GLRectangle.create(centerX - length / 2, centerY, centerZ, width, height, GLFace.DIRECTION_X_NEGATIVE, isWithTexture));
        //Right
        glCube.addShape(GLRectangle.create(centerX + length / 2, centerY, centerZ, width, height, GLFace.DIRECTION_X_POSITIVE, isWithTexture));

        return glCube;
    }
}
