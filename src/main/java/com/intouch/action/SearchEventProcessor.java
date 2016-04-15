/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intouch.action;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.intouch.db.DataHelper;
import com.intouch.exceptions.ServerQueryException;
import com.intouch.hibernate.Event;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.proxy.HibernateProxy;
import org.json.simple.JSONObject;

/**
 *
 * @author vladislav
 */
public class SearchEventProcessor extends Processor{

    @Override
    public JSONObject processRequest(Map<String, String[]> params) throws ServerQueryException {
        isParameterExist(params, "token");
        isApiKeyValid(params.get("api_key")[0]);
        Map<String, Object> parameters = new HashMap<>();
        
        if(params.get("city")!=null){
            parameters.put("city", params.get("city")[0]);
        }
        
        if(params.get("eventName")!=null){
            parameters.put("name", params.get("eventName")[0]);
        }
        
        if(params.get("typeId")!=null){
            parameters.put("type_id", params.get("typeId")[0]);
        }
        if(parameters.size()==0){
            throw new ServerQueryException("No parameters to search.");
        }
        
       // GsonBuilder b = new GsonBuilder();
        //b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        Gson gson = new Gson();
        List<Event> list = DataHelper.getInstance().searchEvents(parameters);
        
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("result", "success");
        jSONObject.put("events", gson.toJson(list));
        

        return jSONObject;
    }
    
}
