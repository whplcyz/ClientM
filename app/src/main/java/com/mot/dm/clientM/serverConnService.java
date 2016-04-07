package com.mot.dm.clientM;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by qngv36 on 4/4/2016.
 */
public class serverConnService extends Service {
    DownloadBinder downloadBinder = new DownloadBinder();

    @Override
    public IBinder onBind(Intent intent)
    {
        return  downloadBinder;
    }

    @Override
    public void onCreate()
    {

    }

    @Override
    public int onStartCommand(Intent intent, int flag, int  startId)
    {

        return super.onStartCommand(intent, flag, startId);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

    class DownloadBinder extends Binder
    {
        public void getDownloadStatus()
        {

        }

        public void start2Download()
        {

        }
    }

}
