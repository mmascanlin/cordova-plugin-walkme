package com.madmobile.plugins;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;

import abbi.io.abbisdk.ABBIFlags;
import abbi.io.abbisdk.ABBI;

public class WalkMe extends CordovaPlugin {
    @Override
    public void initialize(final CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);

        String appKey = this.preferences.getString("WalkMeAppKey", null);
        String appSecret = this.preferences.getString("WalkMeAppSecret", null);
        ABBI.setFlag(ABBIFlags.ABBI_APP_HYBRID.getValue());

        ABBI.start(appKey, appSecret,  cordova.getActivity().getApplication() );
    }
}
