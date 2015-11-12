package com.guapo.segnocodard;

import android.content.Context;
import android.os.Bundle;
import android.view.Display;

import com.google.android.gms.cast.CastPresentation;

/**
 * Uses Android presentation framework to render to remote display
 * 
 */
public class SCRD_CastPresentation extends CastPresentation {

    private Context mContext;

    public SCRD_CastPresentation(Context context, Display display) {
        super(context, display);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SCRD_MainActivity.sCastGLView = new SCRD_CastGLView(mContext);

        setContentView(SCRD_MainActivity.sCastGLView);
    }

}
