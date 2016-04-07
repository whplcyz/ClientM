/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                          Copyright 2012 Motorola Solutions Inc.                        |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM Client   
CODED     BY: Kalyan Pushpala/Jens Hansen 16-Sep-2009    

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
16/09/09 Jens Hansen             CCMPD01261809 Generalization for service 
                                               delegations.
19/July/12 tqfn38			   CCMPD01665910 proxy back compatibility                                                                                                                                         
------------------------------------------------------------------------------*/
package com.mot.dm.common.delegation;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.XmlResourceParser;
import android.net.Uri;
import android.util.Log;

import com.mot.dm.clientM.MyApplication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

@SuppressWarnings("deprecation")
public abstract class AbstractServiceDelegation implements IServiceDelegation
{    
    private XmlBeanFactory factory;

    public AbstractServiceDelegation(String service_delegation_file, Properties properties)
    {
        File serviceDelegationFile = new File(service_delegation_file);
        Context context = MyApplication.getContext();

        if(!serviceDelegationFile.exists())
        {
            BufferedReader reader = null;
            BufferedWriter writer = null;
            FileOutputStream out = null;
            try {
                out = context.openFileOutput(service_delegation_file, Context.MODE_PRIVATE);
                writer = new BufferedWriter(new OutputStreamWriter(out));
                reader = new BufferedReader(
                        new InputStreamReader(context.getAssets().open(service_delegation_file)));

                // do reading, usually loop until end of file reading
                String mLine;
                while ((mLine = reader.readLine()) != null) {
                    writer.write(mLine);
                    writer.newLine();
                }
            } catch (IOException e) {
                Log.e("", e.toString());
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                        writer.close();
                    } catch (IOException e) {
                        Log.e("", e.toString());
                    }
                }
            }
        }


        File file =  context.getFileStreamPath(service_delegation_file);
        String filePath = service_delegation_file;
        if(file.exists())
        {
            filePath = file.getAbsolutePath();
        }

        try {
            factory = new XmlBeanFactory(new ClassPathResource(filePath));
            properties.put("dmswebapp", "dms");

            PropertyPlaceholderConfigurer cfg = new CommonPropertyPlaceholderConfigurer(properties);
            cfg.postProcessBeanFactory(factory);
        }
        catch (Exception ex)
        {
            Log.e("", ex.toString());
        }
    }

    public Object getService(String servicename)
    {
        return factory.getBean(servicename);
    }
}
