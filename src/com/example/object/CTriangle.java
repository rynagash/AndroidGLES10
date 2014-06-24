package com.example.object;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.microedition.khronos.opengles.GL10;

public class CTriangle extends CObject {
    // @formatter:off
    static final float points[] = {
        -1.f, .0f, .0f,
         0.f, .0f, .0f,
         .0f, 1.f, .0f
    };
    //@formatter:on

    public CTriangle() {
        setVertexBuffer(points.length * 4);
    }

    @Override
    public void draw(GL10 gl) {
        if (visible) {
            gl.glPushMatrix();

            gl.glColor4f(color[0], color[1], color[2], color[3]);

            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex_buffer);
            gl.glDrawArrays(GL10.GL_TRIANGLES, 0, points.length / 3);

            gl.glPopMatrix();
        }
    }

    @Override
    protected void setVertexBuffer(int buffer_size) {
        ByteBuffer vbb1 = ByteBuffer.allocateDirect(buffer_size);
        vbb1.order(ByteOrder.nativeOrder());
        vertex_buffer = vbb1.asFloatBuffer();
        vertex_buffer.put(points);
        vertex_buffer.position(0);

    }

}
