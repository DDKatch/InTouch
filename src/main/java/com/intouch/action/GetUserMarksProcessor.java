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
public class GetUserMarksProcessor extends Processor{

    @Override
    public JSONObject processRequest(Map<String, String[]> params) throws ServerQueryException {
        isApiKeyValid(params.get("api_key"));
        isParameterExist(params, "token");
        isParameterExist(params, "user_id");
        
        Long userId = DataHelper.getInstance().getUserIdByToken(params.get("token")[0]);
        
        JSONObject jSONObject = new JSONObject();
        
        jSONObject.put("result", "success");
        
        jSONObject.put("marks", new Gson().toJson(DataHelper.getInstance().getEventMarks(Long.parseLong(params.get("user_id")[0]), "userId")));
        
        return jSONObject;
    }
    
}
