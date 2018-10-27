package com.kyleluoma.application.model;

import javax.persistence.*;

import com.kyleluoma.application.model.ItemPoolVisibility;

import java.util.List;

@Entity
public class GiftPool {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
 
    private String poolTitle;
    private String poolDescription;

    @OneToMany
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
    
    public List<DesiredItem> getDesiredItems() {
        return desiredItems;
    }
    
    public void setPoolTitle(String poolTitle) {
        this.poolTitle = poolTitle;
    }
    
    public void setPoolDescription(String poolDescription) {
        this.poolDescription = poolDescription;
    }
    
    /*public void setDesiredItems(List<DesiredItem> desiredItems) {
        this.desiredItems = desiredItems;
    }*/
}
