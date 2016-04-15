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
public class GetEventCreatorProcessor extends Processor{

    @Override
    public JSONObject processRequest(Map<String, String[]> params) throws ServerQueryException {
        isParameterExist(params, "token");
        isApiKeyValid(params.get("api_key"));
        isParameterExist(params, "event_id");
        if(null==DataHelper.getInstance().getUserByToken(params.get("token")[0])){
            throw new ServerQueryException("User with token "+ params.get("tonen")[0]+ "does no found");
        }
        DataHelper dataHelper = DataHelper.getInstance();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("result", "success");
        jSONObject.put("User", new Gson().toJson(dataHelper.getEventCreator(Integer.parseInt(params.get("event_id")[0]))));
        return jSONObject;
    }
    
}
