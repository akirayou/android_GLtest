package com.example.akira.gltest;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

/**
 * Created by akira on 17/08/08.
 */

public class TestGLSView extends GLSurfaceView {
    private TestRender renderer;
    private ScaleGestureDetector scaleGestureDetector;
    public TestGLSView(Context context, AttributeSet attrs){
        super(context);
        renderer=new TestRender(context);
        setRenderer(renderer);

        scaleGestureDetector=new ScaleGestureDetector(context, new ScaleGestureDetector.OnScaleGestureListener() {
            @Override
            public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
                scaleGestureDetector.getScaleFactor();
                return false;
            }

            @Override
            public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
                return false;
            }

            @Override
            public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {

            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        scaleGestureDetector.onTouchEvent(e);
        return true;
    }

}
