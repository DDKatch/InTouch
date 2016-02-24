/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intouch.processor;

import com.intouch.hibernate.HibernateUtil;
import com.intouch.hibernate.UserEvent;
import com.intouch.validators.ParamsValidator;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;

/**
 *
 * @author Владислав
 */
public class RequestProcessor {
    
    public JSONObject processRequest(Map<String, String[]> params){
        ParamsValidator pv = new ParamsValidator();
        JSONObject obj = new JSONObject();
       
            Session session  = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = session.beginTransaction();
            UserEvent uv = new UserEvent();
            uv.setEventId(123);
            uv.setUserId(1223);
            session.save(uv);
            tx.commit();
        
        
        return obj;
    }
}
