/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intouch.db;

import com.intouch.exceptions.ServerQueryException;
import com.intouch.hibernate.Event;
import com.intouch.hibernate.EventType;
import com.intouch.hibernate.HibernateUtil;
import com.intouch.hibernate.User;
import com.intouch.hibernate.UserEvent;
import com.intouch.hibernate.UserSubs;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
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
    
    public List<Event> getEvent(String token, long user_id) throws ServerQueryException{
        Session session = getSession();
        session.beginTransaction();
        Long userId = (Long)session.createCriteria(User.class).add(Restrictions.eq("token", token)).setProjection(Projections.projectionList().add(Projections.property("id"), "id")).uniqueResult();
        if(userId==null){
            throw new ServerQueryException("User with token "+token+" does not exist");
        }
        List<Event> events = session.createCriteria(Event.class).add(Restrictions.eq("creatorId", user_id)).list();
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
    
    public User getEventCreator(long eventId) throws ServerQueryException{
        Session session = getSession();
        session.beginTransaction();
        Long creatorId = (Long)session.createCriteria(Event.class).add(Restrictions.eq("id", eventId)).setProjection(Projections.property("creator_id")).uniqueResult();
        if(creatorId==null){
            throw new ServerQueryException("Event with id "+eventId+" not found.");
        }
        User user = (User)session.createCriteria(User.class).add(Restrictions.eq("id", creatorId)).setProjection(
                Projections.projectionList().add(Projections.property("id")).add(Projections.property("login")).
                        add(Projections.property("first_name")).add(Projections.property("last_name")).
                        add(Projections.property("last_visit")).
                        add(Projections.property("registration_date"))
      ).setResultTransformer(Transformers.aliasToBean(User.class)).uniqueResult();
        session.getTransaction().commit();
        return user;
        
    }
   
    public void addEventFollower(UserEvent userEvent){
        Session session = getSession();
        session.beginTransaction();
        session.save(userEvent);
        session.getTransaction().commit();
    }
    
    public boolean isUserFollowed(Long userId, Long eventId){
        Session session = getSession();
        session.beginTransaction();
        UserEvent userEvent = (UserEvent)session.createCriteria(UserEvent.class).add(Restrictions.eq("user_id", userId)).add(Restrictions.eq("event_id", eventId)).uniqueResult();
        if(userEvent==null){
            session.getTransaction().commit();
            return false;
        }
        session.getTransaction().commit();
        return true;
    }
    
    public List<User> getEventFollowers(String token, long event_id) throws ServerQueryException{
        Session session = getSession();
        session.beginTransaction();
        Long userId = (Long)session.createCriteria(User.class).add(Restrictions.eq("token", token)).setProjection(Projections.property("id")).uniqueResult();
        if(userId==null){
             throw new ServerQueryException("User with token "+token+" does not exist");
        }
        
        List<UserEvent> userEventList = session.createCriteria(UserEvent.class).add(Restrictions.eq("eventId", event_id)).list();
        
        Criterion criterion = null;
        
        Criterion[] criterions = new Criterion[userEventList.size()];
        
        for(int i=0; i<userEventList.size(); i++){
            criterions[i] = Restrictions.eq("id", userEventList.get(i).getUserId());
        }
        
        criterion = Restrictions.or(criterions);
        
        List<User> users = session.createCriteria(User.class).add(criterion).setProjection(Projections.projectionList()
                .add(Projections.property("id"), "id").add(Projections.property("login"), "login").add(Projections.property("firstName"), "firstName").
                add(Projections.property("lastName"), "lastName").add(Projections.property("registrationDate"), "registrationDate")
                .add(Projections.property("lastVisit"), "lastVisit")
             ).setResultTransformer(Transformers.aliasToBean(User.class)).list();
        
        session.getTransaction().commit();
        return users;
    }
    
    public String getDeviceId(long id){
        Session session = getSession();
        session.beginTransaction();
        String deviceId = (String)session.createCriteria(User.class).add(Restrictions.eq("id", id)).setProjection(Projections.property("deviceId")).uniqueResult();
        session.getTransaction().commit();
        return deviceId;
    }
    
    public List<String> getMyFollowersTokens(String token) throws ServerQueryException{
        Session session = getSession();
        session.beginTransaction();
        Long userId = (Long)session.createCriteria(User.class).add(Restrictions.eq("token", token)).setProjection(Projections.property("id")).uniqueResult();
        
        if(userId==null){
            throw new ServerQueryException("User with token "+ token+" does not exist");
        }
        
        List<UserSubs> userSubsList = session.createCriteria(UserSubs.class).add(Restrictions.eq("user", userId)).list();
        
        Criterion criterion = null;
        
        Criterion[] criterions = new Criterion[userSubsList.size()];
        
        for(int i=0; i<userSubsList.size(); i++){
            criterions[i] = Restrictions.eq("id", userSubsList.get(i).getUser());
        }
        
        criterion = Restrictions.or(criterions);
        
        List<String> tokens = session.createCriteria(User.class).add(criterion).setProjection(Projections.property("deviceId")).list();
        
        session.getTransaction().commit();
        return tokens;
    }
    
    public void updateLastVisitTime(String token){
        Session session = getSession();
        session.beginTransaction();

        String hql = "update User set last_visit = :last_visit " + 
        "where token = :token";
        Query query = session.createQuery(hql);
        query.setParameter("last_visit", new Date());
        query.setParameter("token", token);
        int result = query.executeUpdate();
        System.out.println("Rows affected: " + result); 

        session.getTransaction().commit(); 
    }
    
}


    
    
