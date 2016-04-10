package com.mot.dm.clientM.Base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by qngv36 on 4/10/2016.
 */
public class BaseActivity extends AppCompatActivity {
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
