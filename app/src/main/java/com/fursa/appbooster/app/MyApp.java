package com.fursa.appbooster.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by Fursa Ilya on 10.11.17.
 */

public class MyApp extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        MyApp.context = getApplicationContext();
    }

    public static Context getContext() {
        return MyApp.context;
    }

}
