/*
 * scrd_dpad.cpp
 *
 *  Created on: Nov 4, 2015
 *      Author: Jeff
 */

#include <scrd_dpad.h>

SCRD_DPadSideBarHandler::SCRD_DPadSideBarHandler() {

}

SCRD_DPadSideBarHandler::~SCRD_DPadSideBarHandler() {

}

void SCRD_DPadSideBarHandler::onPointerDown(float clipx, float clipy, F3D_Quad *quad) {
    LOGE("SCRD_DPadHandler::onPointerDown(%f, %f, %p)",
            clipx, clipy, quad);

    if (quad == NULL) {
        return;
    }

    int tag;
    quad->getTag(&tag);

    switch (tag) {
    case Q_DPAD_SIMPLE_UP:
        SCRD_4SideBar::moveSelectionUp();
        break;
    case Q_DPAD_SIMPLE_DOWN:
        SCRD_4SideBar::moveSelectionDown();
        break;
    default:
        break;
    }
}

void SCRD_DPadSideBarHandler::onPointerMove(float clipx, float clipy, F3D_Quad *quad) {
    LOGE("SCRD_DPadHandler::onPointerMove(%f, %f, %p)",
            clipx, clipy, quad);

    if (quad == NULL) {
        return;
    }

    int tag;
    quad->getTag(&tag);

    switch (tag) {
    case Q_DPAD_SIMPLE_UP:
        break;
    case Q_DPAD_SIMPLE_DOWN:
        break;
    default:
        break;
    }
}

void SCRD_DPadSideBarHandler::onPointerUp(float clipx, float clipy, F3D_Quad *quad) {
    LOGE("SCRD_DPadHandler::onPointerUp(%f, %f, %p)",
            clipx, clipy, quad);

    if (quad == NULL) {
        return;
    }

    int tag;
    quad->getTag(&tag);

    switch (tag) {
    case Q_DPAD_SIMPLE_UP:
        break;
    case Q_DPAD_SIMPLE_DOWN:
        break;
    default:
        break;
    }
}

