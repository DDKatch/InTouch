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
import org.hibernate.HibernateException;
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

    private static Transaction transaction;
    
    private DataHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
        if(transaction==null||!transaction.isActive())           
            transaction = getSession().beginTransaction();              
    }

    public static DataHelper getInstance() {
        return dataHelper == null ? new DataHelper() : dataHelper;
    }
    
    private Session getSession() {
        return sessionFactory.openSession();
    }
    
    public List<User> getUserByLogin(String login){
        Session session = getSession();
        List<User> users = null;
        session.beginTransaction();
        try{
            users = session.createCriteria(User.class).add(Restrictions.eq("login", login)).list();
        }
        catch(HibernateException ex){
            session.beginTransaction();
            users = session.createCriteria(User.class).add(Restrictions.eq("login", login)).list();
        }
        session.close();
        return users;
    }
    
    public void createNewUser(User user){
        Session session = getSession();
        Transaction transaction1 = session.beginTransaction();
        session.save(user);
        transaction1.commit();
        session.close();
    }
    
    public List<User> getUser(String login, String password){
        Session session = getSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("login", login));
        criteria.add(Restrictions.eq("password", password));
        List<User> users = criteria.list();
        session.close();
        return users;
    }
    
}
