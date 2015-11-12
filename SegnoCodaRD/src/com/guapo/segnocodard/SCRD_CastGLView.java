package com.guapo.segnocodard;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;

/**
 * Handles rendering the remote display surface
 * 
 */
public class SCRD_CastGLView extends SCRD_GLView {
    private static final String TAG = "SCRD_CastGLView";

    private class SGRD_CastRenderer extends HatchF3D_Renderer {

        private int mTextureProgramHandle;
        private Point mSurfaceDims;

        public SGRD_CastRenderer() {
            mSurfaceDims = new Point();
        }

        @Override
        public void onDrawFrame(GL10 gl) {

            // don't call super
            // let local view instance execute update
            // and request render of remote display view
            // super.onDrawFrame(gl);

            Log.d(TAG, "onDrawFrame");

            super.onSurfaceChanged(null, mSurfaceDims.x, mSurfaceDims.y);

            // texture mapping unit LRU cache
            // since we are in a separate
            SCRD_JNI.clearTMUCache();
            setClearColor(0.0f, 0.0f, 0.0f, 1.0f);
            draw(SCRD_Assets.QG_4SIDEBAR, true);
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {

            // need to call super method to set viewport for this display
            super.onSurfaceChanged(gl, width, height);

            // need to update perspective projection for
            // quad groups drawn by this rendering thread
            SCRD_JNI.setCastScreenDimensions(width, height);

            Log.d(TAG, "onSurfaceChanged");

            // camera/projection init
            mSurfaceDims.x = width;
            mSurfaceDims.y = height;
        }

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {

            // don't call super method to prevent clearing statics
            // super.onSurfaceCreated(gl, config);

            // GPU init
            mTextureProgramHandle = loadProgram(SCRD_Assets.VSHADER_SRC_DEBUG,
                    SCRD_Assets.FSHADER_SRC_DEBUG);

            // load bitmaps
            loadBitmapAssets();

            loadQuads(SCRD_Assets.QUADS_4SIDEBAR);
            loadTriggerSets(SCRD_Assets.TSS_4SIDEBAR);
            loadQuadGroups(SCRD_Assets.QGS_4SIDEBAR);
            loadTexConfigs(SCRD_Assets.TCS_4SIDEBAR);

            // init extended statics
            SCRD_JNI.init(mTextureProgramHandle);

            Log.d(TAG, "onSurfaceCreated");

            setProgram(mTextureProgramHandle, SCRD_Assets.QTAGS_4SIDEBAR);
        }

    }

    private SGRD_CastRenderer mRenderer;

    public SCRD_CastGLView(Context context) {
        super(context);
    }

    @Override
    protected HatchF3D_Renderer getRenderer() {

        if (mRenderer == null) {
            mRenderer = new SGRD_CastRenderer();
        }

        return mRenderer;
    }

}
