package com.example.object;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public abstract class CObject {
    protected boolean visible = true;
    protected float color[] = { 1.f, 1.f, 1.f, 1.f };
    protected FloatBuffer vertex_buffer;

    public abstract void draw(GL10 gl);

    protected abstract void setVertexBuffer(int buffer_size);

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setColor(float r, float g, float b, float a) {
        color[0] = r;
        color[1] = g;
        color[2] = b;
        color[3] = a;
    }

}
