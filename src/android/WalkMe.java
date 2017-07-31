package com.madmobile.plugins;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.HashMap;
import java.util.Iterator;
import android.util.Log;

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

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("sendGoal".equals(action)) {
            this.sendGoal(args);
            return true;
        }
        return false;  // Returning false results in a "MethodNotFound" error.
    }

    private void sendGoal(JSONArray args) throws JSONException {
        String TAG = "WalkMe";
        Log.v(TAG, "Entering sendGoal");

        if (args.length() == 1) {
            ABBI.sendGoal(args.getString(0), null);
            Log.v(TAG, "Sent goal without properties");
        } else {
            try {
                String goalName = args.getString(0);
                JSONObject jObject = args.getJSONObject(1);
                HashMap<String, String> properties = new HashMap<String, String>();
                Iterator<?> keys = jObject.keys();

                while( keys.hasNext() ){
                    String key = (String)keys.next();
                    String value = jObject.getString(key);
                    properties.put(key, value);
                }

                ABBI.sendGoal(goalName, properties);
                Log.v(TAG, "Sent goal with properties");
            } catch (JSONException e) {
                Log.e(TAG, "Error Sending Goal");
            }
        }
    }

}
