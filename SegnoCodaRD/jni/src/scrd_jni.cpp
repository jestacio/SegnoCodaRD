#include <scrd_jni.h>

JNIEXPORT void JNICALL Java_com_guapo_hatchf3d_HatchF3D_1JNI_initNative(
        JNIEnv * env, jobject obj) {
    LOGE("HatchF3D initNative in SCRD project");

    SCRD_CastStatics::sDPadSBHandler = new SCRD_DPadSideBarHandler();
    SCRD_CastStatics::sHitHandler = new SCRD_HitHandler();
    SCRD_CastStatics::sHitHandler->ctxHandler =
            SCRD_CastStatics::sDPadSBHandler;
    F3D_JNIStatics::sHitDetector.setHandler(SCRD_CastStatics::sHitHandler);
}

JNIEXPORT void JNICALL Java_com_guapo_segnocodard_SCRD_1JNI_updateNative(
        JNIEnv * env, jobject obj) {
    LOGE("updateNative");
}

JNIEXPORT void JNICALL Java_com_guapo_segnocodard_SCRD_1JNI_initNative(
        JNIEnv * env, jobject obj, jint programHandle) {
    LOGE("SCRD initNative");

    SCRD_CastStatics::sQG_4sidebar = F3D_JNIStatics::lookupQuadGroup(
            QG_4TABSIDEBAR);
    SCRD_CastStatics::sQG_dpad_simple = F3D_JNIStatics::lookupQuadGroup(
            QG_DPAD_SIMPLE);

    SCRD_4SideBar::init(SCRD_CastStatics::sQG_4sidebar);

    LOGE("SCRD initNative loaded dpad simple=%p 4sidebar=%p",
            SCRD_CastStatics::sQG_dpad_simple, SCRD_CastStatics::sQG_4sidebar);
}

JNIEXPORT void JNICALL Java_com_guapo_segnocodard_SCRD_1JNI_setScreenDimensionsNative(
        JNIEnv * env, jobject obj, jint width, jint height) {
    LOGE("setScreenDimensionsNative");

    float fovy, near, far, aspect;
    float newAspect = static_cast<float>(width) / static_cast<float>(height);
    std::vector<F3D_Quad *> *quads;
    SCRD_CastStatics::sQG_dpad_simple->getQuads(&quads);
    for (std::vector<F3D_Quad *>::iterator it = quads->begin();
            it != quads->end(); ++it) {
        (*it)->getPerspectiveProjection(&fovy, &near, &far, &aspect);
        (*it)->setPerspectiveProjection(fovy, near, far, newAspect);
        (*it)->setPerspectiveCamera(0.0f, 0.0f, CAM_DEFAULTHEIGHT);
    }
}

JNIEXPORT void JNICALL Java_com_guapo_segnocodard_SCRD_1JNI_setCastScreenDimensionsNative(
        JNIEnv * env, jobject obj, jint width, jint height) {
    LOGE("setCastScreenDimensionsNative");

    float fovy, near, far, aspect;
    float newAspect = static_cast<float>(width) / static_cast<float>(height);
    std::vector<F3D_Quad *> *quads;
    SCRD_CastStatics::sQG_4sidebar->getQuads(&quads);
    for (std::vector<F3D_Quad *>::iterator it = quads->begin();
            it != quads->end(); ++it) {
        (*it)->getPerspectiveProjection(&fovy, &near, &far, &aspect);
        (*it)->setPerspectiveProjection(fovy, near, far, newAspect);
        (*it)->setPerspectiveCamera(0.0f, 0.0f, CAM_DEFAULTHEIGHT);
    }
}

JNIEXPORT void JNICALL Java_com_guapo_segnocodard_SCRD_1JNI_clearTMUCacheNative(
        JNIEnv * env, jobject obj) {
    F3D_Bitmap::clearLastLoaded();
}

JNIEXPORT void JNICALL Java_com_guapo_segnocodard_SCRD_1JNI_resetNativeStaticsNative(
        JNIEnv * env, jobject obj) {
    std::vector<F3D_Quad *>().swap(F3D_JNIStatics::sQuads);
    std::vector<F3D_QuadGroup *>().swap(F3D_JNIStatics::sQuadGroups);
    std::vector<F3D_Bitmap *>().swap(F3D_JNIStatics::sBitmaps);
    std::vector<int>().swap(F3D_JNIStatics::sAnimationQueue_QGTags);
    std::vector<int>().swap(F3D_JNIStatics::sAnimationQueue_TSTags);
    std::vector<bool>().swap(F3D_JNIStatics::sAnimationQueue_clearFlags);
    std::vector<F3D_Program *>().swap(F3D_JNIStatics::sPrograms);
    std::vector<F3D_Texture *>().swap(F3D_JNIStatics::sTextures);
    std::vector<F3D_TriggerSet *>().swap(F3D_JNIStatics::sTriggerSets);
    F3D_Bitmap::clearLastLoaded();
    F3D_JNIStatics::sHitDetector.clearQuads();
}
