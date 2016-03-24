/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intouch.processor;

import com.google.gson.Gson;
import com.intouch.Methods.Method;
import com.intouch.exceptions.ServerQueryException;
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
        Gson gson = new Gson();
        try{
            pv.validate(params);
        }  
        catch(ServerQueryException ex){
            obj.put("result", "error");
            obj.put("error type", ex.getMessage());
            return obj;
        }
        switch(params.get("method")[0]){
            case "registration":{
                try{
                    User user = Method.registration(params.get("first_name")[0], params.get("last_name")[0], params.get("login")[0], params.get("password")[0], "token");/////////////////
                   obj.put("result", "success");
                   obj.put("session id", "token");/////////////////////   
                    obj.put("user", gson.toJson(user, User.class));
                    return obj;
                }
                
                catch(ServerQueryException e){
                    obj.put("result", "error");
                    obj.put("error type", e.getMessage());                   
                    return obj;
                }

            }
            
            case "login":{
            try {
                User user = Method.logIn(params.get("login")[0], params.get("password")[0]);
                obj.put("result", "success");
                obj.put("session_id", session.getId());
                obj.put("user", gson.toJson(user, User.class));
                session.setAttribute("user", user);
                return obj;
            } catch (ServerQueryException ex) {
                obj.put("result", "error");
                obj.put("error type", ex.getMessage());
                return obj;
            }
            }
            default: return null;
        }
    }
    public RequestProcessor(){}
}
