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
import org.json.simple.JSONObject;

/**
 *
 * @author vladislav
 */
public class LoginProcessor extends Processor{

    @Override
public JSONObject processRequest(Map<String, String[]> params) throws ServerQueryException{
        User user;
        Gson gson;
        JSONObject response;

        isParameterExist(params, "api_key");
        isParameterExist(params, "login");
        isParameterExist(params, "password");
        isApiKeyValid(params.get("api_key"));
        DataHelper dataHelper = DataHelper.getInstance();
        user = dataHelper.getUser(params.get("login")[0], params.get("password")[0]);
        if(user==null){
        throw new ServerQueryException("Invalid login or password.");
        }
        dataHelper.updateLastVisitTime(user.getToken());
        user.setLastVisit(new Date());

        gson = new Gson();
        response = new JSONObject();
        response.put("result", "success");
        response.put("user", gson.toJson(user, User.class));
        return response;
    }

    
}
