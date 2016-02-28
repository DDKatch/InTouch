/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intouch.processor;

//import com.intouch.Methods.Method;
import com.intouch.Methods.Method;
import com.intouch.db.DataHelper;
import com.intouch.hibernate.User;
import com.intouch.validators.ParamsValidator;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;

/**
 *
 * @author Владислав
 */
public class RequestProcessor {
    
    public JSONObject processRequest(Map<String, String[]> params, HttpSession session){
        ParamsValidator pv = new ParamsValidator();
        JSONObject obj = new JSONObject();
        String result = pv.validate(params);
        if(result!=null){
            obj.put("result", "error");
            obj.put("error type", result);
            return obj;
        }
        switch(params.get("method")[0]){
            case "registration":{
                String registrationResult = Method.registration(params.get("first_name")[0], params.get("last_name")[0], params.get("login")[0], params.get("password")[0]);
                
                if(registrationResult!=null){
                    obj.put("result", "error");
                    obj.put("error type", registrationResult);
                    return obj;
                }
                else{
                    obj.put("result", "success");
                    User user = DataHelper.getInstance().getUserByLogin(params.get("login")[0]).get(0);
                    obj.put("session id", session.getId());
                    session.setAttribute("user", user);
                    return obj;
                }
            }
            
            case "login":{
                User user = Method.logIn(params.get("login")[0], params.get("password")[0]);
                if(user==null){
                    obj.put("result", "error");
                    obj.put("error type", "invalid login or password");
                    return obj;
                }
                else{
                    obj.put("result", "success");
                    obj.put("session_id", session.getId());
                    obj.put("user", user);
                    session.setAttribute("user", user);
                    return obj;
                }
            }
            
        }
        
          /*  Session session  = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = session.beginTransaction();
            UserEvent uv = new UserEvent();
            uv.setEventId(123);
            uv.setUserId(1223);
            session.save(uv);
            tx.commit();
        */
        
        return obj;
    }
    public RequestProcessor(){}
}
