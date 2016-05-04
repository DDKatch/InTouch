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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;

/**
 *
 * @author kachu
 */
public class DeleteEventProcessor extends Processor{
    
    @Override
    public JSONObject processRequest(Map<String, String[]> params) throws ServerQueryException{
        DataHelper dataHelper = DataHelper.getInstance();
        User user;
        JSONObject response;
        response = new JSONObject();
        
        isParameterExist(params, "event_id");
        isParameterExist(params, "token");
        isParameterExist(params, "api_key");
        isApiKeyValid(params.get("api_key"));
        
        user = dataHelper.getUserByToken(params.get("token")[0]);
        if(user==null){
            throw new ServerQueryException("Invalid token");
        }
        
        Event event = dataHelper.getEventById(Long.parseLong(params.get("event_id")[0]));
        
        Gson gson = new Gson();
        response.put("result", "success");
        response.put("event", "deleted");
        
        List<String> tokens = DataHelper.getInstance().getMyFollowersTokens(params.get("token")[0]);
        Map<String, String> map = new HashMap<>();
        
        map.put("name", event.getName());
        map.put("date_time", event.getName());
        map.put("creator_fist_name", user.getFirstName());
        map.put("creator_last_name", user.getLastName());
        map.put("description", "event was deleted");
        
        sengGcmMessages(tokens, map);
        
        dataHelper.deleteEventMarks(event);
        dataHelper.deleteEventComments(event);        
        dataHelper.deleteUserEvent(event);
        dataHelper.deleteEvent(event);
                
        return response;
    }
}

//sengGcmMessages(tokens, map);