package com.kyleluoma.application.model;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
* UserGiftPoolRelationship class is for associating Users with GiftPools. 
* Each instance of this class is a single associate between one user and 
* one pool. This facilitates the many-to-many relationship between users
* and pools. A user can be a member of many different pools, and a pool will
* contain many different users.
**/
@Entity
public class UserGiftPoolRelationship {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer relId;
    
    private Integer userId;
    private Integer giftPoolId;
    
    public Integer getRelId() {
        return relId;
    }
    
    public Integer getUserId() {
        return userId;
    }
    
    public Integer getGiftPoolId() {
        return giftPoolId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public void set giftPoolId(Integer giftPoolId) {
        this.giftPoolId = giftPoolId;
    }
}
