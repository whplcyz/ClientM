/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                  Copyright 2011-2015 Motorola Solutions Inc.                 |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------
 PRODUCT NAME:  iTM Client   
 CODED     BY:  tqfn38    2014.07.17

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
17/07/14   tqfn38                CCMPD01912783 Flexera license development  
04/01/15   tqfn38                CCMPD01956628 The refactor of radio license page.              
------------------------------------------------------------------------------*/
package com.mot.dm.client.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.mot.dm.client.dmImpl.control.Controller;
import com.mot.dm.common.model.FlexeraFeature;
import com.mot.dm.common.model.ItmModelType;
import com.mot.dm.common.model.SupportSoftware;
import com.mot.dm.common.service.FlexeraLicenseService;
import com.mot.dm.common.util.FlexeraFeatureConstants;
import com.mot.dm.common.util.LightFlexeraFeature;
import com.mot.dm.common.util.LightFlexeraFeature.FlexeraLicenseType;
import com.mot.dm.common.util.exception.ServiceException;
import com.mot.dm.common.util.log.impl.LoggerController;

public class ClientCacheUtil
{
    private static ClientCacheUtil instance = null;
    
    private Logger fgLogger = LoggerController.createLogger(ClientCacheUtil.class);
    
    private Object lockObject = new Object();
    
    private List<FlexeraFeature> flexeraFeatureList;
    private List<ItmModelType> itmModelTypeList;
    private List<SupportSoftware> supportSoftwareList = null;
    
    /*key is feature id*/
    private Map<String, LightFlexeraFeature> flexeraLicenseIDDefineMap = new HashMap<String, LightFlexeraFeature>();        
    /*key is featureID, value is FlexeraFeature object. This map is used as global variable*/
    private Map<String,FlexeraFeature> radioSellingfeatureID2FeatureMap = new HashMap<String, FlexeraFeature>();;    
    /*key is itm model type id.*/
    private Map<String, ItmModelType> itmModelTypeID2itmModelTypeMap = new HashMap<String, ItmModelType>();      
    private boolean hasEAPLicense = true;
    
    public static synchronized ClientCacheUtil Instance()
    {
        if(instance == null)
        {
            instance = new ClientCacheUtil();
            instance.loadClientCaheData();
        }
        return instance;
    }

    public FlexeraFeature getRadioSellingFeatureByFeatureName(String featureName)
    {
        synchronized(lockObject)
        {
            for(FlexeraFeature ff : radioSellingfeatureID2FeatureMap.values())
            {
                if(ff.getFeatureName().equalsIgnoreCase(featureName))
                {
                    return ff;
                }
            }
            
            return null;
        }
    }
    
    public FlexeraFeature getRadioSellingFeatureByFeatureID(String featureID)
    {
        synchronized(lockObject)
        {
            if(radioSellingfeatureID2FeatureMap.containsKey(featureID))
            {
                return radioSellingfeatureID2FeatureMap.get(featureID);
            }
            return null;
        }
    }
    
    public void loadClientCaheData()
    {
        synchronized (lockObject)
        {
            radioSellingfeatureID2FeatureMap.clear();
            itmModelTypeID2itmModelTypeMap.clear();

            try
            {
                FlexeraLicenseService flexeraLicenseService = Controller.getInstance().getFlexeraLicenseService();
                if (flexeraLicenseService != null)
                {
                    flexeraFeatureList = flexeraLicenseService.getAllFlexeraFeatures();
                    for (FlexeraFeature flexeraFeature : flexeraFeatureList)
                    {
                        radioSellingfeatureID2FeatureMap.put(flexeraFeature.getFeatureID(), flexeraFeature);
                    }
                    createFlexeraLicenseIDDefine(flexeraFeatureList);

                    itmModelTypeList = flexeraLicenseService.getITMModelTypes();
                    if (itmModelTypeList != null)
                    {
                        for (ItmModelType itmModelType : itmModelTypeList)
                        {
                            itmModelTypeID2itmModelTypeMap.put(itmModelType.getItmModelTypeId(), itmModelType);
                        }
                    }                                     
                }                

                supportSoftwareList = null;
            }
            catch (ServiceException e)
            {
                fgLogger.error("Load client cache data error:" + e.getMessage());
                return;
            }
        }
    }
    
    public ItmModelType getItmModelTypeByItmModelTypeID(String ItmModelTypeID)
    {
        synchronized(lockObject)
        {
            return itmModelTypeID2itmModelTypeMap.get(ItmModelTypeID);
        }
    }
    
    public String GetITMModelTypeName(String iTMModelTypeID)
    {
        synchronized (lockObject)
        {
            if (itmModelTypeID2itmModelTypeMap.containsKey(iTMModelTypeID))
            {
                return itmModelTypeID2itmModelTypeMap.get(iTMModelTypeID).getItmModelTypeName();
            }
            else
            {
                return null;
            }
        }
    }
    
