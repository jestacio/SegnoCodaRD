package com.guapo.segnocodard;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.MediaRouteActionProvider;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;
import android.support.v7.media.MediaRouter.RouteInfo;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.CastMediaControlIntent;
import com.google.android.gms.cast.CastRemoteDisplayLocalService;
import com.google.android.gms.common.api.Status;

/**
 * This activity shows the local view
 * 
 */
public class SCRD_MainActivity extends AppCompatActivity {
    static {
        System.loadLibrary("SCRD");
    }

    private static final String TAG = "SegnoCodaRD.MainActivity";
    private static final String APP_ID = "5A696D1E";

    static SCRD_LocalGLView sLocalGLView = null;
    static SCRD_CastGLView sCastGLView = null;

    private CastDevice mSelectedDevice;
    private MediaRouter mMediaRouter;
    private SGRD_MediaRouterCallback mMediaRouterCallback;
    private MediaRouteSelector mMediaRouteSelector;

    private class SGRD_MediaRouterCallback extends MediaRouter.Callback {

        @Override
        public void onRouteSelected(MediaRouter router, RouteInfo info) {
            Log.d(TAG, "onRouteSelected");
            
            mSelectedDevice = CastDevice.getFromBundle(info.getExtras());

            if (mSelectedDevice == null) {
                Log.d(TAG, "mSelectedDevice is null");
                return;
            }

            String routeId = info.getId();

            Log.d(TAG, "onRouteSelected routeId = " + routeId);

            Intent intent = new Intent(SCRD_MainActivity.this, SCRD_MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent notificationPendingIntent = PendingIntent
                    .getActivity(SCRD_MainActivity.this, 0, intent, 0);

            CastRemoteDisplayLocalService.NotificationSettings settings = new CastRemoteDisplayLocalService.NotificationSettings.Builder()
                    .setNotificationPendingIntent(notificationPendingIntent)
                    .build();

            CastRemoteDisplayLocalService.startService(SCRD_MainActivity.this,
                    SCRD_PresentationService.class, APP_ID, mSelectedDevice,
                    settings, new CastRemoteDisplayLocalService.Callbacks() {
                        @Override
                        public void onRemoteDisplaySessionStarted(
                                CastRemoteDisplayLocalService service) {
                            // initialize sender UI
                            Log.d(TAG, "onRemoteDisplaySessionStarted");

                            RelativeLayout l = (RelativeLayout) findViewById(R.id.mainlayout);
                            l.addView(sLocalGLView);
                        }

                        @Override
                        public void onRemoteDisplaySessionError(
                                Status errorReason) {
                            // initError();
                            Log.d(TAG, "onRemoteDisplaySessionError "
                                    + errorReason.getStatusMessage());
                        }
                    });
        }

        @Override
        public void onRouteUnselected(MediaRouter router, RouteInfo info) {
            Log.d(TAG, "onRouteUnselected");
            teardown();
            mSelectedDevice = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMediaRouterCallback = new SGRD_MediaRouterCallback();
        mMediaRouter = MediaRouter.getInstance(this);
        mMediaRouteSelector = new MediaRouteSelector.Builder()
                .addControlCategory(
                        CastMediaControlIntent.categoryForCast(APP_ID)).build();

        Log.d(TAG, "onCreate loading hatchf3d assets");

        // load all assets in this rendering context
        // load gpu context for local rendering thread
        if (sLocalGLView == null) {
            AssetManager assetMgr = getResources().getAssets();
            sLocalGLView = new SCRD_LocalGLView(this);
            sLocalGLView.setAssetManager(assetMgr);
        }

        Log.d(TAG, "onCreate END");
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem mediaRouteMenuItem = menu.findItem(R.id.media_route_menu_item);
        MediaRouteActionProvider mediaRouteActionProvider = (MediaRouteActionProvider) MenuItemCompat
                .getActionProvider(mediaRouteMenuItem);
        mediaRouteActionProvider.setRouteSelector(mMediaRouteSelector);

        Log.d(TAG, "onCreateOptionsMenu");
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMediaRouter.addCallback(mMediaRouteSelector, mMediaRouterCallback,
                MediaRouter.CALLBACK_FLAG_REQUEST_DISCOVERY);

        Log.d(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        mMediaRouter.removeCallback(mMediaRouterCallback);
        super.onStop();

        Log.d(TAG, "onStop");
    }

    private void teardown() {
        Log.d(TAG, "teardown");
        CastRemoteDisplayLocalService.stopService();

        RelativeLayout l = (RelativeLayout) findViewById(R.id.mainlayout);
        l.removeView(sLocalGLView);

        SCRD_JNI.resetNativeStatics();
    }
}
