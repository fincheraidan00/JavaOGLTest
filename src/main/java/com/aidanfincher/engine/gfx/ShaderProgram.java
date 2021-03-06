package com.aidanfincher.engine.gfx;

import static org.lwjgl.opengl.GL20.*;

public class ShaderProgram {

    private final int programId;
    private int vertexShaderId, fragmentShaderId;

    public ShaderProgram() throws Exception {
        programId = glCreateProgram();
        if (programId == 0)
            throw new Exception("Failed to create ShaderProgram");
    }

    public void createVertexShader(String shaderCode) throws Exception {
        vertexShaderId = createShader(shaderCode, ShaderType.VERTEX_SHADER);
    }

    public void createFragmentShader(String shaderCode) throws Exception {
        fragmentShaderId = createShader(shaderCode, ShaderType.FRAGMENT_SHADER);
    }

    protected int createShader(String shaderCode, ShaderType shaderType) throws Exception {
        int shaderId = glCreateShader(shaderType.getType());
        if (shaderId == 0)
            throw new Exception("Error creating shader of type: " + shaderType.getTypeName());

        glShaderSource(shaderId, shaderCode);
        glCompileShader(shaderId);

        if (glGetShaderi(shaderId, GL_COMPILE_STATUS) == 0)
            throw new Exception("Error compiling shader code: " + glGetShaderInfoLog(shaderId, 1024));

        glAttachShader(programId, shaderId);

        return shaderId;
    }

    public void link() throws Exception {
        glLinkProgram(programId);
        if (glGetProgrami(programId, GL_LINK_STATUS) == 0)
            throw new Exception("Error linking shader code: " + glGetProgramInfoLog(programId, 1024));

        if (vertexShaderId != 0) glDetachShader(programId, vertexShaderId);
        if (fragmentShaderId != 0) glDetachShader(programId, fragmentShaderId);

        glValidateProgram(programId);
        if (glGetProgrami(programId, GL_VALIDATE_STATUS) == 0)
            System.err.println("Warning validating Shader code: " + glGetProgramInfoLog(programId, 1024));
    }

    public void bind() {
        glUseProgram(programId);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public void destroy() {
        unbind();
        if (programId != 0)
            glDeleteProgram(programId);
    }
}
