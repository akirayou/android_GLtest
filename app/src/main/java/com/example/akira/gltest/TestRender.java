package com.example.akira.gltest;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLException;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


/**
 * Created by akira on 17/07/25.
 */

public class TestRender implements GLSurfaceView.Renderer {
    private static final String TAG="TestGSV";
    private Context context;
    private int width;
    private int height;

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig){
    }


    private ScaleGestureDetector _gestureDetector;


    public TestRender(Context c){
        this.context=c;
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        gl10.glViewport(0, 0, width, height);
        this.width=width;
        this.height=height;
        gl10.glViewport(0, 0, width, height);
        gl10.glMatrixMode(GL10.GL_PROJECTION);
        gl10.glLoadIdentity();
        //  画角の設定(left,right,bottom,top,near,far)
        float aspect = (float) width / height;
        GLU.gluPerspective(gl10,70/*fov[deg]*/,aspect/*aspect*/,0.1f,100f);

    }

    private float rot=0;
    @Override
    public void onDrawFrame(GL10 gl10) {

        gl10.glEnable(GL10.GL_LIGHTING);
        gl10.glEnable(GL10.GL_LIGHT0);
        float [] lightPos={0.0f,10.0f,0.0f};
        float [] white={1.0f,1.0f,1.0f,1.0f};

        gl10.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION,lightPos , 0);
        gl10.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT,white, 0);
        gl10.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE,white, 0);
        gl10.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR,white, 0);
        gl10.glMatrixMode(GL10.GL_MODELVIEW);
        gl10.glLoadIdentity();
        GLU.gluLookAt(gl10, 0,0,-4,   0,0,0,   0,1,0);

        FloatBuffer buffer;
        int nofLines=5;
        ByteBuffer vb = ByteBuffer.allocateDirect(4*3*2*nofLines*2);
        vb.order(ByteOrder.nativeOrder());
        buffer = vb.asFloatBuffer();
        float z=0;
        float b=0.5f*nofLines;
        for(int xi=0;xi<nofLines;xi++) {
            float x=xi-(float)nofLines/2.0f;
            buffer.put(x);
            buffer.put(-b);
            buffer.put(z);

            buffer.put(x);
            buffer.put(b);
            buffer.put(z);
        }
        for(int yi=0;yi<nofLines;yi++) {
            float y=yi-(float)nofLines/2.0f;
            buffer.put(b);
            buffer.put(y);
            buffer.put(z);

            buffer.put(-b);
            buffer.put(y);
            buffer.put(z);
        }
        buffer.rewind();



        gl10.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT, white, 0);
        gl10.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE, white, 0);
        gl10.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SPECULAR, white, 0);

        gl10.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl10.glMatrixMode(GL10.GL_MODELVIEW);


/*
        gl10.glPushMatrix();
        gl10.glLoadIdentity();
        gl10.glTranslatef(0, 0, -4f);
        rot+=1f;
        gl10.glRotatef(rot, 0, 1, 1);
  */

        gl10.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl10.glVertexPointer(3/*vector order*/, GL10.GL_FLOAT, 0/*stride*/, buffer);
        gl10.glLineWidth(4.0f);
        gl10.glDrawArrays(GL10.GL_LINES, 0, nofLines*2*2);

        //gl10.glPopMatrix();


    }
}
