package com.example.gltest;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;

import com.example.object.CLine;
import com.example.object.CTriangle;

public class MyRenderer implements Renderer {
    private float ratio = 1.0f;
    private CLine line;
    private CTriangle triangle;

    private int viewSize[] = { -1, -1 };

    public MyRenderer() {
        line = new CLine(1024);

        triangle = new CTriangle();
        triangle.setColor(1.f, 0.f, 0.f, 1.f);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        // TODO 自動生成されたメソッド・スタブ
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glEnable(GL10.GL_DEPTH_TEST);

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

        line.draw(gl);

        gl.glPushMatrix();
        gl.glScalef(0.3f, 0.3f, 1.0f);
        // triangle.draw(gl);
        gl.glPopMatrix();

        gl.glDisable(GL10.GL_DEPTH_TEST);

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // TODO 自動生成されたメソッド・スタブ
        gl.glViewport(0, 0, width, height);
        viewSize[0] = width;
        viewSize[1] = height;

        ratio = (float) height / width;

        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrthof(-1.f, 1.f, -ratio, ratio, 10.f, -10.f);

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

//        //@formatter:off
//        float points[] = {
//                         0.f,           0.f,
//                (float)width,           0.f,
//                (float)width, (float)height,
//        };
//        //@formatter:on
        // for (int i = 0; i < points.length; i += 2) {
        // float point[] = convertToGLpoint(points[i], points[i + 1]);
        // line0.addPoint(point[0], point[1], point[2]);
        // }

    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // TODO 自動生成されたメソッド・スタブ
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);
    }

    public float[] convertToGLpoint(float x, float y) {
        float[] ret = new float[3];

        ret[0] = 2.f * (x / viewSize[0] - .5f);
        ret[1] = -2.f * ratio * (y / viewSize[1] - .5f);
        ret[2] = .0f;

        return ret;
    }

    public CLine getLine() {
        return line;
    }

}
