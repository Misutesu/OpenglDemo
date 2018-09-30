package com.wujiaquan.demo.opengldemo;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLES20;

public class OpenGLUtils {

    /**
     * 检测设备是否支持OpenGl2.0
     *
     * @param context
     * @return
     */
    public static boolean checkSupport(Context context) {
        boolean result = false;
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (manager != null) {
            ConfigurationInfo info = manager.getDeviceConfigurationInfo();
            result = info.reqGlEsVersion >= 0x2000;
        }
        return result;
    }


    /**
     * 编译着色器
     *
     * @param type
     * @param shaderCode
     * @return
     */
    public static int compilerShader(int type, String shaderCode) {
        //创建着色器ID
        final int shaderObjectId = GLES20.glCreateShader(type);
        if (shaderObjectId == 0) {
            return shaderObjectId;
        }
        //将着色器ID与着色器内容连接
        GLES20.glShaderSource(shaderObjectId, shaderCode);
        //编译着色器
        GLES20.glCompileShader(shaderObjectId);
        //验证编译结果
        final int[] compilerStatus = new int[1];
        GLES20.glGetShaderiv(shaderObjectId, GLES20.GL_COMPILE_STATUS, compilerStatus, 0);
        if (compilerStatus[0] == 0) {
            GLES20.glDeleteShader(shaderObjectId);
            return 0;
        }
        return shaderObjectId;
    }

    public static int linkProgram(int vertexShaderId, int fragmentShaderId) {
        //创建OpenGL程序ID
        final int programShaderId = GLES20.glCreateProgram();
        if (programShaderId == 0) {
            return programShaderId;
        }
        //连接顶点着色器
        GLES20.glAttachShader(programShaderId, vertexShaderId);
        //连接片段着色器
        GLES20.glAttachShader(programShaderId, fragmentShaderId);
        //连接OpenGL程序
        GLES20.glLinkProgram(programShaderId);
        //验证连接结果
        final int linkStatus[] = new int[1];
        GLES20.glGetProgramiv(programShaderId, GLES20.GL_LINK_STATUS, linkStatus, 0);
        if (linkStatus[0] == 0) {
            GLES20.glDeleteProgram(programShaderId);
            return 0;
        }
        return programShaderId;
    }

    /**
     * 验证OpenGL
     *
     * @param programObjectId
     * @return
     */
    public static boolean validateProgram(int programObjectId) {
        GLES20.glValidateProgram(programObjectId);
        final int[] validateStatus = new int[1];
        GLES20.glGetProgramiv(programObjectId, GLES20.GL_VALIDATE_STATUS, validateStatus, 0);
        return validateStatus[0] != 0;
    }

    /**
     * 创建OpenGL
     * @param vertexShaderCode
     * @param fragmentShaderCode
     * @return
     */
    public static int buildProgram(String vertexShaderCode, String fragmentShaderCode) {
        int vertexShader = compilerShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = compilerShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        int program = linkProgram(vertexShader, fragmentShader);

        boolean result = validateProgram(program);
        if (!result) {
            return 0;
        }
        return program;
    }
}
