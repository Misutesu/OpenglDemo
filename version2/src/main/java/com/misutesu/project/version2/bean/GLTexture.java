package com.misutesu.project.version2.bean;

import com.misutesu.project.version2.bean.base.GLMaterial;

public class GLTexture extends GLMaterial {
    private int textureId;

    public GLTexture(int textureId) {
        this.textureId = textureId;
    }

    public int getTextureId() {
        return textureId;
    }

    public void setTextureId(int textureId) {
        this.textureId = textureId;
    }

    public boolean isSame(GLTexture texture) {
        return textureId == texture.getTextureId();
    }
}
