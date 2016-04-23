/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intouch.action;

import com.intouch.db.DataHelper;
import com.intouch.exceptions.ServerQueryException;
import java.util.Map;
import org.json.simple.JSONObject;

/**
 *
 * @author vladislav
 */
public class CreateCommentProcessor extends Processor {

    @Override
    public JSONObject processRequest(Map<String, String[]> params) throws ServerQueryException {
        isApiKeyValid(params.get(("api_key")));
        isParameterExist(params, "event_id");
        isParameterExist(params, "token");
        isParameterExist(params, "comment");
        DataHelper.getInstance().setComment(params.get("token")[0], params.get("comment")[0], Long.parseLong(params.get("event_id")[0]));
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("result", "success");
        return jSONObject;
    }
    
}
