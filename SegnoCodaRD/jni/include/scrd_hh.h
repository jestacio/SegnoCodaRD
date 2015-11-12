/*
 * scrd_hh.h
 *
 *  Created on: Nov 2, 2015
 *      Author: Jeff
 */

#ifndef SCRD_HH_H_
#define SCRD_HH_H_

#include <f3d_hitdetector.h>

class SCRD_CtxHitHandler {
private:
protected:
public:

    SCRD_CtxHitHandler();
    virtual ~SCRD_CtxHitHandler();

    // leave handler methods as pure virtual
    virtual void onPointerDown(float clipx, float clipy, F3D_Quad *quad) = 0;
    virtual void onPointerMove(float clipx, float clipy, F3D_Quad *quad) = 0;
    virtual void onPointerUp(float clipx, float clipy, F3D_Quad *quad) = 0;
};

class SCRD_HitHandler : public F3D_HitHandler {
private:
    float mDownClipPosX, mDownClipPosY;

    float mSaveAzimuth, mSaveZenith;
    float mDeltaAzimuth, mDeltaZenith;
protected:
public:
    double timeTouchDown;
    SCRD_CtxHitHandler *ctxHandler;

    SCRD_HitHandler();
    ~SCRD_HitHandler();

    void onPointerDown(float clipx, float clipy, F3D_Quad *quad);
    void onPointerMove(float clipx, float clipy, F3D_Quad *quad);
    void onPointerUp(float clipx, float clipy, F3D_Quad *quad);
};

#endif /* SCRD_HH_H_ */
