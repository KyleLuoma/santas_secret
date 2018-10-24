package com.kyleluoma.application.model;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
* ItemPoolVisibility class is for associating DesiredItems with GiftPools. 
* Each instance of this class is a single associate between one item and 
* one pool. This facilitates the many-to-many relationship between pools
* and items. An item can appear in many different pools, and a pool will
* contain many different items.
**/
@Entity
public class ItemPoolVisibility {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer visId;
    
    private Integer itemId;
    private Integer poolId;
    private boolean visible;
    
    public Integer getVisId() {
        return visId;
    }
    
    public Integer getItemId() {
        return itemId;
    }
    
    public Integer getPoolId() {
        return poolId;
    }
    
    public boolean getVisible() {
        return visible;
    }
    
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }
    
    public void setPoolId(Integer poolId) {
        this.poolId = poolId;
    }
    
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
        
}
