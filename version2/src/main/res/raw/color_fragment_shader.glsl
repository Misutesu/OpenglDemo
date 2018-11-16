precision mediump float;
varying vec4 v_Color;
varying vec4 v_Diffuse;
varying vec3 v_Brightness;

void main() {
    gl_FragColor = v_Color * vec4(v_Brightness,1.0) + v_Color * v_Diffuse;
}
