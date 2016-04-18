/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intouch.action;

import com.google.gson.Gson;
import com.intouch.db.DataHelper;
import com.intouch.exceptions.ServerQueryException;
import java.util.Map;
import org.json.simple.JSONObject;

/**
 *
 * @author vladislav
 */
public class GetEventFollowersProcessor extends Processor{

    @Override
    public JSONObject processRequest(Map<String, String[]> params) throws ServerQueryException {
        isApiKeyValid(params.get("api_key"));
        isParameterExist(params, "token");
        isParameterExist(params, "event_id");
        
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("result", "success");
        jSONObject.put("users", new Gson().toJson(DataHelper.getInstance().getEventFollowers(params.get("token")[0], Long.parseLong(params.get("event_id")[0]))));
        return jSONObject;
    }
    
}
