package com.example.gltest;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MySurfaceView extends GLSurfaceView {
    private MyRenderer renderer;
    private long start_time, end_time;
    private float prev_point[] = { -1.f, -1.f };

    public MySurfaceView(Context context) {
        super(context);
        init();
    }

    public MySurfaceView(Context context, AttributeSet attr) {
        super(context, attr);
        init();
    }

    private void init() {
        renderer = new MyRenderer();
        setRenderer(renderer);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float point[] = { event.getX(), event.getY() };
        float g_point[] = renderer.convertToGLpoint(point[0], point[1]);
        // System.out.println(prev_point[0] + "-->" + point[0] + ", " +
        // prev_point[1] + "-->" + point[1]);

        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            start_time = System.currentTimeMillis();
            renderer.getLine().addPoint(g_point[0], g_point[1], g_point[2]);
            break;
        case MotionEvent.ACTION_UP:
            renderer.getLine().clearPoints();
            break;
        case MotionEvent.ACTION_MOVE:
            end_time = System.currentTimeMillis();
            if (end_time - start_time > 10) {
                if (Math.abs(point[0] - prev_point[0]) > 5 || Math.abs(point[1] - prev_point[1]) > 5) {
                    renderer.getLine().addPoint(g_point[0], g_point[1], g_point[1]);
                    start_time = end_time;
                }
            }
            break;
        case MotionEvent.ACTION_CANCEL:
            break;
        }

        prev_point[0] = point[0];
        prev_point[1] = point[1];

        return true;
    }
}
