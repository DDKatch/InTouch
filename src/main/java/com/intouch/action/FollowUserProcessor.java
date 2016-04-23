/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intouch.action;

import com.intouch.db.DataHelper;
import com.intouch.exceptions.ServerQueryException;
import com.intouch.hibernate.Event;
import com.intouch.hibernate.User;
import com.intouch.hibernate.UserEvent;
import com.intouch.hibernate.UserSubs;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.json.simple.JSONObject;

/**
 *
 * @author vladislav
 */
public class FollowUserProcessor extends Processor {

    @Override
    public JSONObject processRequest(Map<String, String[]> params) throws ServerQueryException {
        JSONObject jSONObject = new JSONObject();
        User user;
        
        isApiKeyValid(params.get("api_key"));
        isParameterExist(params, "token");
        isParameterExist(params, "followed_login");
        user = getUserByToken(params.get("token")[0]);

        User followedUser = DataHelper.getInstance().getUserByLogin(params.get("followed_login")[0]);
        if(followedUser==null){
            throw new ServerQueryException("User '"+params.get("followed_login")[0]+"' not found.");
        }
        
        UserSubs userSubs = new UserSubs(user.getId(), followedUser.getId());
        
        DataHelper.getInstance().submitUserChanges(userSubs);
        jSONObject.put("result", "success");
        
        return jSONObject;
    }
    
}
