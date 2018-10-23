package com.kyleluoma.application.model;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class List {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer Id;
    
    public Integer getId() {
        return Id;
    }
}
