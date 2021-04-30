package com.aidanfincher.engine.gfx;

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL32.GL_GEOMETRY_SHADER;
import static org.lwjgl.opengl.GL40.GL_TESS_CONTROL_SHADER;
import static org.lwjgl.opengl.GL40.GL_TESS_EVALUATION_SHADER;

public enum ShaderType {
    VERTEX_SHADER(GL_VERTEX_SHADER),
    FRAGMENT_SHADER(GL_FRAGMENT_SHADER),
    GEOMETRY_SHADER(GL_GEOMETRY_SHADER),
    TESSELLATION_CTRL_SHADER(GL_TESS_CONTROL_SHADER),
    TESSELLATION_EVAL_SHADER(GL_TESS_EVALUATION_SHADER);

    private String typeName;
    private int type;

    ShaderType(int shaderType) {
        this.type = shaderType;
        switch (shaderType) {
            case GL_VERTEX_SHADER:
                typeName = "VERTEX_SHADER";
                break;
            case GL_FRAGMENT_SHADER:
                typeName = "FRAGMENT_SHADER";
                break;
            case GL_GEOMETRY_SHADER:
                typeName = "GEOMETRY_SHADER";
                break;
            case GL_TESS_CONTROL_SHADER:
                typeName = "TESSELLATION_CTRL_SHADER";
                break;
            case GL_TESS_EVALUATION_SHADER:
                typeName = "TESSELLATION_EVAL_SHADER";
                break;
            default:
                break;
        }
    }

    public String getTypeName() {
        return this.typeName;
    }

    public int getType() {
        return this.type;
    }
}
