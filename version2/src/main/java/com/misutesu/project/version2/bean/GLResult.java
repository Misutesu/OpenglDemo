package com.misutesu.project.version2.bean;

import com.misutesu.project.version2.bean.base.GLMaterial;

import java.util.HashMap;
import java.util.Map;

public class GLResult {
    private Map<GLMaterial, GLShape> result = new HashMap<>();

    public void addShape(int r, int g, int b, GLShape shape) {
        boolean hasAdd = false;
        for (Map.Entry<GLMaterial, GLShape> entry : result.entrySet()) {
            GLMaterial glMaterial = entry.getKey();
            GLShape glShape = entry.getValue();
            if (glMaterial instanceof GLColor) {
                GLColor glColor = (GLColor) glMaterial;
                if (glColor.isSame(r, g, b)) {
                    glShape.addShape(shape);
                    hasAdd = true;
                    break;
                }
            }
        }
        if (!hasAdd) {
            result.put(new GLColor(r, g, b), shape);
        }
    }

    public void addShape(GLMaterial material, GLShape shape) {
        boolean hasAdd = false;
        for (Map.Entry<GLMaterial, GLShape> entry : result.entrySet()) {
            GLMaterial glMaterial = entry.getKey();
            GLShape glShape = entry.getValue();
            if ((material instanceof GLColor && glMaterial instanceof GLColor)) {
                GLColor oldColor = (GLColor) glMaterial;
                GLColor newColor = (GLColor) material;
                if (oldColor.isSame(newColor)) {
                    glShape.addShape(shape);
                    hasAdd = true;
                }
                break;
            } else if (material instanceof GLTexture && glMaterial instanceof GLTexture) {
                GLTexture oldTexture = (GLTexture) glMaterial;
                GLTexture newTexture = (GLTexture) material;
                if (oldTexture.isSame(newTexture)) {
                    glShape.addShape(shape);
                    hasAdd = true;
                }
            }
        }
        if (!hasAdd) {
            result.put(material, shape);
        }
    }

    public void create() {
        for (Map.Entry<GLMaterial, GLShape> entry : result.entrySet()) {
            entry.getValue().createBuffer();
        }
    }
}
