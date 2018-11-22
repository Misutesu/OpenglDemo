package com.misutesu.project.version2.bean.shape;

import android.util.Log;

import com.misutesu.project.version2.bean.shape.base.GLFace;

import java.util.ArrayList;
import java.util.List;

public class GLCircle extends GLFace {

    public static GLCircle create(float centerX, float centerY, float centerZ, float radius, int num, int direction) {
        return create(centerX, centerY, centerZ, radius, num, direction, false);
    }

    public static GLCircle create(float centerX, float centerY, float centerZ, float radius, int num, int direction, boolean isWithTexture) {
        GLCircle glCircle = new GLCircle();
        List<Float> list = new ArrayList<>();

        float angleInRadians = ((float) Math.PI * 2f / num);

        for (int i = 0; i < num; i++) {
            switch (direction) {
                case DIRECTION_X_POSITIVE:
                    list.add(centerX);
                    list.add(centerY);
                    list.add(centerZ);
                    if (isWithTexture) {
                        list.add(0.5f);
                        list.add(0.5f);
                    }
                    list.add(centerX);
                    list.add((float) (centerY + radius * Math.cos(angleInRadians * i)));
                    list.add((float) (centerZ + radius * Math.sin(angleInRadians * i)));
                    if (isWithTexture) {
                        list.add((float) (0.5f + 0.5f * Math.cos(angleInRadians * i)));
                        list.add((float) (0.5f + 0.5f * Math.sin(angleInRadians * i)));
                    }

                    if (i + 1 == num) {
                        list.add(centerX);
                        list.add((float) (centerY + radius * Math.cos(0)));
                        list.add((float) (centerZ + radius * Math.sin(0)));
                        if (isWithTexture) {
                            list.add((float) (0.5f + 0.5f * Math.cos(0)));
                            list.add((float) (0.5f + 0.5f * Math.sin(0)));
                        }
                    } else {
                        list.add(centerX);
                        list.add((float) (centerY + radius * Math.cos(angleInRadians * (i + 1))));
                        list.add((float) (centerZ + radius * Math.sin(angleInRadians * (i + 1))));
                        if (isWithTexture) {
                            list.add((float) (0.5f + 0.5f * Math.cos(angleInRadians * (i + 1))));
                            list.add((float) (0.5f + 0.5f * Math.sin(angleInRadians * (i + 1))));
                        }
                    }
                    break;
                case DIRECTION_X_NEGATIVE:
                    list.add(centerX);
                    list.add(centerY);
                    list.add(centerZ);
                    if (isWithTexture) {
                        list.add(0.5f);
                        list.add(0.5f);
                    }
                    if (i + 1 == num) {
                        list.add(centerX);
                        list.add((float) (centerY + radius * Math.cos(0)));
                        list.add((float) (centerZ + radius * Math.sin(0)));
                        if (isWithTexture) {
                            list.add((float) (0.5f + 0.5f * Math.cos(0)));
                            list.add((float) (0.5f + 0.5f * Math.sin(0)));
                        }
                    } else {
                        list.add(centerX);
                        list.add((float) (centerY + radius * Math.cos(angleInRadians * (i + 1))));
                        list.add((float) (centerZ + radius * Math.sin(angleInRadians * (i + 1))));
                        if (isWithTexture) {
                            list.add((float) (0.5f + 0.5f * Math.cos(angleInRadians * (i + 1))));
                            list.add((float) (0.5f + 0.5f * Math.sin(angleInRadians * (i + 1))));
                        }
                    }

                    list.add(centerX);
                    list.add((float) (centerY + radius * Math.cos(angleInRadians * i)));
                    list.add((float) (centerZ + radius * Math.sin(angleInRadians * i)));
                    if (isWithTexture) {
                        list.add((float) (0.5f + 0.5f * Math.cos(angleInRadians * i)));
                        list.add((float) (0.5f + 0.5f * Math.sin(angleInRadians * i)));
                    }
                    break;
                case DIRECTION_Y_POSITIVE:
                    list.add(centerX);
                    list.add(centerY);
                    list.add(centerZ);
                    if (isWithTexture) {
                        list.add(0.5f);
                        list.add(0.5f);
                    }
                    list.add((float) (centerX + radius * Math.cos(angleInRadians * i)));
                    list.add(centerY);
                    list.add((float) (centerZ - radius * Math.sin(angleInRadians * i)));
                    if (isWithTexture) {
                        list.add((float) (0.5f + 0.5f * Math.cos(angleInRadians * i)));
                        list.add((float) (0.5f + 0.5f * Math.sin(angleInRadians * i)));
                    }

                    if (i + 1 == num) {
                        list.add((float) (centerX + radius * Math.cos(0)));
                        list.add(centerY);
                        list.add((float) (centerZ - radius * Math.sin(0)));
                        if (isWithTexture) {
                            list.add((float) (0.5f + 0.5f * Math.cos(0)));
                            list.add((float) (0.5f + 0.5f * Math.sin(0)));
                        }
                    } else {
                        list.add((float) (centerX + radius * Math.cos(angleInRadians * (i + 1))));
                        list.add(centerY);
                        list.add((float) (centerZ - radius * Math.sin(angleInRadians * (i + 1))));
                        if (isWithTexture) {
                            list.add((float) (0.5f + 0.5f * Math.cos(angleInRadians * (i + 1))));
                            list.add((float) (0.5f + 0.5f * Math.sin(angleInRadians * (i + 1))));
                        }
                    }
                    break;
                case DIRECTION_Y_NEGATIVE:
                    list.add(centerX);
                    list.add(centerY);
                    list.add(centerZ);
                    if (isWithTexture) {
                        list.add(0.5f);
                        list.add(0.5f);
                    }
                    if (i + 1 == num) {
                        list.add((float) (centerX + radius * Math.cos(0)));
                        list.add(centerY);
                        list.add((float) (centerZ - radius * Math.sin(0)));
                        if (isWithTexture) {
                            list.add((float) (0.5f + 0.5f * Math.cos(0)));
                            list.add((float) (0.5f + 0.5f * Math.sin(0)));
                        }
                    } else {
                        list.add((float) (centerX + radius * Math.cos(angleInRadians * (i + 1))));
                        list.add(centerY);
                        list.add((float) (centerZ - radius * Math.sin(angleInRadians * (i + 1))));
                        if (isWithTexture) {
                            list.add((float) (0.5f + 0.5f * Math.cos(angleInRadians * (i + 1))));
                            list.add((float) (0.5f + 0.5f * Math.sin(angleInRadians * (i + 1))));
                        }
                    }

                    list.add((float) (centerX + radius * Math.cos(angleInRadians * i)));
                    list.add(centerY);
                    list.add((float) (centerZ - radius * Math.sin(angleInRadians * i)));
                    if (isWithTexture) {
                        list.add((float) (0.5f + 0.5f * Math.cos(angleInRadians * i)));
                        list.add((float) (0.5f + 0.5f * Math.sin(angleInRadians * i)));
                    }
                    break;
                case DIRECTION_Z_POSITIVE:
                    list.add(centerX);
                    list.add(centerY);
                    list.add(centerZ);
                    if (isWithTexture) {
                        list.add(0.5f);
                        list.add(0.5f);
                    }
                    list.add((float) (centerX + radius * Math.cos(angleInRadians * i)));
                    list.add((float) (centerY + radius * Math.sin(angleInRadians * i)));
                    list.add(centerZ);
                    if (isWithTexture) {
                        list.add((float) (0.5f + 0.5f * Math.cos(angleInRadians * i)));
                        list.add((float) (0.5f + 0.5f * Math.sin(angleInRadians * i)));
                    }

                    if (i + 1 == num) {
                        list.add((float) (centerX + radius * Math.cos(0)));
                        list.add((float) (centerY + radius * Math.sin(0)));
                        list.add(centerZ);
                        if (isWithTexture) {
                            list.add((float) (0.5f + 0.5f * Math.cos(0)));
                            list.add((float) (0.5f + 0.5f * Math.sin(0)));
                        }
                    } else {
                        list.add((float) (centerX + radius * Math.cos(angleInRadians * (i + 1))));
                        list.add((float) (centerY + radius * Math.sin(angleInRadians * (i + 1))));
                        list.add(centerZ);
                        if (isWithTexture) {
                            list.add((float) (0.5f + 0.5f * Math.cos(angleInRadians * (i + 1))));
                            list.add((float) (0.5f + 0.5f * Math.sin(angleInRadians * (i + 1))));
                        }
                    }
                    break;
                case DIRECTION_Z_NEGATIVE:
                    list.add(centerX);
                    list.add(centerY);
                    list.add(centerZ);
                    if (isWithTexture) {
                        list.add(0.5f);
                        list.add(0.5f);
                    }
                    if (i + 1 == num) {
                        list.add((float) (centerX + radius * Math.cos(0)));
                        list.add((float) (centerY + radius * Math.sin(0)));
                        list.add(centerZ);
                        if (isWithTexture) {
                            list.add((float) (0.5f + 0.5f * Math.cos(0)));
                            list.add((float) (0.5f + 0.5f * Math.sin(0)));
                        }
                    } else {
                        list.add((float) (centerX + radius * Math.cos(angleInRadians * (i + 1))));
                        list.add((float) (centerY + radius * Math.sin(angleInRadians * (i + 1))));
                        list.add(centerZ);
                        if (isWithTexture) {
                            list.add((float) (0.5f + 0.5f * Math.cos(angleInRadians * (i + 1))));
                            list.add((float) (0.5f + 0.5f * Math.sin(angleInRadians * (i + 1))));
                        }
                    }

                    list.add((float) (centerX + radius * Math.cos(angleInRadians * i)));
                    list.add((float) (centerY + radius * Math.sin(angleInRadians * i)));
                    list.add(centerZ);
                    if (isWithTexture) {
                        list.add((float) (0.5f + 0.5f * Math.cos(angleInRadians * i)));
                        list.add((float) (0.5f + 0.5f * Math.sin(angleInRadians * i)));
                    }
                    break;
            }
        }

        glCircle.addVertextList(list);
        return glCircle;
    }
}
