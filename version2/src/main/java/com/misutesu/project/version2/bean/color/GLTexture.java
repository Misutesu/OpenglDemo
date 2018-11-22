package com.misutesu.project.version2.bean.color;

import android.graphics.Bitmap;

import com.misutesu.project.version2.bean.base.GLMaterial;
import com.misutesu.project.version2.utils.TextureHelper;

public class GLTexture extends GLMaterial {
    private Bitmap bitmap;
    private int textureId;

    public GLTexture(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getTextureId() {
        return textureId;
    }

    public boolean isSame(GLTexture texture) {
        return bitmap == texture.getBitmap();
    }

    public void createTextureId() {
        textureId = TextureHelper.loadTexture(bitmap);
    }
}
