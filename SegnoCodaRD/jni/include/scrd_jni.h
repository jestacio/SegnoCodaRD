/*
 * sgrd_jni.h
 *
 *  Created on: Oct 31, 2015
 *      Author: Jeff
 */

#ifndef SGRD_JNI_H_
#define SGRD_JNI_H_

#include <scrd.h>

extern "C" {
JNIEXPORT void JNICALL Java_com_guapo_segnocodard_SCRD_1JNI_updateNative(
        JNIEnv * env, jobject obj);
JNIEXPORT void JNICALL Java_com_guapo_segnocodard_SCRD_1JNI_initNative(
        JNIEnv * env, jobject obj, jint programHandle);
JNIEXPORT void JNICALL Java_com_guapo_segnocodard_SCRD_1JNI_setScreenDimensionsNative(
        JNIEnv * env, jobject obj, jint width, jint height);
JNIEXPORT void JNICALL Java_com_guapo_segnocodard_SCRD_1JNI_setCastScreenDimensionsNative(
        JNIEnv * env, jobject obj, jint width, jint height);
JNIEXPORT void JNICALL Java_com_guapo_segnocodard_SCRD_1JNI_clearTMUCacheNative(
        JNIEnv * env, jobject obj);
JNIEXPORT void JNICALL Java_com_guapo_segnocodard_SCRD_1JNI_resetNativeStaticsNative(
        JNIEnv * env, jobject obj);
}

#endif /* SGRD_JNI_H_ */
