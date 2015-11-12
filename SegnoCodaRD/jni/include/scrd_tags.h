/*
 * sgrd_tags.h
 *
 *  Created on: Oct 30, 2015
 *      Author: Jeff
 */

#ifndef SGRD_TAGS_H_
#define SGRD_TAGS_H_

/*
 * Tag naming convention
 * QG_ -> quad group
 * Q_ -> individual quad
 * TS_ -> trigger set for quad group
 * S_ -> animation key frame sequence
 * TEX_ -> texture tag
 */

#define QG_4TABSIDEBAR 45100
#define Q_4TABSIDEBAR_BG 45100
#define Q_4TABSIDEBAR_HIGHTLIGHT 45101
#define Q_4TABSIDEBAR_LOGO 45102
#define Q_4TABSIDEBAR_TAB1 45103
#define Q_4TABSIDEBAR_TAB2 45104
#define Q_4TABSIDEBAR_TAB3 45105
#define Q_4TABSIDEBAR_TAB4 45106
#define TS_4SIDEBAR_HIDE 45181
#define TS_4SIDEBAR_IN 45182
#define TS_4SIDEBAR_OUT 45183
#define S_4SIDEBAR_TABIN 1127
#define S_4SIDEBAR_TABOUT 1128
#define S_4SIDEBAR_HLTAB1 1127
#define S_4SIDEBAR_HLTAB2 1128
#define S_4SIDEBAR_HLTAB3 1129
#define S_4SIDEBAR_HLTAB4 1130

#define QG_DPAD_SIMPLE 1100
#define Q_DPAD_SIMPLE_OK 1100
#define Q_DPAD_SIMPLE_LEFT 1101
#define Q_DPAD_SIMPLE_UP 1102
#define Q_DPAD_SIMPLE_RIGHT 1103
#define Q_DPAD_SIMPLE_DOWN 1104

#define Q_DPAD_SIMPLE_BACK 1105
#define TEX_IC_PLAY 56000

#endif /* SGRD_TAGS_H_ */
