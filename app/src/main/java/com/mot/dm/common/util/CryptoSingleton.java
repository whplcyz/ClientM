/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                  Copyright 2007-2014 Motorola Solutions Inc.                 |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  <Name>
CODED     BY:  <Name>    <Date>

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
28 Apr 2008 Sigurdur Jonsson                   Created, taken from Backup Restore
                                               Collector application. Used for
                                               encryption/decryption of text
30 Jun 2008 Sigurdur Jonsson     CCMPD01055522 Proxy without password feature
11/Jul/08  Sigurdur Jonsson      CCMPD01055522 FTR issues solved.
19/Aug/08  Sigurdur Jonsson      CCMPD01082191 Can not log in locally.
16/Nov/09  Michael Leismann      CCMPD01287031 Clean up
28/Jan/10  Michael Leismann      CCMPD01305770 Fixed issue with return code
01/Feb/10  Michael Leismann      CCMPD01308679 Fixed issue with unicode password
01/July/13 qngv36    CCMPD01785817 Solve the clash between Cipher.init() and Cipher.doFinal() methods.
                                               It will cause the failture of the decrypt and encrypt.
------------------------------------------------------------------------------*/
package com.mot.dm.common.util;

//import javax.crypto.*;
//import javax.crypto.spec.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;
public final class CryptoSingleton
{
    private static CryptoSingleton c;

    private static String alg = "DES";
    private static Key key = null;
    private static Cipher cipher = null;
    private static byte[] desKeyData =
    { (byte) 0x36, (byte) 0x24, (byte) 0x36, (byte) 0x83, (byte) 0x62, (byte) 0x43, (byte) 0x68,
            (byte) 0x36 };

    private static final int ITERATION_COUNT = 19;

    private static Logger fgLogger = Logger.getLogger(CryptoSingleton.class);
    private static final String c_classname = "package com.mot.dm.common.util.CryptoSingleton";

    /**
     * Method to return an instance of this class.
     * 
     * @return instance of 'CryptoSingleton'
     */
    public synchronized static CryptoSingleton getInstance()
    {
        if (c == null)
        {
            c = new CryptoSingleton();
        }

        if ((key == null) || (cipher == null))
        {
            init();
        }

        return c;
    }

    /**
     * Class constructor to create a 'CryptoSingleton' object.
     */
    private CryptoSingleton()
    {
    }

    /**
     * Method to initialize the 'CryptoSingleton' object.
     * 
     * This method will generate a key and initialize the 'Cipher' object
     * appropriately.
     */
    private static synchronized void init()
    {
        try
        {
            key = new SecretKeySpec(desKeyData, "DES");
            cipher = Cipher.getInstance(alg);
        }
        catch (Exception e)
        {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            printError(c_classname,
                    "unable to initialize, " + "Stack trace: " + sw.toString());
        }
    }

    /**
     * Method to encrypt a given string.
     * 
     * @param in
     *            'String' instance containing the plaintext
     * @return array of bytes containing the ciphertext
     */
    public synchronized byte[] encrypt(String in)
    {
        return encrypt(in, null);
    }
    
