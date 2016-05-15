/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intouch.action;

import com.google.gson.Gson;
import com.intouch.db.DataHelper;
import com.intouch.exceptions.ServerQueryException;
import com.intouch.hibernate.Event;
import com.intouch.hibernate.User;
import java.util.Map;
import org.json.simple.JSONObject;

/**
 *
 * @author kachu
 */
public class GetEventCommentsProcessor extends Processor{
    @Override
    public JSONObject processRequest(Map<String, String[]> params) throws ServerQueryException {
        isParameterExist(params, "token");
        isParameterExist(params, "event_id");
        isApiKeyValid(params.get("api_key"));
        
        DataHelper dataHelper = DataHelper.getInstance();
        User user = dataHelper.getUserByToken(params.get("token")[0]);
        
        if(user == null){
            throw new ServerQueryException("User with token "+ params.get("tonen")[0]+ "does no found");
        }
        Event event = dataHelper.getEventById(Long.parseLong(params.get("event_id")[0]));
        
        JSONObject response = new JSONObject();
        response.put("result", "success");
        response.put("Comments", new Gson().toJson(dataHelper.getEventComments(event)));
        return response;
    }
}
