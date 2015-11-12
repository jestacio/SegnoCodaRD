package com.guapo.segnocodard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.util.LinkedList;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.guapo.hatchf3d.HatchF3D_GLSurfaceView;
import com.guapo.hatchf3d.HatchF3D_Texture;
import com.guapo.segnocodard.SCRD_Assets.BitmapInfo;

public abstract class SCRD_GLView extends HatchF3D_GLSurfaceView {
    private static final String TAG = "SCRD_GLView";

    protected LinkedList<HatchF3D_Texture> mRuntimeTextures;

    public SCRD_GLView(Context context) {
        super(context);
        mRuntimeTextures = new LinkedList<HatchF3D_Texture>();
    }

    protected void loadTexture(float width, float height, float x, float y,
            int textureTag, int bitmapTag) {
        float bl_x, bl_y, br_x, br_y, tl_x, tl_y, tr_x, tr_y;

        // lookup bitmap node
        BitmapInfo bmpInfo = null;
        for (BitmapInfo bmi : SCRD_Assets.ALL_BITMAPS) {
            if (bmi.tag == bitmapTag) {
                bmpInfo = bmi;
                break;
            }
        }

        if (bmpInfo == null) {
            Log.w(TAG, "loadTexture: Could not find bitmap " + bitmapTag);
            return;
        }

        float bitmapWidth = bmpInfo.width;
        if (bitmapWidth == 0.0f) {
            bitmapWidth = 1.0f;
        }

        float bitmapHeight = bmpInfo.height;
        if (bitmapHeight == 0.0f) {
            bitmapHeight = 1.0f;
        }

        bl_x = x / bitmapWidth;
        bl_y = 1.0f - (y / bitmapHeight);
        br_y = bl_y;
        br_x = bl_x + (width / bitmapWidth);
        tl_x = bl_x;
        tl_y = bl_y - (height / bitmapHeight);
        tr_x = br_x;
        tr_y = tl_y;

        HatchF3D_Texture newTexture = new HatchF3D_Texture(textureTag,
                bitmapTag, new float[] { bl_x, bl_y, tr_x, tr_y, tl_x, tl_y,
                        bl_x, bl_y, br_x, br_y, tr_x, tr_y });
        loadTexture(newTexture);
        mRuntimeTextures.addLast(newTexture);
    }

    void loadBitmapAssets() {
        AssetManager assetManager = mContext.getAssets();
        InputStream istr;
        Bitmap bitmap = null;

        for (int i = 0; i < SCRD_Assets.ALL_BITMAPS.length; ++i) {
            if (SCRD_Assets.ALL_BITMAPS[i].buf == null) {

                try {
                    istr = assetManager.open(SCRD_Assets.ALL_BITMAPS[i].path);
                    bitmap = BitmapFactory.decodeStream(istr);
                } catch (IOException e) {
                    Log.e(TAG, "", e);
                    continue;
                }

                // load quad config tool bitmap
                SCRD_Assets.ALL_BITMAPS[i].buf = ByteBuffer
                        .allocateDirect(bitmap.getByteCount());
                bitmap.copyPixelsToBuffer(SCRD_Assets.ALL_BITMAPS[i].buf);
                SCRD_Assets.ALL_BITMAPS[i].width = bitmap.getWidth();
                SCRD_Assets.ALL_BITMAPS[i].height = bitmap.getHeight();
                // Log.e(TAG, String.format("DS Bitmap dimensions: %d, %d",
                // dsBitmaps[i].width, dsBitmaps[i].height));
                bitmap.recycle();
            }

            loadBitmap(SCRD_Assets.ALL_BITMAPS[i].tag,
                    SCRD_Assets.ALL_BITMAPS[i].buf.array(),
                    SCRD_Assets.ALL_BITMAPS[i].width,
                    SCRD_Assets.ALL_BITMAPS[i].height);
        }

        // parse texture definition file
        // load textures into engine
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(
                    assetManager.open("bitmaps/tex_definition.txt")));
        } catch (IOException e) {
            Log.w(TAG, "Error reading texture definition file", e);
            return;
        }

        // read number of textures defined in file
        String line = null;
        try {
            line = br.readLine();
        } catch (IOException e) {
            Log.w(TAG, "", e);
        }

        int numTextures = 0;
        try {
            numTextures = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            Log.w(TAG, "", e);
            numTextures = 0;
        }

        int bitmapTag = -1;
        int textureTag = -1;
        float width = 0.0f, height = 0.0f, x = 0.0f, y = 0.0f;

        final boolean DEBUG_TEXTURES = true;
        if (DEBUG_TEXTURES)
            Log.d(TAG, "loadBitmapAssets: num textures: " + line);
        for (int i = 0; i < numTextures; ++i) {
            try {
                // read texture comment
                line = br.readLine();
                if (DEBUG_TEXTURES)
                    Log.d(TAG, "loadBitmapAssets: loading texture: " + line);

                // read texture tag
                line = br.readLine();
                textureTag = Integer.parseInt(line);

                // read texture bitmap tag
                line = br.readLine();
                bitmapTag = Integer.parseInt(line);

                // read width
                line = br.readLine();
                width = Float.parseFloat(line);

                // read height
                line = br.readLine();
                height = Float.parseFloat(line);

                // read x
                line = br.readLine();
                x = Float.parseFloat(line);

                // read y
                line = br.readLine();
                y = Float.parseFloat(line);

                loadTexture(width, height, x, y, textureTag, bitmapTag);
            } catch (IOException e) {
                Log.w(TAG, "", e);
            } catch (NumberFormatException e) {
                Log.w(TAG, "", e);
            }
        }

        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
