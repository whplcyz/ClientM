package com.mot.dm.clientM;

import android.app.Application;
import android.content.Context;

/**
 * Created by qngv36 on 4/2/2016.
 */
public class MyApplication extends Application
{
    private String userName = "Superuser";
    private static Context context;
    public String GeUserName()
    {
        return  userName;
    }


    public void SetUserName(String userName)
    {
        this.userName = userName;
    }

    @Override
    public void onCreate()
    {
        context = getApplicationContext();
        super.onCreate();
    }


    public static Context getContext()
    {
        return context;
    }
}
