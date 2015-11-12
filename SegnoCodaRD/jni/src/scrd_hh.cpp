/*
 * scrd_hh.cpp
 *
 *  Created on: Nov 2, 2015
 *      Author: Jeff
 */

#include <scrd_hh.h>

SCRD_CtxHitHandler::SCRD_CtxHitHandler() {

}

SCRD_CtxHitHandler::~SCRD_CtxHitHandler() {

}

SCRD_HitHandler::SCRD_HitHandler() {
    mDownClipPosX = 0.0f;
    mDownClipPosY = 0.0f;
    mSaveAzimuth = 0.0f;
    mSaveZenith = 0.0f;
    mDeltaAzimuth = 0.0f;
    mDeltaZenith = 0.0f;

    timeTouchDown = 0.0f;
    ctxHandler = NULL;
}

SCRD_HitHandler::~SCRD_HitHandler() {

}

void SCRD_HitHandler::onPointerDown(float clipx, float clipy, F3D_Quad *quad) {
    LOGE("SCRD_HitHandler::onPointerDown (%f, %f, %p)", clipx, clipy, quad);

    if (ctxHandler != NULL) {
        ctxHandler->onPointerDown(clipx, clipy, quad);
    }
}

void SCRD_HitHandler::onPointerMove(float clipx, float clipy, F3D_Quad *quad) {
    LOGE("SCRD_HitHandler::onPointerDown (%f, %f, %p)", clipx, clipy, quad);

    if (ctxHandler != NULL) {
        ctxHandler->onPointerMove(clipx, clipy, quad);
    }
}

void SCRD_HitHandler::onPointerUp(float clipx, float clipy, F3D_Quad *quad) {
    LOGE("SCRD_HitHandler::onPointerDown (%f, %f, %p)", clipx, clipy, quad);

    if (ctxHandler != NULL) {
        ctxHandler->onPointerUp(clipx, clipy, quad);
    }
}
