/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intouch.action;

/**
 *
 * @author kachu
 */
import com.google.gson.Gson;
import com.intouch.db.DataHelper;
import com.intouch.exceptions.ServerQueryException;
import com.intouch.hibernate.User;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import org.json.simple.JSONObject;

public class UpdateUserProcessor extends Processor {
    @Override
    public JSONObject processRequest(Map<String, String[]> params) throws ServerQueryException {
        DataHelper dataHelper = DataHelper.getInstance();
        User user;
        JSONObject response;
        response = new JSONObject();
        
        isParameterExist(params, "api_key");
        isParameterExist(params, "token");
        isApiKeyValid(params.get("api_key"));
        user = dataHelper.getUserByToken(params.get("token")[0]);
        if(user == null){
            throw new ServerQueryException("Invalid token");
        }
        
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
        
        if(params.get("login")!=null){
            user.setSkype(params.get("login")[0]);
        }
        
        if(params.get("password")!=null){
            user.setSkype(params.get("password")[0]);
        }
                
        if(params.get("first_name")!=null){
            user.setSkype(params.get("first_name")[0]);
        }
        
        if(params.get("last_name")!=null){
            user.setUserImage(params.get("last_name")[0]);
        }
                
        dataHelper.saveUserChanges(user);
        Gson gson = new Gson();
        response.put("result", "success");
        response.put("user", gson.toJson(user, User.class));
        return response;
    }
}
