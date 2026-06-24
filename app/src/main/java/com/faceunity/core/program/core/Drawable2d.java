package com.faceunity.core.program.core;

import com.faceunity.core.utils.GlUtil;
import java.nio.FloatBuffer;

public class Drawable2d {
    public static final int COORDS_PER_VERTEX = 2;
    public static final int SIZEOF_FLOAT = 4;
    public static final int TEXTURE_COORD_STRIDE = 8;
    public static final int VERTEXTURE_STRIDE = 8;
    private FloatBuffer mTexCoordArray;
    private FloatBuffer mVertexArray;
    private int mVertexCount;

    public Drawable2d() {
    }

    public Drawable2d(float[] fArr, float[] fArr2) {
        updateVertexArray(fArr);
        updateTexCoordArray(fArr2);
    }

    public Drawable2d(float[] fArr) {
        updateVertexArray(fArr);
    }

    public void updateVertexArray(float[] fArr) {
        this.mVertexArray = GlUtil.createFloatBuffer(fArr);
        this.mVertexCount = fArr.length / 2;
    }

    public void updateTexCoordArray(float[] fArr) {
        this.mTexCoordArray = GlUtil.createFloatBuffer(fArr);
    }

    public FloatBuffer vertexArray() {
        return this.mVertexArray;
    }

    public FloatBuffer texCoordArray() {
        return this.mTexCoordArray;
    }

    public int vertexCount() {
        return this.mVertexCount;
    }
}
