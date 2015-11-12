package com.guapo.segnocodard;

import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.google.android.gms.cast.CastRemoteDisplayLocalService;

/**
 * The life cycle of the remote view is tied to the life cycle of this service
 * Use this service to interface with the remote view with the local view is not
 * in the device foreground
 * 
 */
public class SCRD_PresentationService extends CastRemoteDisplayLocalService {

    private static final String TAG = "SGRD_PresentationService";
    private SCRD_CastPresentation mPresentation;

    @Override
    public void onCreatePresentation(Display display) {
        Log.d(TAG, "onCreatePresentation");
        createPresentation(display);
    }

    @Override
    public void onDismissPresentation() {
        Log.d(TAG, "onDismissPresentation");
        dismissPresentation();
    }

    private void createPresentation(Display display) {
        Point dispSize = new Point();
        display.getSize(dispSize);
        dismissPresentation();
        mPresentation = new SCRD_CastPresentation(this, display);
        try {
            mPresentation.show();
        } catch (WindowManager.InvalidDisplayException ex) {
            Log.e(TAG,
                    "Unable to show presentation, display was " + "removed.",
                    ex);
            dismissPresentation();
        }
    }

    private void dismissPresentation() {
        if (mPresentation != null) {
            mPresentation.dismiss();
            mPresentation = null;
        }
    }

}
