precision mediump float;

//此变量接收实际的纹理数据， sampler2D为二维纹理数据的数组
uniform sampler2D u_TextureUnit;
//纹理坐标
varying vec2 v_TextureCoordinates;
varying vec4 v_Diffuse;
varying vec3 v_Brightness;

void main() {
    vec4 textureColor = texture2D(u_TextureUnit, v_TextureCoordinates);
    gl_FragColor = textureColor * v_Diffuse + textureColor * vec4(v_Brightness,1.0);
}
