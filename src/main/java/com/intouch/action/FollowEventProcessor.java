/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intouch.action;

import com.intouch.db.DataHelper;
import com.intouch.exceptions.ServerQueryException;
import com.intouch.hibernate.User;
import com.intouch.hibernate.UserEvent;
import java.util.Map;
import org.json.simple.JSONObject;

/**
 *
 * @author vladislav
 */
public class FollowEventProcessor extends Processor {

    @Override
    public JSONObject processRequest(Map<String, String[]> params) throws ServerQueryException {
        isParameterExist(params, "token");
        isParameterExist(params, "event_id");
        isApiKeyValid(params.get("api_key"));
        
        User user = DataHelper.getInstance().getUserByToken(params.get("token")[0]);
        
        if(user==null){
            throw new ServerQueryException("User with api key ");
        }
        
        if(null == DataHelper.getInstance().getEventById(Long.parseLong(params.get("event_id")[0]))){
            throw new ServerQueryException("Event with id "+params.get("event_id")[0]+" no found");
        }
        
        if(DataHelper.getInstance().isUserFollowed(user.getId(), Long.parseLong(params.get("event_id")[0]))){
            throw new ServerQueryException("You already follow this event");
        }
        
        UserEvent userEvent = new UserEvent(user.getId(), Integer.parseInt(params.get("event_id")[0]));
        
        DataHelper.getInstance().addEventFollower(userEvent);
        
        JSONObject jSONObject = new JSONObject();
        
        jSONObject.put("result", "success");
        return jSONObject;
        
        
    }
    
}
