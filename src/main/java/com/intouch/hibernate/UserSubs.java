package com.intouch.hibernate;
// Generated 19.04.2016 15:45:16 by Hibernate Tools 4.3.1



/**
 * UserSubs generated by hbm2java
 */
public class UserSubs  implements java.io.Serializable {


     private Long id;
     private long user;
     private long subscriber;

    public UserSubs() {
    }

    public UserSubs(long user, long subscriber) {
       this.user = user;
       this.subscriber = subscriber;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public long getUser() {
        return this.user;
    }
    
    public void setUser(long user) {
        this.user = user;
    }
    public long getSubscriber() {
        return this.subscriber;
    }
    
    public void setSubscriber(long subscriber) {
        this.subscriber = subscriber;
    }




}


