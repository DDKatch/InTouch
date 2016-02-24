package com.intouch.hibernate;
// Generated 23.02.2016 1:48:34 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Event generated by hbm2java
 */
public class Event  implements java.io.Serializable {


     private Long id;
     private String name;
     private String description;
     private byte[] gps;
     private long creatorId;
     private Date dateTime;
     private String adress;
     private Date createDate;

    public Event() {
    }

    public Event(String name, String description, byte[] gps, long creatorId, Date dateTime, String adress, Date createDate) {
       this.name = name;
       this.description = description;
       this.gps = gps;
       this.creatorId = creatorId;
       this.dateTime = dateTime;
       this.adress = adress;
       this.createDate = createDate;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public byte[] getGps() {
        return this.gps;
    }
    
    public void setGps(byte[] gps) {
        this.gps = gps;
    }
    public long getCreatorId() {
        return this.creatorId;
    }
    
    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }
    public Date getDateTime() {
        return this.dateTime;
    }
    
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
    public String getAdress() {
        return this.adress;
    }
    
    public void setAdress(String adress) {
        this.adress = adress;
    }
    public Date getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }




}


