/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import enums.Feelings;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jitse
 */

@Embeddable public class ContextInfo {
    
    @Basic(optional = true)
    private String location;
    
    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Column(name = "DATETIME_FIELD")
    private Date dateTimeField;
    
    @Enumerated(EnumType.STRING)
    private Feelings feeling;

    
    
    public Date getDateTimeField() {
        return dateTimeField;
    }

    public void setDateTimeField(Date dateTimeField) {
        this.dateTimeField = dateTimeField;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Feelings getFeeling() {
        return feeling;
    }

    public void setFeeling(Feelings feeling) {
        this.feeling = feeling;
    }
    
    
    public ContextInfo(){
        dateTimeField = new Date();
    }
    
    
}
