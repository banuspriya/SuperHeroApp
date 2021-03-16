package com.superhero.myapplication.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;

public class NetworkUtility {

    //function to check connectivity
    public static boolean getConnectivity(Context context) {
        boolean result=false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (capabilities != null) {
                if ((capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) || (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))) {
                    result = true;
                }
            } else {
                result = false;
            }
        }

        return result;
    }

}
