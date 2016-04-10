package com.mot.dm.clientM.Base;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by qngv36 on 4/10/2016.
 */
public class BaseActivity extends Activity{
    protected void onCreate(Bundle savedInstance)
    {
        ActivityCollector.addActivity(this);
        super.onCreate(savedInstance);
    }

    protected void onDestroy()
    {
        ActivityCollector.removeActivity(this);
        super.onDestroy();
    }
}
