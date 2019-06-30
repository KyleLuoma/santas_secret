package com.kyleluoma.application.model;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class WishList {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer Id;
    
    private Integer userId;
    private String title;
    private String description;
    private LocalDateTime localDateTimeCreated;
    private LocalDateTime localDateTimeDisabled;

    public Integer getId() { return Id;}
    
    public Integer getUserId() {
        return userId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public LocalDateTime getLocalDateTimeCreated() {
	return localDateTimeCreated;
    }
    
    public LocalDateTime getLocalDateTimeDisabled() {
	return localDateTimeDisabled;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setLocalDateTimeCreated(LocalDateTime localDateTimeCreated) {
	this.localDateTimeCreated = localDateTimeCreated;
    }
    
    public void setLocalDateTimeDisabled(LocalDateTime localDateTimeDisabled) {
	this.localDateTimeDisabled = localDateTimeDisabled;
    }
    
}
