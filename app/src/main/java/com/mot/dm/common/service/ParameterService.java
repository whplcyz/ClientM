/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                       Copyright 2007-2014 Motorola Solutiosn Inc.            |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM
CODED     BY: ?

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
24/04/08   Jens Hansen           CCMPD01013422 added getParameterByName
17/June/14 tqfn38		 CCMPD01902641 Flexera license feature
------------------------------------------------------------------------------*/
package com.mot.dm.common.service;

import java.util.List;

import com.mot.dm.common.model.Parameter;
import com.mot.dm.common.util.exception.ServiceException;

public interface ParameterService extends Service
{

    /**
     * Get a parameter by its identifier.
     * 
     * @param paraName
     * @return Parameter
     * @throws ServiceException
     */
    public Parameter getParameter(String paraName) throws ServiceException;


    /**
     * To filter parameter by the setting condition.
     * 
     * @param parameter
     * @return
     * @throws ServiceException
     */
    public List<Parameter> getParameters(Parameter parameter) throws ServiceException;

    /**
     * Add a new parameter into DB.
     * 
     * @param Parameter
     * @return Parameter, this return parameter has auto generated a sequence
     *         increment identifier.
     * @throws ServiceException
     */
    public Parameter addOrModifyParameter(Parameter parameter) throws ServiceException;

    /**
     * Remove a parameter by the identifier.
     * 
     * @param paraName
     * @throws ServiceException
     */
    public void removeParameter(String paraName) throws ServiceException;

    /**
     * Get all of the exists parameters.
     * 
     * @return List<Parameter>
     * @throws ServiceException
     */
    public List<Parameter> getAllParameter() throws ServiceException;
}
