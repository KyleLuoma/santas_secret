package com.kyleluoma.application.model;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.kyleluoma.application.model.ItemPoolVisibility;

@Entity
public class GiftPool {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
 
    private String poolTitle;
    private String poolDescription;
    private List<DesiredItem> desiredItems;
    
    public Integer getId() {
        return id;
    }
    
    public String getPoolTitle() {
        return poolTitle;
    }
    
    public String getPoolDescription() {
        return poolDescription;
    }
    
    public String getDesiredItems() {
        return desiredItems;
    }
    
    public void setPoolTitle(String poolTitle) {
        this.poolTitle = poolTitle;
    }
    
    public void setPoolDescription(String poolDescription) {
        this.poolDescription = poolDescription;
    }
    
    public void setDesiredItems(List<DesiredItem> desiredItems) {
        this.desiredItems = desiredItems.clone();
    }
}
