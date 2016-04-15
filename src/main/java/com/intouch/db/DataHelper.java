/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intouch.db;

import com.intouch.hibernate.Event;
import com.intouch.hibernate.EventType;
import com.intouch.hibernate.HibernateUtil;
import com.intouch.hibernate.User;
import com.intouch.hibernate.UserSubs;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;

import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import org.hibernate.transform.Transformers;

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
    
    public Session getSession() {
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
        criteria.add(Restrictions.eq("login", login))
        .add(Restrictions.eq("password", password));
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
    
    public List<Event> getEvent(String token){
        Session session = getSession();
        session.beginTransaction();
        Long userId = (Long)session.createCriteria(User.class).add(Restrictions.eq("token", token)).setProjection(Projections.projectionList().add(Projections.property("id"), "id")).uniqueResult();
        List<Event> events = session.createCriteria(Event.class).add(Restrictions.eq("creator_id", userId)).list();
        session.getTransaction().commit();
        
        return events;
    }
    
    public void submitUserChanges(UserSubs userSubs){
        Session session = getSession();
        session.beginTransaction();
        session.save(userSubs);
        session.getTransaction().commit();
    }
    
    public Event getEventById(Long id){
        Session session = getSession();
        session.beginTransaction();
        Event event = (Event) session.createCriteria(Event.class).add(Restrictions.eq("id", id)).uniqueResult();
        session.getTransaction().commit();
        return event;
    }
    
    public List<EventType> getEventTypes(){
        Session session = getSession();
        session.beginTransaction();
        List<EventType> list = session.createCriteria(EventType.class).setProjection(Projections.projectionList().add(Projections.property("typeName"), "typeName").add(Projections.property("id"), "id")).setResultTransformer(Transformers.aliasToBean(EventType.class)).list();
        session.getTransaction().commit();
        return list;
    }
    
    public List<Event> searchEvents(Map<String, Object> params){
        Session session = getSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Event.class);
        for(Entry<String, Object> entry: params.entrySet()){
            if(entry.getKey().equals("name")){
                criteria.add(Restrictions.ilike(entry.getKey(), entry.getValue().toString(), MatchMode.ANYWHERE));
            }
            else{
                
                criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
            }
        }
        List<Event> list = criteria.list();
        session.getTransaction().commit();
        return list;
    }
    
    
}


    
    
