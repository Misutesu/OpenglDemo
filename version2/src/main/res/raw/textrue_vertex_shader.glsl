uniform mat4 u_Matrix;
uniform mat4 u_MMatrix;
uniform vec3 u_LightLocation;
uniform vec3 u_Brightness;
uniform vec3 u_LightColor;

attribute vec3 a_Position;
attribute vec2 a_TextureCoordinates;

varying vec4 v_Diffuse;
varying vec3 v_Brightness;
varying vec2 v_TextureCoordinates;

void main() {
    vec3 normalVectorOrigin = a_Position;
    vec3 normalVector = normalize((u_MMatrix*vec4(normalVectorOrigin,1)).xyz);
    vec3 vectorLight = normalize(u_LightLocation - (u_MMatrix * vec4(a_Position,1)).xyz);
    float factor = max(0.0, dot(normalVector, vectorLight));

    v_Brightness = u_Brightness;
    v_Diffuse = factor*vec4(u_LightColor,1.0);
    v_TextureCoordinates = a_TextureCoordinates;

    gl_Position = u_Matrix * vec4(a_Position,1);
}