    public void createFlexeraLicenseIDDefine(List<FlexeraFeature> flexeraFeatureList)
    {          
        flexeraLicenseIDDefineMap.clear();        
        
        LightFlexeraFeature mainFlexeraFeature = new LightFlexeraFeature(FlexeraFeatureConstants.iTMSYSTEMLICENSEID, FlexeraLicenseType.MAIN_LICENSE, FlexeraFeatureConstants.iTMSYSTEMLICENSEFEATURENAME);
        flexeraLicenseIDDefineMap.put(FlexeraFeatureConstants.iTMSYSTEMLICENSEID, mainFlexeraFeature);
        
     
        for (Entry<String, String> entry : FlexeraFeatureConstants.getSystemNoCapacityFeatureID2NameMap().entrySet())
        {
            LightFlexeraFeature systemFlexeraFeature = new LightFlexeraFeature(entry.getKey(), FlexeraLicenseType.SYSTEM_ON_OFF_LICENSE,
                    entry.getValue());
            flexeraLicenseIDDefineMap.put(entry.getKey(), systemFlexeraFeature);
        }

        for (Entry<String, String> entry : FlexeraFeatureConstants.getSystemCapacityFeatureID2NameMap().entrySet())
        {
            LightFlexeraFeature capacityFlexeraFeature = new LightFlexeraFeature(entry.getKey(),
                    FlexeraLicenseType.SYSTEM_CAPACITY_LICENSE, entry.getValue());
            flexeraLicenseIDDefineMap.put(entry.getKey(), capacityFlexeraFeature);
        }

        for (FlexeraFeature flexeraFeature : flexeraFeatureList)
        {
            LightFlexeraFeature siteWideFlexeraFeature = new LightFlexeraFeature(flexeraFeature.getSiteWideFeatureID(),
                    FlexeraLicenseType.RADIO_FEATURE_SITE_WIDE_LICENSE, flexeraFeature.getSiteWideFeatureName());
            flexeraLicenseIDDefineMap.put(flexeraFeature.getSiteWideFeatureID(), siteWideFlexeraFeature);

            LightFlexeraFeature radioLicenseFeature = new LightFlexeraFeature(flexeraFeature.getFeatureID(),
                    FlexeraLicenseType.RADIO_FEATURE_TEI_LOCKED_LICENSE, flexeraFeature.getFeatureName());
            flexeraLicenseIDDefineMap.put(flexeraFeature.getFeatureID(), radioLicenseFeature);
        }
    }
    
    public LightFlexeraFeature getLightFlexeraFeatureByFeatureID(String featureID)
    {
        synchronized(lockObject)
        {
            return flexeraLicenseIDDefineMap.get(featureID);
        }
    }

    public List<FlexeraFeature> getFlexeraFeatureList()
    {
        synchronized(lockObject)
        {
            List<FlexeraFeature> tempFlexeraFeatureList = new ArrayList<FlexeraFeature>();
            if(flexeraFeatureList != null)
            {
                tempFlexeraFeatureList.addAll(flexeraFeatureList);
            }
            return tempFlexeraFeatureList;
        }
    }

    public List<ItmModelType> getItmModelTypeList()
    {
        synchronized(lockObject)
        {
            List<ItmModelType> tempItmModelTypeList = new ArrayList<ItmModelType>();
            if(itmModelTypeList != null)
            {
                tempItmModelTypeList.addAll(itmModelTypeList);
            }
            return tempItmModelTypeList;
        }
    }
    
    public List<LightFlexeraFeature> getLightFlexeraFeatureList()
    {
        synchronized(lockObject)
        {
            List<LightFlexeraFeature> lightFlexeraFeatureList = new ArrayList<LightFlexeraFeature>();
            lightFlexeraFeatureList.addAll(flexeraLicenseIDDefineMap.values());
            
            Collections.sort(lightFlexeraFeatureList, new Comparator<LightFlexeraFeature>()
            {
                public int compare(LightFlexeraFeature arg0, LightFlexeraFeature arg1)
                {
                    return arg0.getFeatureID().compareTo(arg1.getFeatureID());
                }
            });
            return lightFlexeraFeatureList;
        }
    }

    public List<SupportSoftware> getSupportSoftwareList()
    {
    	synchronized(lockObject)
    	{
	    	if(supportSoftwareList == null)
	        {
	            try
	            {
	                supportSoftwareList = Controller.getInstance().getSoftwareService().getSupportSoftwares();
	            }
	            catch (ServiceException e)
	            {
	                fgLogger.error("Load software infoerror:" + e.getMessage());
	                e.printStackTrace();
	            }
	        }
	    	
	    	List<SupportSoftware> tempSupportSoftwareList = new ArrayList<SupportSoftware>();
	    	tempSupportSoftwareList.addAll(supportSoftwareList);
	    	return tempSupportSoftwareList;
    	}
    }

    /**
     * @return the hasEAPLicense
     */
    public boolean getHasEAPLicense()
    {
        return hasEAPLicense;
    }

    /**
     * @param hasEAPLicense the hasEAPLicense to set
     */
    public void setHasEAPLicense(boolean hasEAPLicense)
    {
        this.hasEAPLicense = hasEAPLicense;
    }
}
