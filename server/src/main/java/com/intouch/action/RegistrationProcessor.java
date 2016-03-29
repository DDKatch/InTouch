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
        try{
            isParameterExist(params, "api_key");
            isParameterExist(params, "login");
            isParameterExist(params, "password");
            isParameterExist(params, "first_name");
            isParameterExist(params, "last_name");
            isApiKeyValid(params.get("api_key")[0]);
            dataHelper = DataHelper.getInstance();
            if(dataHelper.getUserByLogin(params.get("login")[0])!=null){
                throw new ServerQueryException("User with login "+ params.get("login")[0] +"already exist.");
            }
        }
        catch(ServerQueryException ex){
            response.put("result", "error");
            response.put("error_type", ex.getMessage());
            return response;
        }
        User user = new User(params.get("first_name")[0], params.get("last_name")[0], params.get("login")[0], params.get("password")[0], new Date(), new Date(), UUID.randomUUID().toString());
        dataHelper.createNewUser(user);
        
        Gson gson = new Gson();
        response.put("result", "success");
        response.put("user", gson.toJson(user, User.class));
        return response;
    }
    
}
