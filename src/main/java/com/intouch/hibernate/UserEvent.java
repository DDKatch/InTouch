package com.intouch.hibernate;
// Generated 24.03.2016 21:30:48 by Hibernate Tools 4.3.1



/**
 * UserEvent generated by hbm2java
 */
public class UserEvent  implements java.io.Serializable {


     private UserEventId id;
     private Event event;
     private User user;

    public UserEvent() {
    }

    public UserEvent(UserEventId id, Event event, User user) {
       this.id = id;
       this.event = event;
       this.user = user;
    }
   
    public UserEventId getId() {
        return this.id;
    }
    
    public void setId(UserEventId id) {
        this.id = id;
    }
    public Event getEvent() {
        return this.event;
    }
    
    public void setEvent(Event event) {
        this.event = event;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

}


