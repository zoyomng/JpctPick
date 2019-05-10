package com.zoyo.jpctpick;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;

public class CBoxView extends GLSurfaceView {

    private float xpos = -1;
    private float ypos = -1;
    CBoxRander mRander;

    public CBoxView(Context context, AttributeSet attrs) {
        super(context);
        // TODO Auto-generated constructor stub

        // 使用自己实现的 EGLConfigChooser,该实现必须在setRenderer(renderer)之前
        // 如果没有setEGLConfigChooser方法被调用，则默认情况下，视图将选择一个与当前android.view.Surface兼容至少16位深度缓冲深度EGLConfig。
        setEGLConfigChooser(new EGLConfigChooser() {
            public EGLConfig chooseConfig(EGL10 egl, EGLDisplay display) {
                int[] attributes = new int[]{EGL10.EGL_DEPTH_SIZE, 16,
                        EGL10.EGL_NONE};
                EGLConfig[] configs = new EGLConfig[1];
                int[] result = new int[1];
                egl.eglChooseConfig(display, attributes, configs, 1, result);
                return configs[0];
            }
        });

        mRander = new CBoxRander(context);
        setRenderer(mRander);
    }

    public boolean onTouchEvent(MotionEvent me) {

        if (me.getAction() == MotionEvent.ACTION_DOWN) {
            xpos = me.getX();
            ypos = me.getY();
            int a = mRander.Pickint((int) me.getX(), (int) me.getY());
            System.out.println("====================" + a);
            return true;
        }

        if (me.getAction() == MotionEvent.ACTION_UP) {
            xpos = -1;
            ypos = -1;
            mRander.touchTurn = 0;
            mRander.touchTurnUp = 0;

            return true;
        }

        if (me.getAction() == MotionEvent.ACTION_MOVE) {
            float xd = me.getX() - xpos;
            float yd = me.getY() - ypos;

            xpos = me.getX();
            ypos = me.getY();

            mRander.touchTurn = xd / -100f;
            mRander.touchTurnUp = yd / -100f;
            return true;
        }

        try {
            Thread.sleep(15);
        } catch (Exception e) {
            // No need for this...
        }

        return super.onTouchEvent(me);
    }

}