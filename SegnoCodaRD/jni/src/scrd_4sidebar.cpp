/*
 * scrd_4sidebar.cpp
 *
 *  Created on: Nov 2, 2015
 *      Author: Jeff
 */

#include <scrd_4sidebar.h>

int S_HLTAB[] = { S_4SIDEBAR_HLTAB1, S_4SIDEBAR_HLTAB2, S_4SIDEBAR_HLTAB3,
        S_4SIDEBAR_HLTAB4 };
int SCRD_4SideBar::sSelectedIdx = 0;
int SCRD_4SideBar::sEnabledTabCount = 4;
F3D_Quad *SCRD_4SideBar::sTabs[4];
F3D_Quad *SCRD_4SideBar::sLogo = NULL;
F3D_Quad *SCRD_4SideBar::sBg = NULL;
F3D_Quad *SCRD_4SideBar::sHightlight = NULL;
F3D_QuadGroup *SCRD_4SideBar::sQG = NULL;

void SCRD_4SideBar::init(F3D_QuadGroup *sbQG) {
    sTabs[0] = F3D_JNIStatics::lookupQuad(Q_4TABSIDEBAR_TAB1);
    sTabs[1] = F3D_JNIStatics::lookupQuad(Q_4TABSIDEBAR_TAB2);
    sTabs[2] = F3D_JNIStatics::lookupQuad(Q_4TABSIDEBAR_TAB3);
    sTabs[3] = F3D_JNIStatics::lookupQuad(Q_4TABSIDEBAR_TAB4);
    sBg = F3D_JNIStatics::lookupQuad(Q_4TABSIDEBAR_BG);
    sLogo = F3D_JNIStatics::lookupQuad(Q_4TABSIDEBAR_LOGO);
    sHightlight = F3D_JNIStatics::lookupQuad(Q_4TABSIDEBAR_HIGHTLIGHT);
    sSelectedIdx = 0;
    sQG = sbQG;
}

void SCRD_4SideBar::moveSelectionUp() {

    sSelectedIdx += (sEnabledTabCount - 1);
    sSelectedIdx %= sEnabledTabCount;

    // play in animation for selected tab
    sTabs[sSelectedIdx]->startAnimation(S_4SIDEBAR_TABIN, true);

    // play highlight move animation to selected tab
    sHightlight->startAnimation(S_HLTAB[sSelectedIdx], true);

    if (sQG != NULL) {
        sQG->startAnimation(-1);
    }
}

void SCRD_4SideBar::moveSelectionDown() {

    sSelectedIdx += 1;
    sSelectedIdx %= sEnabledTabCount;

    // play in animation for selected tab
    sTabs[sSelectedIdx]->startAnimation(S_4SIDEBAR_TABIN, true);

    // play highlight move animation to selected tab
    sHightlight->startAnimation(S_HLTAB[sSelectedIdx], true);

    if (sQG != NULL) {
        sQG->startAnimation(-1);
    }
}

void SCRD_4SideBar::handleOK() {

}

void SCRD_4SideBar::handleBack() {

}


void SCRD_4SideBar::enableTabs(int count) {
    if (count < SIDERBAR4_MINTABCOUNT) {
        count = SIDERBAR4_MINTABCOUNT;
    }

    if (count > SIDERBAR4_MAXTABCOUNT) {
        count = SIDERBAR4_MAXTABCOUNT;
    }

    for (int i = 0; i < SIDERBAR4_MAXTABCOUNT; ++i) {
        if (i < count) {
            sTabs[sSelectedIdx]->controlAlpha(1.0f);
        } else {
            sTabs[sSelectedIdx]->controlAlpha(0.0f);
        }
    }

    sEnabledTabCount = count;
    sSelectedIdx = 0;
}
