package com.example.android.project3_baking.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Network {

    public static boolean verifyConnection(Context context){

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null){
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            if (networkInfo != null){
                return networkInfo.isConnected();
            }
        }

        return false;
    }

}
