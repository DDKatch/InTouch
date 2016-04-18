/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intouch.action;

import com.intouch.db.DataHelper;
import com.intouch.exceptions.ServerQueryException;
import com.intouch.hibernate.User;
import com.intouch.messages.GCM;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;

/**
 *
 * @author vladislav
 */
public abstract class Processor {
    public abstract JSONObject processRequest(Map<String, String[]> params) throws ServerQueryException;
    protected void isParameterExist(Map<String, String[]> map, String parameter) throws ServerQueryException {
        if(map.get(parameter)==null){
            throw new ServerQueryException("Parameter "+ parameter+" not found.");
        }
    }
    
    final protected void isApiKeyValid(String[] api_key) throws ServerQueryException{
        if(!api_key[0].equals("SHEMODED")||api_key==null){
            throw new ServerQueryException("Invalid parameter api_key.");
        }
    }
    
    final protected User getUserByToken(String token) throws ServerQueryException{
        DataHelper dataHelper = DataHelper.getInstance();
        User user = dataHelper.getUserByToken(token);
        
        if(user==null){
            throw new ServerQueryException("Inavalid token");
        }
        
        return user;
    }
    
    final protected void sengGcmMessages(List<String> tokens, Map<String, String> messages){
        for(String token:tokens){
            GCM.send1(messages, token);     
        }
        
    }
    
}
