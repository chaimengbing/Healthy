package com.health.infrared;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;


/**
 * Created by 123 on 2017/12/4.
 */

public class UIApplition extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Logger
        Logger.addLogAdapter(new AndroidLogAdapter());
        Logger.i("onCreate::");
    }
}
