/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intouch.db;

import com.intouch.hibernate.Event;
import com.intouch.hibernate.HibernateUtil;
import com.intouch.hibernate.User;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Владислав
 */
public class DataHelper {
    
    private SessionFactory sessionFactory = null;
    private static DataHelper dataHelper;
   
    private DataHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();             
    }

    public static DataHelper getInstance() {
       if(dataHelper==null){ 
           dataHelper = new DataHelper();
       }
       return dataHelper;
    }
    
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
    public User getUserByLogin(String login){
        Session session = getSession();
        User user = null;
        session.beginTransaction();        
        user = (User) session.createCriteria(User.class).add(Restrictions.eq("login", login)).uniqueResult();
        session.getTransaction().commit();
        return user;
    }
    
    public void createNewUser(User user){
        Session session = getSession();
        Transaction transaction1 = session.beginTransaction();
        session.save(user);
        transaction1.commit();
    }
    
    public User getUser(String login, String password){
        Session session = getSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("login", login));
        criteria.add(Restrictions.eq("password", password));
        User user = (User) criteria.uniqueResult();
        session.getTransaction().commit();
        return user;
    }
    
    public User getUserByToken(String token){
        Session session = getSession();
        session.beginTransaction();
        User user = (User) session.createCriteria(User.class).add(Restrictions.eq("token", token)).uniqueResult();
        session.getTransaction().commit();
        return user;
    }
            
    public Event getEventByUser(User user){
        Session session = getSession();
        session.beginTransaction();
        Event event = (Event) session.createCriteria(Event.class).add(Restrictions.eq("user", user)).uniqueResult();
        session.getTransaction().commit();
        return event;
    }
        
    public void createNewEvent(Event event){
        Session session = getSession();
        Transaction transaction1 = session.beginTransaction();
        session.save(event);
        transaction1.commit();
    }
    
    public Set getEvent(String token){
        Session session = getSession();
        session.beginTransaction();
        User user = (User) session.createCriteria(User.class).add(Restrictions.eq("token", token)).uniqueResult();
        session.getTransaction().commit();
        
        return user.getUserEvents();
    }
    
    public Set getUserEvents(String token){
        Session session = getSession();
        session.beginTransaction();
        User user = (User) session.createCriteria(User.class).add(Restrictions.eq("token", token)).uniqueResult();
        session.getTransaction().commit();
        
        return user.getUserEvents();
    }
    
    private <T> T cast(Object o, Class<T> clazz) {
        try {
            return clazz.cast(o);
        } catch(ClassCastException e) {
            System.out.println(e.toString());
            return null;
        }
    }
    
}


    
    
