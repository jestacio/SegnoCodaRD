package com.guapo.segnocodard;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;

public class SCRD_LocalGLView extends SCRD_GLView {
    private static final String TAG = "SCRD_CastGLView";

    private class SGRD_LocalRenderer extends HatchF3D_Renderer {

        private int mTextureProgramHandle;
        private Point mSurfaceDims;

        public SGRD_LocalRenderer() {
            mSurfaceDims = new Point();
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            super.onDrawFrame(gl);

            if (SCRD_MainActivity.sCastGLView != null) {
                SCRD_MainActivity.sCastGLView.requestRender();
            }

            Log.d(TAG, "onDrawFrame");

            super.onSurfaceChanged(null, mSurfaceDims.x, mSurfaceDims.y);

            SCRD_JNI.clearTMUCache();
            setClearColor(0.0f, 0.0f, 0.0f, 1.0f);
            draw(SCRD_Assets.QG_DPAD_SIMPLE, true);
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {

            // need to call super method to set viewport for this display
            super.onSurfaceChanged(gl, width, height);

            // need to update perspective camera for
            // quad groups drawn by this rendering thread
            SCRD_JNI.setScreenDimensions(width, height);

            Log.d(TAG, "onSurfaceChanged");

            // camera/projection init
            mSurfaceDims.x = width;
            mSurfaceDims.y = height;
        }

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            super.onSurfaceCreated(gl, config);

            // GPU init
            mTextureProgramHandle = loadProgram(SCRD_Assets.VSHADER_SRC_DEBUG,
                    SCRD_Assets.FSHADER_SRC_DEBUG);

            // load bitmaps
            loadBitmapAssets();

            loadQuads(SCRD_Assets.QUADS_DPAD_SIMPLE);
            loadQuadGroups(SCRD_Assets.QGS_DPAD_SIMPLE);
            loadTexConfigs(SCRD_Assets.TCS_DPAD_SIMPLE);

            // init extended statics
            SCRD_JNI.init(mTextureProgramHandle);

            Log.d(TAG, "onSurfaceCreated");

            setProgram(mTextureProgramHandle, SCRD_Assets.QTAGS_DPAD_SIMPLE);
        }

    }

    private SGRD_LocalRenderer mRenderer;

    public SCRD_LocalGLView(Context context) {
        super(context);
    }

    @Override
    protected HatchF3D_Renderer getRenderer() {

        if (mRenderer == null) {
            mRenderer = new SGRD_LocalRenderer();
        }

        return mRenderer;
    }

}
