package com.guapo.segnocodard;

import java.nio.ByteBuffer;

/**
 * 
 * Holds constants for assets loaded for the hatchf3d engine
 */
public class SCRD_Assets {
    public static final String VSHADER_SRC_DEBUG = "precision mediump float;\n"
            // This matrix member variable provides a hook to manipulate
            // the coordinates of objects that use this vertex shader.
            + "uniform mat4 uMVPMatrix;\n" // ---------------------------------
            + "uniform sampler2D uTexture;\n" // ------------------------------
            + "uniform float uAlpha;\n" // ------------------------------------
            + "attribute vec3 aPosition;\n" // --------------------------------
            + "attribute vec2 aTexCoord;\n" // --------------------------------
            + "varying vec2 vTexCoord;\n" // ----------------------------------
            + "varying vec3 vFragCoord;\n" // ---------------------------------
            + "varying vec2 vBlurTexCoords[14];\n"
            + "void main(){\n" // ---------------------------------------------
            // The matrix must be included as part of gl_Position
            // Note that the uMVPMatrix factor *must be first* in order
            // for the matrix multiplication product to be correct.
            + "    gl_Position = uMVPMatrix * vec4(aPosition, 1.0);\n"
            + "    vTexCoord = aTexCoord;\n"
            + "    vFragCoord = gl_Position.xyz;\n" // ------------------------
            + "}\n"; // -------------------------------------------------------

    public static final String FSHADER_SRC_DEBUG = "precision mediump float;\n"
            + "uniform mat4 uMVPMatrix;\n" // ---------------------------------
            + "uniform sampler2D uTexture;\n" // ------------------------------
            + "uniform float uAlpha;\n" // ------------------------------------
            + "varying vec2 vTexCoord;\n" // ----------------------------------
            + "varying vec3 vFragCoord;\n" // ---------------------------------
            + "varying float vDistance;\n" // ---------------------------------
            + "void main() {\n"
            + "    vec4 color = texture2D(uTexture, vTexCoord);\n"
            + "    gl_FragColor = vec4(color.rgb, color.a * uAlpha);\n" + "}"; // ---------------------------------------------------------

    static final class BitmapInfo {
        public String path;
        public int tag, width, height;
        public ByteBuffer buf;

        BitmapInfo(String p, int t) {
            path = p;
            tag = t;
            buf = null;
        }
    }

    static final BitmapInfo ALL_BITMAPS[] = {
            new BitmapInfo("bitmaps/83000.png", 83000),
            new BitmapInfo("bitmaps/83001.png", 83001),
            new BitmapInfo("bitmaps/83002.png", 83002) };

    static final int QG_4SIDEBAR = 45100;
    static final String QGS_4SIDEBAR[] = { "45100/quadGroup_45100.txt" };
    static final String TCS_4SIDEBAR[] = { "45100/texConfig_45100.txt" };
    static final String TSS_4SIDEBAR[] = { "45100/triggerSet_45181.txt",
            "45100/triggerSet_45182.txt", "45100/triggerSet_45183.txt" };
    static final String QUADS_4SIDEBAR[] = { "45100/quad_45100.txt",
            "45100/quad_45101.txt", "45100/quad_45102.txt",
            "45100/quad_45103.txt", "45100/quad_45104.txt",
            "45100/quad_45105.txt", "45100/quad_45106.txt" };
    static final int QTAGS_4SIDEBAR[] = { 45100, 45101, 45102, 45103, 45104,
            45105, 45106 };

    static final int QG_DPAD_SIMPLE = 1100;
    static final String QGS_DPAD_SIMPLE[] = { "1100/quadGroup_1100.txt" };
    static final String TCS_DPAD_SIMPLE[] = { "1100/texConfig_1100.txt" };
    static final String QUADS_DPAD_SIMPLE[] = { "1100/quad_1100.txt",
            "1100/quad_1101.txt", "1100/quad_1102.txt", "1100/quad_1103.txt",
            "1100/quad_1104.txt", "1100/quad_1105.txt" };
    static final int QTAGS_DPAD_SIMPLE[] = { 1100, 1101, 1102, 1103, 1104, 1105 };
}
