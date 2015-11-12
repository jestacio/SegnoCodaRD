/*
 * sgrd.h
 *
 *  Created on: Oct 31, 2015
 *      Author: Jeff
 */

#ifndef SGRD_H_
#define SGRD_H_


#include <f3d_jni.h>
#include <f3d_fileio.h>
#include <scrd_tags.h>
#include <scrd_hh.h>
#include <scrd_4sidebar.h>
#include <scrd_dpad.h>

// camera stuff
#define CAM_DEFAULTHEIGHT 2.0f

/*
 * Singleton instances
 */
class SCRD_CastStatics {
private:
protected:
public:
    static F3D_QuadGroup *sQG_4sidebar;
    static F3D_QuadGroup *sQG_dpad_simple;
    static SCRD_HitHandler *sHitHandler;
    static SCRD_DPadSideBarHandler *sDPadSBHandler;
};

#endif /* SGRD_H_ */
