package com.kyleluoma.application.model;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;

@Entity
public class ItemQuestion {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private Integer itemId;
    private Integer sendingUserId;
    private String questionSubject;
    private String questionBody;
    private String responseBody;
    private Instant dateTimeAsked;
    private Instant dateTimeResponded;

    public Integer getId() {
        return id;
    }

    public Integer getItemId() {
        return sendingUserId;
    }
    
    public Integer getSendingUserId() {
        return sendingUserId;
    }
    
    public String getQuestionSubject() {
        return questionSubject;
    }
    
    public String getQuestionBody() {
        return questionBody;
    }
    
    public String getResponseBody() {
        return responseBody;
    }
    
    public Instant getDateTimeAsked() {
        return dateTimeAsked;
    }
    
    public Instant getDateTimeResponded() {
        return dateTimeResponded;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }
    
    public void setSendingUserId(Integer sendingUserId) {
        this.sendingUserId = sendingUserId;
    }
    
    public void setQuestionSubject(String questionSubject) {
        this.questionSubject = questionSubject;
    }
    
    public void setQuestionBody(String questionBody) {
        this.questionBody = questionBody;
    }
    
    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }
    
    public void setDateTimeAsked(Instant dateTimeAsked) {
        this.dateTimeAsked = dateTimeAsked;
    }
    
    public void setDateTimeResponded(Instant dateTimeResponded) {
        this.dateTimeResponded = dateTimeResponded;
    }
}
