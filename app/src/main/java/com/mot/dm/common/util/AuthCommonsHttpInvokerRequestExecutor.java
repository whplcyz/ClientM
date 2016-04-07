/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                       Copyright 2007-2012 Motorola Solutions Inc.                      |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM Client   
CODED     BY:  Jens Hansen    2009.06.30

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
30/06/09   Jens Hansen           CCMPD01237099 Initial Version
26/08/09   Kalyan Pushpala       CCMPD01237099 override validate response to
											   raise custom exception in case of 
											   unauthorized HTTP status code.
12/10/09   Kalyan Pushpala       CCMPD01274569 Encoding the credentials by default
                                               using UTF-8 and provided option for 
                                               configuring the same during bean 
                                               definition
11/Feb/10  hgkj73                CCMPD01310676 Upgrade Spring3.0,SpringSec3.0.1
11/Oct/10  cjh102                CCMPD01399192 Adding additional information in
                                               request.
08/Dec/10  ckfm01                CCMPD01448983 Adding MacAddress in session request.
11/Jan/11  cjh102                CCMPD01461502 Changes to common classes.
19/July/12 tqfn38			   CCMPD01665910 proxy back compatibility 
------------------------------------------------------------------------------*/

package com.mot.dm.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.springframework.remoting.httpinvoker.CommonsHttpInvokerRequestExecutor;
import org.springframework.remoting.httpinvoker.HttpInvokerClientConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;

import com.mot.dm.common.util.exception.AuthenticationRequiredException;

public abstract class AuthCommonsHttpInvokerRequestExecutor extends
        CommonsHttpInvokerRequestExecutor
{
    private String credentialsCharset = "UTF-8"; // By default. Same as server

    private static Logger logger = Logger.getLogger("com.mot.dm");

    public static final String MAC_ADDRESS_HEADER_KEY = "MacAddress";
    public static final String CLIENT_ID_HEADER_KEY = "ClientID";

    public void setCredentialsCharset(String credentialsCharset)
    {
        Assert.hasText(credentialsCharset, "credentialsCharset cannot be null or empty");
        this.credentialsCharset = credentialsCharset;
    }

    protected String getCredentialsCharset()
    {
        return credentialsCharset;
    }

    /**
     * Basic Authentication support for HttpRequest - Uses Credentials stored in
     * Global Security Context - Uses Configured Char set for credentials
     * encoding
     * 
     * @param HttpInvokerClientConfiguration
     * @return PostMethod
     * @throws IOException
     */
    protected PostMethod createPostMethod(HttpInvokerClientConfiguration config) throws IOException
    {
        PostMethod postMethod = super.createPostMethod(config);

        setAuthorizationRequestHeader(postMethod);
        addRequestHeaderInfo(postMethod);

        super.getHttpClient().getParams().setVirtualHost("iTM" + VersionNumber.INTERFACE_VERSION);

        return postMethod;
    }

    /**
     * Returns the request header. Can be overridden in inheriting classes. If
     * not data is available to be used in the header, null is returned
     * 
     * @return
     * @throws UnsupportedEncodingException
     */
    protected void setAuthorizationRequestHeader(PostMethod postMethod) throws UnsupportedEncodingException
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null) && (auth.getName() != null) && (auth.getCredentials() != null))
        {
            String base64 = auth.getName() + ":" + auth.getCredentials().toString();

            String authRequestHeader =  "Basic " + new String(Base64.encodeBase64(base64.getBytes(credentialsCharset)));
            postMethod.setRequestHeader("Authorization", authRequestHeader);
        }
    }

    /**
     * Used to filter SC_UNAUTHORIZED error code and throw a application
     * specific exception
     * 
     * @param HttpInvokerClientConfiguration
     * @param PostMethod
     * @throws IOException
     */
    protected void validateResponse(HttpInvokerClientConfiguration config, PostMethod postMethod)
            throws IOException
    {
        if (HttpServletResponse.SC_UNAUTHORIZED == postMethod.getStatusCode())
        {
            logger.debug("Throw new AuthenticationRequiredException exception");
            String responseMsg = postMethod.getResponseBodyAsString();
            String errorMessage = CommonConstants.Authenticate_AuthenticationError;
            if (responseMsg.contains(CommonConstants.AuthenticateToLDAP_NetworkError))
            {
                errorMessage = CommonConstants.AuthenticateToLDAP_NetworkError;
            }
            else if (responseMsg.contains(CommonConstants.AuthenticateToLDAP_UnknownError))
            {
                errorMessage = CommonConstants.AuthenticateToLDAP_UnknownError;
            }
            throw new AuthenticationRequiredException(
                    "SessionDetailsChange.ReauthenticationRequiredMsg", new RuntimeException(
                            errorMessage));
        }
        else
        {
            super.validateResponse(config, postMethod);
        }
    }

    /**
     * Adding information about the client attempting to connect
     * 
     * @param postMethod
     */
    protected void addRequestHeaderInfo(PostMethod postMethod)
    {
        postMethod.addRequestHeader(MAC_ADDRESS_HEADER_KEY, OsUtils.getMacAddressString());
        postMethod.addRequestHeader(CLIENT_ID_HEADER_KEY, getClientID());
    }

    /**
     * All classes extending this must implement this method to return the
     * appropriate ID as specified in the VersionNumber class
     * 
     * @return
     */
    protected abstract String getClientID();
}
