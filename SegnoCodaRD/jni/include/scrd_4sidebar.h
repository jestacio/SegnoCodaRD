/*
 * scrd_4sidebar.h
 *
 *  Created on: Nov 2, 2015
 *      Author: Jeff
 */

#ifndef SCRD_4SIDEBAR_H_
#define SCRD_4SIDEBAR_H_

#include <f3d_quad.h>
#include <f3d_quadgroup.h>
#include <f3d_jni.h>
#include <scrd_tags.h>

#define SIDERBAR4_MINTABCOUNT 1
#define SIDERBAR4_MAXTABCOUNT 4


class SCRD_4SideBar {
private:
    static int sSelectedIdx;
    static int sEnabledTabCount;
    static F3D_Quad *sTabs[];
    static F3D_Quad *sLogo;
    static F3D_Quad *sBg;
    static F3D_Quad *sHightlight;
    static F3D_QuadGroup *sQG;

protected:
public:

    static void init(F3D_QuadGroup *sbQG);
    static void handleOK();
    static void moveSelectionUp();
    static void moveSelectionDown();
    static void handleBack();
    static void enableTabs(int count);
};


#endif /* SCRD_4SIDEBAR_H_ */
