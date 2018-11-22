package com.misutesu.project.version2.bean;

import android.graphics.Bitmap;
import android.opengl.GLES20;

import com.misutesu.project.version2.bean.base.GLMaterial;
import com.misutesu.project.version2.bean.base.GLShape;
import com.misutesu.project.version2.bean.color.GLColor;
import com.misutesu.project.version2.bean.color.GLTexture;
import com.misutesu.project.version2.program.ColorProgram;
import com.misutesu.project.version2.program.TextureProgram;

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

    public void addShape(Bitmap bitmap, GLShape shape) {
        boolean hasAdd = false;
        for (Map.Entry<GLMaterial, GLShape> entry : result.entrySet()) {
            GLMaterial glMaterial = entry.getKey();
            GLShape glShape = entry.getValue();
            if (glMaterial instanceof GLTexture) {
                GLTexture oldTexture = (GLTexture) glMaterial;
                if (oldTexture.getBitmap() == bitmap) {
                    glShape.addShape(shape);
                    hasAdd = true;
                }
            }
        }
        if (!hasAdd) {
            result.put(new GLTexture(bitmap), shape);
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
            if (entry.getKey() instanceof GLTexture) {
                ((GLTexture) entry.getKey()).createTextureId();
            }
            entry.getValue().createBuffer();
        }
    }

    public void draw(float[] modelViewProjectionMatrix, float[] modelMatrix, float[] lightLocation, float[] lightColor, float[] brightnessColor
            , ColorProgram colorProgram, TextureProgram textureProgram) {
        for (Map.Entry<GLMaterial, GLShape> entry : result.entrySet()) {
            GLShape glShape = entry.getValue();
            if (entry.getKey() instanceof GLColor) {
                GLColor glColor = (GLColor) entry.getKey();

                GLES20.glUseProgram(colorProgram.getProgram());

                GLES20.glUniformMatrix4fv(colorProgram.getuMatrix(), 1, false, modelViewProjectionMatrix, 0);
                GLES20.glUniformMatrix4fv(colorProgram.getuMMatrix(), 1, false, modelMatrix, 0);

                GLES20.glUniform3fv(colorProgram.getuLightLocation(), 1, lightLocation, 0);
                GLES20.glUniform3fv(colorProgram.getuLightColor(), 1, lightColor, 0);
                GLES20.glUniform3fv(colorProgram.getuBrightness(), 1, brightnessColor, 0);

                GLES20.glUniform4f(colorProgram.getuColor(), glColor.getRf(), glColor.getGf(), glColor.getBf(), glColor.getAf());

                glShape.getFloatBuffer().position(0);
                GLES20.glVertexAttribPointer(colorProgram.getaPosition(), 3, GLES20.GL_FLOAT, false, 0, glShape.getFloatBuffer());
                GLES20.glEnableVertexAttribArray(colorProgram.getaPosition());
                glShape.getFloatBuffer().position(0);

                GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, entry.getValue().getSize() / 3);
            } else if (entry.getKey() instanceof GLTexture) {
                GLTexture glTexture = (GLTexture) entry.getKey();

                GLES20.glUseProgram(textureProgram.getProgram());

                GLES20.glUniformMatrix4fv(textureProgram.getuMatrix(), 1, false, modelViewProjectionMatrix, 0);
                GLES20.glUniformMatrix4fv(textureProgram.getuMMatrix(), 1, false, modelMatrix, 0);

                GLES20.glUniform3fv(textureProgram.getuLightLocation(), 1, lightLocation, 0);
                GLES20.glUniform3fv(textureProgram.getuLightColor(), 1, lightColor, 0);
                GLES20.glUniform3fv(textureProgram.getuBrightness(), 1, brightnessColor, 0);

                GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, glTexture.getTextureId());
                GLES20.glUniform1i(textureProgram.getuTextureUnit(), 0);

                glShape.getFloatBuffer().position(0);
                GLES20.glVertexAttribPointer(textureProgram.getaPosition(), 3, GLES20.GL_FLOAT, false, 20, glShape.getFloatBuffer());
                GLES20.glEnableVertexAttribArray(textureProgram.getaPosition());
                glShape.getFloatBuffer().position(3);
                GLES20.glVertexAttribPointer(textureProgram.getaTextureCoordinatesLocation(), 2, GLES20.GL_FLOAT, false, 20, glShape.getFloatBuffer());
                GLES20.glEnableVertexAttribArray(textureProgram.getaTextureCoordinatesLocation());
                glShape.getFloatBuffer().position(0);
                GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, entry.getValue().getSize() / 5);
            }
        }
    }
}
