package com.intouch.hibernate;
// Generated 15.04.2016 18:58:37 by Hibernate Tools 4.3.1



/**
 * EventType generated by hbm2java
 */
public class EventType  implements java.io.Serializable {


     private Integer id;
     private String typeName;

    public EventType() {
    }

    public EventType(String typeName) {
       this.typeName = typeName;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTypeName() {
        return this.typeName;
    }
    
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }




}


