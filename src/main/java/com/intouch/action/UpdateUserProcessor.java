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
        isParameterExist(params, "login");
        isParameterExist(params, "password");
        isParameterExist(params, "first_name");
        isParameterExist(params, "last_name");
        isParameterExist(params, "skype");
        isParameterExist(params, "phone");
        isParameterExist(params, "email");
        isParameterExist(params, "image");
        isParameterExist(params, "token");
        isApiKeyValid(params.get("api_key"));
        
        user = dataHelper.getUserByToken(params.get("token")[0]);
        if(user == null){
            throw new ServerQueryException("Invalid token");
        }
        
        user.setSkype(params.get("skype")[0]);
        user.setEmail(params.get("email")[0]);
        user.setPassword(params.get("password")[0]);
        user.setLastName(params.get("last_name")[0]);
        user.setFirstName(params.get("first_name")[0]);
        user.setPhone(params.get("phone")[0]);
        user.setUserImage(params.get("image")[0]);
        
        dataHelper.saveUserChanges(user);
        Gson gson = new Gson();
        response.put("result", "success");
        response.put("user", gson.toJson(user, User.class));
        return response;
    }
}
