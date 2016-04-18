/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intouch.action;

import com.google.gson.Gson;
import com.intouch.db.DataHelper;
import com.intouch.exceptions.ServerQueryException;
import com.intouch.hibernate.Event;
import com.intouch.hibernate.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;

/**
 *
 * @author kachu
 */
public class EventProcessor extends Processor{
    
    @Override
    public JSONObject processRequest(Map<String, String[]> params) throws ServerQueryException{
        DataHelper dataHelper = DataHelper.getInstance();
        User user;
        JSONObject response;
        response = new JSONObject();
        
        isParameterExist(params, "name");
        isParameterExist(params, "description");
        isParameterExist(params, "gps");
        isParameterExist(params, "date_time");
        isParameterExist(params, "address");
        isParameterExist(params, "token");
        isParameterExist(params, "type_id");
        isParameterExist(params, "city");
        isApiKeyValid(params.get("api_key"));
        
        user = dataHelper.getUserByToken(params.get("token")[0]);
        Event event = null;
        Date date_time = null;
        try {
            date_time = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(params.get("date_time")[0]);
        } catch (ParseException ex) {
            Logger.getLogger(EventProcessor.class.getName()).log(Level.SEVERE, null, ex);
            date_time = new Date();
        }
        
        event = new Event(params.get("name")[0], params.get("description")[0], params.get("gps")[0], user.getId(), date_time, params.get("address")[0], new Date(), Integer.parseInt(params.get("type_id")[0]), params.get("city")[0]);
        dataHelper.createNewEvent(event);
        Gson gson = new Gson();
        response.put("result", "success");
        response.put("event", gson.toJson(event, Event.class));
        List<String> tokens = DataHelper.getInstance().getMyFollowersTokens(params.get("token")[0]);
        
        return response;
    }

}
