LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := HatchF3D
LOCAL_SRC_FILES := ../../HatchF3DEngine/obj/local/armeabi-v7a/libHatchF3D.a
LOCAL_EXPORT_C_INCLUDES := 
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)

LOCAL_C_INCLUDES := \
		$(LOCAL_PATH)/include \
		$(LOCAL_PATH)/../../HatchF3DEngine/jni/include
LOCAL_CFLAGS	:=  -O0 -g
LOCAL_LDLIBS 	:=	-llog -lGLESv2 -lm -landroid
LOCAL_STATIC_LIBRARIES := libHatchF3D
LOCAL_MODULE    := SCRD
LOCAL_SRC_FILES := \
		src/scrd.cpp \
		src/scrd_hh.cpp \
		src/scrd_4sidebar.cpp \
		src/scrd_dpad.cpp \
		src/scrd_jni.cpp

include $(BUILD_SHARED_LIBRARY)
