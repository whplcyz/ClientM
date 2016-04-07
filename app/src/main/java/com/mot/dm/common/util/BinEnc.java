/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                       Copyright 2007-2008 Motorola Inc.                      |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  <Name>
CODED     BY:  <Name>    <Date>

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
28 Apr 2008	Sigurdur Jonsson                   Created, taken from Backup Restore
                                               Collector application. Used for
                                               encryption/decryption of text
------------------------------------------------------------------------------*/
package com.mot.dm.common.util;

/**
 * This class is responsible for providing a simple binary to ASCII and ASCII to
 * binary encoding/decoding facility.
 * 
 * The encoding process will take an array of bytes and simply convert each
 * hexadecimal value into its ASCII equivalent.
 * 
 * example: input = 0x32, 0x45, 0x67 ouput = "434567"
 */

public final class BinEnc
{

    /**
     * Default class constructor
     */

    private BinEnc()
    {
    }

    /**
     * This method will take an array of bytes and will do a simple encoding in
     * ASCII characters.
     * 
     * @param in
     *            array of bytes containing the binary data to be encoded
     * @return 'String' the ASCII-encoded binary data
     */

    public static String encode(byte[] in)
    {
    	if(null == in)
    	{
    		return null;
    	}
        
    	// for each byte in the provided array, convert to the
        // corresponding ASCII character representation and
        // append to the 'StringBuffer'

        StringBuffer ret = new StringBuffer();
        for (int ii = 0; ii < in.length; ++ii)
        {

            // split each byte into a most significant half
            // and a least significant part

            byte msh = (byte) (in[ii] >> 4);
            msh = (byte) (msh & 0x0F);
            byte lsh = (byte) (in[ii] & 0x0F);

            ret.append(byteToChar(msh));
            ret.append(byteToChar(lsh));
        }

        return ret.toString();
    }

    /**
     * This method will take a 'String' instance representing an ASCII encoding
     * of some arbitrary binary data and will convert it back to an array of
     * 'byte'.
     * 
     * @param in
     *            'String' instance representing the ASCII encoded binary data
     * @return array of bytes containing the decoded data
     */

    public static byte[] decode(String in)
    {

        // if the length of the string isn't an even number,
        // do not attempt a decode
        if (null == in || (in.length() % 2) != 0)
        {
            return null;
        }

        // allocate a byte array to hold the return value based on
        // the length of the input

        byte[] ret = new byte[in.length() / 2];
        for (int ii = 0, idx = 0; ii < in.length(); ++ii)
        {
            // get the most significant part of the byte
            char c = in.charAt(ii);
            byte msh = (byte) (charToByte(c) << 4);

            // get the least significant part of the byte
            c = in.charAt(++ii);
            byte lsh = charToByte(c);

            // combine the two halves
            ret[idx++] = (byte) (msh | lsh);
        }

        return ret;
    }

    /**
     * Method to convert a character to a byte value.
     * 
     * This method will take a numerical character ('0' .. '9') and will return
     * the corresponding value as a byte.
     * 
     * @param in
     *            character to be decoded
     * @return byte value of the character
     */

    private static byte charToByte(char in)
    {
        byte ret = (byte) 0;

        switch (in)
        {
        case '0':
            ret = (byte) 0;
            break;
        case '1':
            ret = (byte) 1;
            break;
        case '2':
            ret = (byte) 2;
            break;
        case '3':
            ret = (byte) 3;
            break;
        case '4':
            ret = (byte) 4;
            break;
        case '5':
            ret = (byte) 5;
            break;
        case '6':
            ret = (byte) 6;
            break;
        case '7':
            ret = (byte) 7;
            break;
        case '8':
            ret = (byte) 8;
            break;
        case '9':
            ret = (byte) 9;
            break;
        case 'A':
        case 'a':
            ret = (byte) 10;
            break;
        case 'B':
        case 'b':
            ret = (byte) 11;
            break;
        case 'C':
        case 'c':
            ret = (byte) 12;
            break;
        case 'D':
        case 'd':
            ret = (byte) 13;
            break;
        case 'E':
        case 'e':
            ret = (byte) 14;
            break;
        case 'F':
        case 'f':
            ret = (byte) 15;
            break;
        default:
        	break;
        }

        return ret;
    }

    /**
     * Method to convert a byte value to a character value.
     * 
     * This method will take a byte value (0x00 - 0x0F) and will return the
     * corresponding value as a character.
     * 
     * @param in
     *            byte to be decoded
     * @return character corresponding to the byte
     */

    private static char byteToChar(byte in)
    {
        char ret = '0';

        switch (in)
        {
        case 0:
            ret = '0';
            break;
        case 1:
            ret = '1';
            break;
        case 2:
            ret = '2';
            break;
        case 3:
            ret = '3';
            break;
        case 4:
            ret = '4';
            break;
        case 5:
            ret = '5';
            break;
        case 6:
            ret = '6';
            break;
        case 7:
            ret = '7';
            break;
        case 8:
            ret = '8';
            break;
        case 9:
            ret = '9';
            break;
        case 10:
            ret = 'A';
            break;
        case 11:
            ret = 'B';
            break;
        case 12:
            ret = 'C';
            break;
        case 13:
            ret = 'D';
            break;
        case 14:
            ret = 'E';
            break;
        case 15:
            ret = 'F';
            break;
        default:
        	break;
        }

        return ret;
    }
}
