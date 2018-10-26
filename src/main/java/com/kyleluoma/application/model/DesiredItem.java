package com.kyleluoma.application.model;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DesiredItem {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    private Integer wishListID;
    private String title;
    private String description;
    private String URL;
    private Boolean purchased;
    private Integer purchasedByUserId;
    private Enum priority;
    
    public Integer getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getURL() {
        return URL;
    }
    
    public Boolean getPurchase() {
        return purchased;
    }
    
    public Integer getPurchasedByUserId() {
        return purchasedByUserId;
    }
    
    public Enum getPriority() {
        return priority;
    }
    
    public void setWishListID(Integer wishListID) {
        this.wishListID = wishListID;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setURL(String URL) {
        this.URL = URL;
    }
    
    public void setPurchased(Boolean purchased) {
        this.purchased = purchased;
    }
    
    public void setPriority(Enum priority) {
        this.priority = priority;
    }
}
