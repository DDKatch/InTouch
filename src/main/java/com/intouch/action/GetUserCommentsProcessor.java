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
import java.util.Map;
import org.json.simple.JSONObject;

/**
 *
 * @author kachu
 */
public class GetUserCommentsProcessor extends Processor{
    @Override
    public JSONObject processRequest(Map<String, String[]> params) throws ServerQueryException {
        isParameterExist(params, "token");
        isApiKeyValid(params.get("api_key"));
        
        DataHelper dataHelper = DataHelper.getInstance();
        User user = dataHelper.getUserByToken(params.get("token")[0]);
        if(user == null){
            throw new ServerQueryException("User with token "+ params.get("tonen")[0]+ "does no found");
        }
        
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("result", "success");
        jSONObject.put("Comments", new Gson().toJson(dataHelper.getUserComments(user)));
        return jSONObject;
    }
}
