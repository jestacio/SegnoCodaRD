/*
 * scrd_dpad.h
 *
 *  Created on: Nov 4, 2015
 *      Author: Jeff
 */

#ifndef SCRD_DPAD_H_
#define SCRD_DPAD_H_

#include <scrd_hh.h>
#include <scrd_4sidebar.h>

class SCRD_DPadSideBarHandler : public SCRD_CtxHitHandler {
private:
protected:
public:

    SCRD_DPadSideBarHandler();
    ~SCRD_DPadSideBarHandler();

    void onPointerDown(float clipx, float clipy, F3D_Quad *quad);
    void onPointerMove(float clipx, float clipy, F3D_Quad *quad);
    void onPointerUp(float clipx, float clipy, F3D_Quad *quad);
};


#endif /* SCRD_DPAD_H_ */
