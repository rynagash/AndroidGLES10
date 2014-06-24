package com.example.object;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.microedition.khronos.opengles.GL10;

public class CLine extends CObject {
    private int buffer_size = 0;
    private int num_of_values = 0;

    public CLine(int buffer_size) {
        this.buffer_size = buffer_size;
        setVertexBuffer(buffer_size);
    }

    public void addPoint(float x, float y, float z) {
        if ((num_of_values + 3) * 4 < buffer_size) {
            vertex_buffer.put(num_of_values++, x);
            vertex_buffer.put(num_of_values++, y);
            vertex_buffer.put(num_of_values++, z);
            vertex_buffer.position(0);
        }
    }

    public void clearPoints() {
        vertex_buffer.clear();
        num_of_values = 0;
    }

    @Override
    public void draw(GL10 gl) {
        if (visible) {
            gl.glPushMatrix();

            gl.glLineWidth(1.5f);
            gl.glColor4f(color[0], color[1], color[2], color[3]);
            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex_buffer);
            gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, num_of_values / 3);

            gl.glPopMatrix();
        }
    }

    @Override
    protected void setVertexBuffer(int buffer_size) {
        // TODO 自動生成されたメソッド・スタブ
        ByteBuffer vbb1 = ByteBuffer.allocateDirect(buffer_size);
        vbb1.order(ByteOrder.nativeOrder());
        vertex_buffer = vbb1.asFloatBuffer();
    }

}
