package com.mot.dm.clientM;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Intent;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import java.security.Provider;

/**
 * Created by qngv36 on 4/3/2016.
 */
public class DeviceInfoProvider extends ContentProvider
{
    private static final int Device_Info = 0;
    private static final int Device_Info_Item = 1;
    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static
    {
        uriMatcher.addURI("com.example.qngv36.rmcmobile", "device_info", Device_Info);
        uriMatcher.addURI("com.example.qngv36.rmcmobile", "device_info/#", Device_Info_Item);
    }


    @Override
    public boolean onCreate()
    {
        return  false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sort)
    {
        int matchCode = uriMatcher.match(uri);
        switch (matchCode)
        {
            case Device_Info:
                Intent intent = new Intent();
                return  null;

            case Device_Info_Item:
                return null;
        }
        return  null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues)
    {
        return  null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs)
    {
        return  0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues,String selection, String[] selectionArgs)
    {
        return 0;
    }

    @Override
    public String getType(Uri uri)
    {
        return  null;
    }


}
