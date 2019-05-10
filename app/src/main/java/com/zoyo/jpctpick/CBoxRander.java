package com.zoyo.jpctpick;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.threed.jpct.Camera;
import com.threed.jpct.FrameBuffer;
import com.threed.jpct.Interact2D;
import com.threed.jpct.Light;
import com.threed.jpct.Object3D;
import com.threed.jpct.Primitives;
import com.threed.jpct.RGBColor;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.Texture;
import com.threed.jpct.TextureManager;
import com.threed.jpct.World;
import com.threed.jpct.util.BitmapHelper;
import com.threed.jpct.util.MemoryHelper;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class CBoxRander implements GLSurfaceView.Renderer {

    public MainActivity mActivity = null;
    private FrameBuffer fb = null;
    private World world = null;
    Camera cam = null;
    // 立方体
    private Object3D cube = null;
    private Object3D yi, shi, zhu, xing, wan, wen;
    // 光照类
    private Light sun = null;
    private RGBColor back = new RGBColor(255, 255, 255);
    // 旋转
    public float touchTurn = 0;
    public float touchTurnUp = 0;


    public CBoxRander(Context context) {
        mActivity = (MainActivity) context;
    }


    @Override
    public void onDrawFrame(GL10 gl) {
        // TODO Auto-generated method stub
        if (touchTurn != 0) {
            cube.rotateY(touchTurn);
            touchTurn = 0;
        }

        if (touchTurnUp != 0) {
            cube.rotateX(touchTurnUp);
            touchTurnUp = 0;
        }

        fb.clear(back);
        world.renderScene(fb);
        world.draw(fb);
        fb.display();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int w, int h) {
        // TODO Auto-generated method stub
        if (fb != null) {
            fb.dispose();
        }

        // 创建一个宽度为w,高为h的FrameBuffer
        fb = new FrameBuffer(gl, w, h);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // TODO Auto-generated method stub
//        world = new World();
//        world.setAmbientLight(100, 100, 100);
//
//        sun = new Light(world);
//        sun.setIntensity(25, 25, 25);
//
//        // 纹理
//        Texture texture = new Texture(BitmapHelper.rescale(BitmapHelper.convert(mActivity.getResources().getDrawable(R.mipmap.ic_launcher)), 64, 64));
//        TextureManager.getInstance().addTexture("texture", texture);
//        // 立方体
//        cube = Primitives.getCube(10);
//        cube.calcTextureWrapSpherical();
//        cube.setTexture("texture");
//        cube.strip();
//        cube.build();
//
//        world.addObject(cube);
//        // 摄像机
//        cam = world.getCamera();
//        cam.moveCamera(Camera.CAMERA_MOVEOUT, 50);
//        cam.lookAt(cube.getTransformedCenter());
//
//        SimpleVector sv = new SimpleVector();
//        sv.set(cube.getTransformedCenter());
//        sv.y -= 100;
//        sv.z -= 100;
//        sun.setPosition(sv);
//        MemoryHelper.compact();


        world = new World();
        world.setAmbientLight(100, 100, 100);

        sun = new Light(world);
        sun.setIntensity(250, 250, 250);
        sun.setPosition(GetPoint(0, 0, -150));
        sun.setDiscardDistance(500);

        // 纹理
        CreateTextures();

        // 立方体
        CreateBox();

        // 摄像机
        cam = world.getCamera();
        cam.moveCamera(Camera.CAMERA_MOVEOUT, 50);
        cam.lookAt(cube.getTransformedCenter());

        SimpleVector sv = new SimpleVector();
        sv.set(cube.getTransformedCenter());
        sv.y -= 100;
        sv.z -= 100;
        sun.setPosition(sv);
        MemoryHelper.compact();


    }

    private SimpleVector GetPoint(float x, float y, float z) {
        return new SimpleVector(x, y, z);
    }

    // 创建纹理
    private void CreateTextures() {
        Texture texture = new Texture(BitmapHelper.rescale(
                BitmapHelper.convert(mActivity.getResources().getDrawable(
                        R.drawable.a)), 64, 64));
        TextureManager.getInstance().addTexture("yi", texture);

        texture = new Texture(BitmapHelper.rescale(
                BitmapHelper.convert(mActivity.getResources().getDrawable(
                        R.drawable.b)), 64, 64));
        TextureManager.getInstance().addTexture("shi", texture);

        texture = new Texture(BitmapHelper.rescale(
                BitmapHelper.convert(mActivity.getResources().getDrawable(
                        R.drawable.c)), 64, 64));
        TextureManager.getInstance().addTexture("zhu", texture);

        texture = new Texture(BitmapHelper.rescale(
                BitmapHelper.convert(mActivity.getResources().getDrawable(
                        R.drawable.d)), 64, 64));
        TextureManager.getInstance().addTexture("xing", texture);

        texture = new Texture(BitmapHelper.rescale(
                BitmapHelper.convert(mActivity.getResources().getDrawable(
                        R.drawable.e)), 64, 64));
        TextureManager.getInstance().addTexture("wan", texture);

        texture = new Texture(BitmapHelper.rescale(
                BitmapHelper.convert(mActivity.getResources().getDrawable(
                        R.drawable.a)), 64, 64));
        TextureManager.getInstance().addTexture("wen", texture);
    }

//    private void CreateBox() {
//        cube = new Object3D(12);
//
//        // 前
//
//        cube.addTriangle(GetPoint(-30, -30, 30), 0.0f, 0.0f,
//                GetPoint(30, -30, 30), 1.0f, 0.0f, GetPoint(-30, 30, 30), 0.0f,
//                1.0f, TextureManager.getInstance().getTextureID("yi"));
//
//        cube.addTriangle(GetPoint(30, -30, 30), 1.0f, 0.0f,
//                GetPoint(30, 30, 30), 1.0f, 1.0f, GetPoint(-30, 30, 30), 0.0f,
//                1.0f, TextureManager.getInstance().getTextureID("yi"));
//
//        // 上
//
//        cube.addTriangle(GetPoint(-30, 30, 30), 0.0f, 0.0f,
//                GetPoint(30, 30, 30), 1.0f, 0.0f, GetPoint(-30, 30, -30), 0.0f,
//                1.0f, TextureManager.getInstance().getTextureID("shi"));
//
//        cube.addTriangle(GetPoint(30, 30, 30), 1.0f, 0.0f,
//                GetPoint(30, 30, -30), 1.0f, 1.0f, GetPoint(-30, 30, -30),
//                0.0f, 1.0f, TextureManager.getInstance().getTextureID("shi"));
//
//
//        // 后
//
//        cube.addTriangle(GetPoint(-30, 30, -30), 0.0f, 0.0f,
//                GetPoint(30, 30, -30), 1.0f, 0.0f, GetPoint(-30, -30, -30),
//                0.0f, 1.0f, TextureManager.getInstance().getTextureID("zhu"));
//
//        cube.addTriangle(GetPoint(30, 30, -30), 1.0f, 0.0f,
//                GetPoint(30, -30, -30), 1.0f, 1.0f, GetPoint(-30, -30, -30),
//                0.0f, 1.0f, TextureManager.getInstance().getTextureID("zhu"));
//
//
//        // 下
//
//        cube.addTriangle(GetPoint(-30, -30, -30), 0.0f, 0.0f,
//                GetPoint(30, -30, -30), 1.0f, 0.0f, GetPoint(-30, -30, 30),
//                0.0f, 1.0f, TextureManager.getInstance().getTextureID("xing"));
//
//        cube.addTriangle(GetPoint(30, -30, -30), 1.0f, 0.0f,
//                GetPoint(30, -30, 30), 1.0f, 1.0f, GetPoint(-30, -30, 30), 0.0f,
//                1.0f, TextureManager.getInstance().getTextureID("xing"));
//
//
//        // 左
//
//        cube.addTriangle(GetPoint(-30, -30, -30), 0.0f, 0.0f,
//                GetPoint(-30, -30, 30), 1.0f, 0.0f, GetPoint(-30, 30, -30),
//                0.0f, 1.0f, TextureManager.getInstance().getTextureID("wan"));
//
//        cube.addTriangle(GetPoint(-30, -30, 30), 1.0f, 0.0f,
//                GetPoint(-30, 30, 30), 1.0f, 1.0f, GetPoint(-30, 30, -30),
//                0.0f, 1.0f, TextureManager.getInstance().getTextureID("wan"));
//
//        // 右
//
//        cube.addTriangle(GetPoint(30, -30, 30), 0.0f, 0.0f,
//                GetPoint(30, -30, -30), 1.0f, 0.0f, GetPoint(30, 30, 30), 0.0f,
//                1.0f, TextureManager.getInstance().getTextureID("wen"));
//
//        cube.addTriangle(GetPoint(30, -30, -30), 1.0f, 0.0f,
//                GetPoint(30, 30, -30), 1.0f, 1.0f, GetPoint(30, 30, 30), 0.0f,
//                1.0f, TextureManager.getInstance().getTextureID("wen"));
//
//
//        cube.strip();
//        cube.build();
//        world.addObject(cube);
//        cube.setCulling(false);
//        cube.scale(0.4f);
//        cube.rotateZ(180);
//    }

    private void CreateBox() {
        cube = new Object3D(0);

        // 前
        yi = new Object3D(2);
        yi.addTriangle(GetPoint(-30, -30, 30), 0.0f, 0.0f,
                GetPoint(30, -30, 30), 1.0f, 0.0f, GetPoint(-30, 30, 30), 0.0f,
                1.0f, TextureManager.getInstance().getTextureID("yi"));

        yi.addTriangle(GetPoint(30, -30, 30), 1.0f, 0.0f,
                GetPoint(30, 30, 30), 1.0f, 1.0f, GetPoint(-30, 30, 30), 0.0f,
                1.0f, TextureManager.getInstance().getTextureID("yi"));
        cube.addChild(yi);
        world.addObject(yi);
        yi.setCollisionMode(Object3D.COLLISION_CHECK_OTHERS);

        // 上
        shi = new Object3D(2);
        shi.addTriangle(GetPoint(-30, 30, 30), 0.0f, 0.0f,
                GetPoint(30, 30, 30), 1.0f, 0.0f, GetPoint(-30, 30, -30), 0.0f,
                1.0f, TextureManager.getInstance().getTextureID("shi"));

        shi.addTriangle(GetPoint(30, 30, 30), 1.0f, 0.0f,
                GetPoint(30, 30, -30), 1.0f, 1.0f, GetPoint(-30, 30, -30),
                0.0f, 1.0f, TextureManager.getInstance().getTextureID("shi"));
        cube.addChild(shi);
        world.addObject(shi);
        shi.setCollisionMode(Object3D.COLLISION_CHECK_OTHERS);

        // 后
        zhu = new Object3D(2);
        zhu.addTriangle(GetPoint(-30, 30, -30), 0.0f, 0.0f,
                GetPoint(30, 30, -30), 1.0f, 0.0f, GetPoint(-30, -30, -30),
                0.0f, 1.0f, TextureManager.getInstance().getTextureID("zhu"));

        zhu.addTriangle(GetPoint(30, 30, -30), 1.0f, 0.0f,
                GetPoint(30, -30, -30), 1.0f, 1.0f, GetPoint(-30, -30, -30),
                0.0f, 1.0f, TextureManager.getInstance().getTextureID("zhu"));
        cube.addChild(zhu);
        world.addObject(zhu);
        zhu.setCollisionMode(Object3D.COLLISION_CHECK_OTHERS);

        // 下
        xing = new Object3D(2);
        xing.addTriangle(GetPoint(-30, -30, -30), 0.0f, 0.0f,
                GetPoint(30, -30, -30), 1.0f, 0.0f, GetPoint(-30, -30, 30),
                0.0f, 1.0f, TextureManager.getInstance().getTextureID("xing"));

        xing.addTriangle(GetPoint(30, -30, -30), 1.0f, 0.0f,
                GetPoint(30, -30, 30), 1.0f, 1.0f, GetPoint(-30, -30, 30), 0.0f,
                1.0f, TextureManager.getInstance().getTextureID("xing"));
        cube.addChild(xing);
        world.addObject(xing);
        xing.setCollisionMode(Object3D.COLLISION_CHECK_OTHERS);

        // 左
        wan = new Object3D(2);
        wan.addTriangle(GetPoint(-30, -30, -30), 0.0f, 0.0f,
                GetPoint(-30, -30, 30), 1.0f, 0.0f, GetPoint(-30, 30, -30),
                0.0f, 1.0f, TextureManager.getInstance().getTextureID("wan"));

        wan.addTriangle(GetPoint(-30, -30, 30), 1.0f, 0.0f,
                GetPoint(-30, 30, 30), 1.0f, 1.0f, GetPoint(-30, 30, -30),
                0.0f, 1.0f, TextureManager.getInstance().getTextureID("wan"));
        cube.addChild(wan);
        world.addObject(wan);
        wan.setCollisionMode(Object3D.COLLISION_CHECK_OTHERS);

        // 右
        wen = new Object3D(2);
        wen.addTriangle(GetPoint(30, -30, 30), 0.0f, 0.0f,
                GetPoint(30, -30, -30), 1.0f, 0.0f, GetPoint(30, 30, 30), 0.0f,
                1.0f, TextureManager.getInstance().getTextureID("wen"));

        wen.addTriangle(GetPoint(30, -30, -30), 1.0f, 0.0f,
                GetPoint(30, 30, -30), 1.0f, 1.0f, GetPoint(30, 30, 30), 0.0f,
                1.0f, TextureManager.getInstance().getTextureID("wen"));
        cube.addChild(wen);
        world.addObject(wen);
        wen.setCollisionMode(Object3D.COLLISION_CHECK_OTHERS);

        cube.strip();
        cube.build();
        world.addObject(cube);
        cube.setCulling(false);
        cube.scale(0.4f);
        cube.rotateZ(180);
        cube.setCollisionMode(Object3D.COLLISION_CHECK_OTHERS);
    }

    public int Pickint(int fX, int fY) {
        //fY = fb.getHeight() - fY;
        SimpleVector dir = Interact2D.reproject2D3DWS(cam, fb, fX, fY).normalize();
        Object[] res = world.calcMinDistanceAndObject3D(cam.getPosition(), dir, 10000);

        Object3D picked = (Object3D) res[1];

        if (picked == null)
            return -1;

        if (picked.getID() == yi.getID())
            return 1;
        else if (picked.getID() == shi.getID())
            return 2;
        else if (picked.getID() == zhu.getID())
            return 3;
        else if (picked.getID() == xing.getID())
            return 4;
        else if (picked.getID() == wan.getID())
            return 5;
        else if (picked.getID() == wen.getID())
            return 6;

        return -1;
    }
}