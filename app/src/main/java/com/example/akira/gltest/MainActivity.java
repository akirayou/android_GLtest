package com.example.akira.gltest;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private SurfaceView surfaceView;
    private TestSV testSV;

    private TestGLSView mGlView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        surfaceView = (SurfaceView)findViewById(R.id.surfaceView);
        testSV = new TestSV(this, surfaceView);

        this.mGlView = (TestGLSView) (this.findViewById(R.id.glview1));
        //this.mGlView.setRenderer(new TestRender(this));


    }
}
