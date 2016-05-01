/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intouch.action;

import com.google.gson.Gson;
import com.intouch.db.DataHelper;
import com.intouch.exceptions.ServerQueryException;
import com.intouch.hibernate.User;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import org.json.simple.JSONObject;

/**
 *
 * @author vladislav
 */
public class RegistrationProcessor extends Processor {

    @Override
    public JSONObject processRequest(Map<String, String[]> params) throws ServerQueryException {
        JSONObject response;
        response = new JSONObject();
        DataHelper dataHelper = null;
        
        isParameterExist(params, "api_key");
        isParameterExist(params, "login");
        isParameterExist(params, "password");
        isParameterExist(params, "first_name");
        isParameterExist(params, "last_name");
        isParameterExist(params, "applicationId");
        isParameterExist(params, "deviceId");
        isApiKeyValid(params.get("api_key"));
        dataHelper = DataHelper.getInstance();
        if(dataHelper.getUserByLogin(params.get("login")[0])!=null){
            throw new ServerQueryException("User with login "+ params.get("login")[0] +"already exist.");
        }
        User user = new User(params.get("first_name")[0], params.get("last_name")[0], params.get("login")[0], params.get("password")[0], new Date(), new Date(), UUID.randomUUID().toString(), params.get("deviceId")[0]);
       
        if(params.get("skype")!=null){
            user.setSkype(params.get("skype")[0]);
        }
        
        if(params.get("email")!=null){
            user.setSkype(params.get("email")[0]);
        }
                
        if(params.get("skype")!=null){
            user.setSkype(params.get("phone")[0]);
        }
        
        if(params.get("image_url")!=null){
            user.setUserImage(params.get("image_url")[0]);
        }
        user.setApplicationId(params.get("applicationId")[0]);
        dataHelper.createNewUser(user);
        
        Gson gson = new Gson();
        response.put("result", "success");
        response.put("user", gson.toJson(user, User.class));
        return response;
    }
    
}
