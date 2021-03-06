package com.intouch.hibernate;
// Generated 24.03.2016 21:30:48 by Hibernate Tools 4.3.1



/**
 * UserSubsId generated by hbm2java
 */
public class UserSubsId  implements java.io.Serializable {


     private long user;
     private long subscriber;

    public UserSubsId() {
    }

    public UserSubsId(long user, long subscriber) {
       this.user = user;
       this.subscriber = subscriber;
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


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof UserSubsId) ) return false;
		 UserSubsId castOther = ( UserSubsId ) other; 
         
		 return (this.getUser()==castOther.getUser())
 && (this.getSubscriber()==castOther.getSubscriber());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getUser();
         result = 37 * result + (int) this.getSubscriber();
         return result;
   }   


}


