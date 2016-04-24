/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intouch.action;

import com.intouch.db.DataHelper;
import com.intouch.exceptions.ServerQueryException;
import com.intouch.hibernate.User;
import java.util.Map;
import org.json.simple.JSONObject;

/**
 *
 * @author vladislav
 */
public class UnfollowEventProcessor extends Processor{

    @Override
    public JSONObject processRequest(Map<String, String[]> params) throws ServerQueryException {
        isApiKeyValid(params.get("api_key"));
        isParameterExist(params, "token");
        isParameterExist(params, "event_id");
        
        User user = DataHelper.getInstance().getUserByToken(params.get("token")[0]);
        
        if(user==null){
            throw new ServerQueryException("User with token "+ params.get("token")[0] +" does not exist");
        }
        
        DataHelper.getInstance().unfollowEvent(user.getId(), Long.parseLong(params.get("event_id")[0]));
        
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("result", "success");
        return jSONObject;
    }
    
}
