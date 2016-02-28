/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intouch.db;

import com.intouch.hibernate.HibernateUtil;
import com.intouch.hibernate.User;
import java.util.List;
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

    private Transaction transaction;
    
    private DataHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
        transaction = sessionFactory.getCurrentSession().beginTransaction();
    }

    public static DataHelper getInstance() {
        return dataHelper == null ? new DataHelper() : dataHelper;
    }
    
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
    public List<User> getUserByLogin(String login){
        Session session = getSession();
        if(!transaction.isActive()){
            transaction = session.beginTransaction();
        }
        return session.createCriteria(User.class).add(Restrictions.eq("login", login)).list();
    }
    
    public void createNewUser(User user){
        Session session = getSession();
         if(!transaction.isActive()){
            transaction = session.beginTransaction();
        }
        session.save(user);
        transaction.commit();
    }
    
    public List<User> getUser(String login, String password){
        Session session = getSession();
        Criteria criteria = session.createCriteria(User.class);
        if(!transaction.isActive()){
            transaction = session.beginTransaction();
        }
        criteria.add(Restrictions.eq("login", login));
        criteria.add(Restrictions.eq("password", password));
        
        return criteria.list();
    }
    
}
