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
public class GetEventTypesProcessor extends Processor{

    @Override
    public JSONObject processRequest(Map<String, String[]> params) throws ServerQueryException {
        isApiKeyValid(params.get("api_key")[0]);
        JSONObject jSONObject = new JSONObject();
        Gson gson = new Gson();
        DataHelper dataHelper = DataHelper.getInstance();
        jSONObject.put("result", "success");
        jSONObject.put("EventTypes", gson.toJson(dataHelper.getEventTypes()));      
        return jSONObject;
    }   
}