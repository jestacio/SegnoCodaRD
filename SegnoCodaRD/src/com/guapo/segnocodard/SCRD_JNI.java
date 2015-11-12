package com.guapo.segnocodard;

public class SCRD_JNI {

    static void update() {
        synchronized (com.guapo.hatchf3d.HatchF3D_JNI.sLock) {
            updateNative();
        }
    }

    static void init(int programHandle) {
        synchronized (com.guapo.hatchf3d.HatchF3D_JNI.sLock) {
            initNative(programHandle);
        }
    }

    static void setScreenDimensions(int width, int height) {
        synchronized (com.guapo.hatchf3d.HatchF3D_JNI.sLock) {
            setScreenDimensionsNative(width, height);
        }
    }

    static void setCastScreenDimensions(int width, int height) {
        synchronized (com.guapo.hatchf3d.HatchF3D_JNI.sLock) {
            setCastScreenDimensionsNative(width, height);
        }
    }

    static void clearTMUCache() {
        synchronized (com.guapo.hatchf3d.HatchF3D_JNI.sLock) {
            clearTMUCacheNative();
        }
    }

    static void resetNativeStatics() {
        synchronized (com.guapo.hatchf3d.HatchF3D_JNI.sLock) {
            resetNativeStaticsNative();
        }
    }

    private static native void updateNative();

    private static native void initNative(int programHandle);

    private static native void setScreenDimensionsNative(int width, int height);

    private static native void setCastScreenDimensionsNative(int width,
            int height);

    private static native void clearTMUCacheNative();

    private static native void resetNativeStaticsNative();
}
