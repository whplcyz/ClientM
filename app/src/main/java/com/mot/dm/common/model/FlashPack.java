/*
+------------------------------------------------------------------------------+
|                                                                              |
|               J A V A    C L A S S    S P E C I F I C A T I O N              |
|                                                                              |
|                 Copyright 2007-2011 Motorola Solutions Inc.                  |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM    
CODED     BY:   Hai Dong

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
05/Jan/09  Hai Dong              initial version  
16/Jun/09  Hai Dong              CCMPD01224853  Add hashcode implementation
24/Mar/11  Michael Leismann      CCMPD01483125 iTM 5.0: Code clean-up
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/*
 * Model object for flashpack
 */
public class FlashPack extends BaseObject
{
    private static final long serialVersionUID = 1L;

    /*
     * The uuid of the flashpack
     */
    private String flashPackUuid;

    /*
     * The codeplug uuid of the flashpack
     */
    private String codeplugUuid;

    /*
     * The key of the flashpack. for iTM20, it's a file path
     */
    private String flashPackKey;

    /*
     * Default constructor required by hibernate
     */
    public FlashPack()
    {
    }

    /*
     * Check if the obect o equals the FlashPack object
     * 
     * @see com.mot.dm.common.model.BaseObject#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o)
    {
        if (o instanceof FlashPack == false)
        {
            return false;
        }
        if (this == o)
        {
            return true;
        }
        FlashPack rhs = (FlashPack) o;
        return new EqualsBuilder().append(codeplugUuid, rhs.codeplugUuid)
                .append(flashPackKey, rhs.flashPackKey).isEquals();
    }

    /*
     * Return the hashcode of the object
     * 
     * @see com.mot.dm.common.model.BaseObject#hashCode()
     * 
     * @return int hashcode
     */
    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(flashPackUuid).append(codeplugUuid).hashCode();
    }

    @Override
    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).append(
                "codeplugUuid", this.codeplugUuid).append("flashPackKey", this.flashPackKey);
        return sb.toString();
    }

    /*
     * Get the codeplug uuid of the flashpack
     */
    public String getCodeplugUuid()
    {
        return codeplugUuid;
    }

    /*
     * Set the codeplug uuid for the flashpack
     */
    public void setCodeplugUuid(String codeplugUuid)
    {
        this.codeplugUuid = codeplugUuid;
    }

    /*
     * Get the FlashPack key
     * 
     * @return String
     */
    public String getFlashPackKey()
    {
        return flashPackKey;
    }

    /*
     * Set the FlashPack key
     */
    public void setFlashPackKey(String flashpackKey)
    {
        this.flashPackKey = flashpackKey;
    }

    /*
     * Get the flashpack uuid
     * 
     * @return String
     */
    public String getFlashPackUuid()
    {
        return flashPackUuid;
    }

    /*
     * Set the flashpack uuid
     * 
     * @param flashPackUuid String
     */
    public void setFlashPackUuid(String flashPackUuid)
    {
        this.flashPackUuid = flashPackUuid;
    }

}