    /**
     * Method to encrypt a given string.
     * 
     * @param in
     *            'String' instance containing the plaintext
     * @param  charsetName
     *         The name of a supported {@linkplain java.nio.charset.Charset
     *         charset}
     * @return array of bytes containing the ciphertext
     */
    public synchronized byte[] encrypt(String in, String charsetName)
    {
        byte[] ret = null;
        try
        {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] input;
            if(charsetName == null || charsetName.isEmpty())
            {
                input = in.getBytes();
            }
            else
            {
                input = in.getBytes(charsetName);
            }
            ret = cipher.doFinal(input);
        }
        catch (Exception e)
        {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            printError(c_classname, "Login failed, " + "Stack trace: " + sw.toString());
        }
        return ret;
    }

    /**
     * Method to decrypt data.
     * 
     * @param in
     *            array of bytes containing the ciphertext
     * @return 'String' instance containing the plaintext
     */
    public synchronized String decrypt(byte[] in)
    {
        return decrypt(in,null);
    }
    
    /**
     * Method to decrypt data.
     * 
     * @param in
     *            array of bytes containing the ciphertext
     * @param  charsetName
     *         The name of a supported {@linkplain java.nio.charset.Charset
     *         charset}
     * @return 'String' instance containing the plaintext
     */
    public synchronized String decrypt(byte[] in, String charsetName)
    {
        String ret = null;
        try
        {
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] plainTxt = cipher.doFinal(in);
            
            if(charsetName == null || charsetName.isEmpty())
            {
                ret = new String(plainTxt);
            }
            else
            {
                ret = new String(plainTxt, charsetName);
            }
        }
        catch (Exception e)
        {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            printError(c_classname, "Login failed, " + "Stack trace: " + sw.toString());
        }
        
        if(ret == null)
        {
            ret = "";
        }
        return ret;
    }

    /**
     * Encrypt a file
     * 
     * @param p_inFileName
     * @param p_outFilename
     * @param p_passPhrase
     * @return true if success, false for failure
     */
    public synchronized boolean encrypt(String p_inFileName, String p_outFilename, String p_passPhrase)
    {
        // Buffer used to transport the bytes from one stream to another
        byte[] buf = new byte[1024];

        InputStream in = null;
        CipherOutputStream cipherOut = null;
        try
        {
            // Create key
            SecretKey secretKey = createKey(p_passPhrase);
            if (secretKey == null)
            {
                return false;
            }

            // Prepare the parameter to the ciphers
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(desKeyData, ITERATION_COUNT);

            in = new FileInputStream(p_inFileName);

            // Create the ciphers
            Cipher ecipher = Cipher.getInstance(secretKey.getAlgorithm());
            ecipher.init(Cipher.ENCRYPT_MODE, secretKey, paramSpec);
            // Klocwork finding should be ignored
            cipherOut = new CipherOutputStream(new FileOutputStream(p_outFilename), ecipher);

            // Read in the decrypted bytes and write the encrypted bytes
            int numRead;
            while ((numRead = in.read(buf)) >= 0)
            {
                cipherOut.write(buf, 0, numRead);
            }
            cipherOut.flush();
            cipherOut.close();
            in.close();
        }
        catch (Exception e)
        {
            printInfo(c_classname, "Encryption failed: " + e.getMessage());
            return false;
        }

        // Close all files and streams
        finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                }
            }
            catch (Exception e)
            {
            }
            try
            {
                if (cipherOut != null)
                {
                    cipherOut.close();
                }
            }
            catch (Exception e)
            {
            }
        }

        return true;
    }

    /**
     * Decrypts a file
     * 
     * @param p_inFileName
     * @param p_outFilename
     * @param p_passPhrase
     * @return true if success, false for failure
     */
    public synchronized boolean decrypt(String p_inFileName, String p_outFilename, String p_passPhrase)
    {
        // Buffer used to transport the bytes from one stream to another
        byte[] buf = new byte[1024];

        OutputStream out = null;
        CipherInputStream cipherIn = null;
        try
        {
            // Create key
            SecretKey secretKey = createKey(p_passPhrase);
            if (secretKey == null)
            {
                return false;
            }

            // Prepare the parameter to the ciphers
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(desKeyData, ITERATION_COUNT);

            out = new FileOutputStream(p_outFilename);

            // Create the ciphers
            Cipher dcipher = Cipher.getInstance(secretKey.getAlgorithm());
            dcipher.init(Cipher.DECRYPT_MODE, secretKey, paramSpec);
            // Klocwork finding should be ignored
            cipherIn = new CipherInputStream(new FileInputStream(p_inFileName), dcipher);

            // Read in the encrypted bytes and write the decrypted bytes
            int numRead;
            while ((numRead = cipherIn.read(buf)) >= 0)
            {
                out.write(buf, 0, numRead);
            }
            out.flush();
            out.close();
            cipherIn.close();
        }
        catch (Exception e)
        {
            printInfo(c_classname, "Decryption failed: " + e.getMessage());
            return false;
        }

        // Close all files and streams
        finally
        {
            try
            {
                if (out != null)
                {
                    out.close();
                }
            }
            catch (Exception e)
            {
            }
            try
            {
                if (cipherIn != null)
                {
                    cipherIn.close();
                }
            }
            catch (Exception e)
            {
            }
        }

        return true;
    }

    /**
     * Creates the key based on the pass phrase Common for encrypt and decrypt
     * 
     * @param p_passPhrase
     * @return the key, or null if creation fails
     */
    private SecretKey createKey(String p_passPhrase)
    {
        try
        {
            // As the key created below only can handle ASCII chars
            // then the following is done:
            // 1. Convert each char to the unicode value; e.g. "1" is "0031"
            // 2. Append these unicode strings; e.g. "becomes "003100A9"
            // 3. Use this appended string when the key is created as it only
            // contains ASCII chars (0-9 A-F)
            StringBuffer passPhraseWithValues = new StringBuffer();
            for (int i = 0; i < p_passPhrase.length(); i++)
            {
                // Convert the int to a hex value
                int unicodeVal = p_passPhrase.charAt(i);
                String hexValue = Integer.toHexString(unicodeVal).toUpperCase();

                // Unicode has this format: \u0000
                // where the 0000 is replaced with the hex value,
                // but it must be a four number hex value with leading zeros
                String hexValueWithZeros = "0000" + hexValue;
                hexValueWithZeros = hexValueWithZeros.substring(hexValueWithZeros.length() - 4);

                // Append these hex values
                passPhraseWithValues.append(hexValueWithZeros);
            }

            // Create the key
            KeySpec keySpec = new PBEKeySpec(passPhraseWithValues.toString().toCharArray(),
                    desKeyData, ITERATION_COUNT);
            SecretKey secretKey = SecretKeyFactory.getInstance("PBEWithMD5AndDES")
                    .generateSecret(keySpec);

            return secretKey;
        }
        catch (Exception e)
        {
            printInfo(c_classname, "Create key exception: " + e.getMessage());
            return null;
        }
    }

    private static void printError(String module, String message)
    {
        fgLogger.error(" " + module + "- " + message);
    }
    
    private static void printInfo(String module, String message)
    {
        fgLogger.info("  " + module + "- " + message);
    }
}
