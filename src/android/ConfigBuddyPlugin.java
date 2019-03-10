package com.matadornetwork.configbuddy;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;

import org.apache.cordova.*;
import org.apache.cordova.engine.SystemWebView;
import org.json.JSONArray;
import org.json.JSONException;

public class ConfigBuddyPlugin extends CordovaPlugin {
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        if (action.equals("getSystemTextSize")) {
            float fontScale = cordova.getActivity().getResources().getConfiguration().fontScale;
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, fontScale));
        } else if (action.equals("getTextZoom")) {
            if (isSystemWebView()) {
                cordova.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        float zoom = getSystemWebView().getSettings().getTextZoom();
                        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, zoom));
                    }
                });
            }
        } else if (action.equals("setTextZoom")) {
            if (isSystemWebView()) {
                if (args.length() > 0) {
                    final int zoom = args.getInt(0);
                    if (zoom > 0) {
                        cordova.getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                getSystemWebView().getSettings().setTextZoom(zoom);
                            }
                        });
                    }
                }
            }
        } else if (action.equals("isPowerSaveEnabled")) {
            PowerManager pm = (PowerManager) cordova.getActivity().getSystemService(cordova.getActivity().POWER_SERVICE);
            boolean isPowerSaveMode = false;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                isPowerSaveMode = pm.isPowerSaveMode();

            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, isPowerSaveMode));
        } else if (action.equals("openAppSettings")) {
            Uri packageUri = Uri.fromParts("package", this.cordova.getActivity().getPackageName(), null);
            cordova.startActivityForResult(this, new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageUri), 0);
        } else {
            return false;
        }

        return true;
    }
    private boolean isSystemWebView() {
        return webView.getView() instanceof SystemWebView;
    }
    private SystemWebView getSystemWebView() {
        return (SystemWebView) webView.getView();
    }
}
