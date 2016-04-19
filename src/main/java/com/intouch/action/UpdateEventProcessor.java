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
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;

/**
 *
 * @author kachu
 */
public class UpdateEventProcessor extends Processor{
    //http://localhost:8080/InTouch/RequestServlet?method=updateEvent&event_id=3&name=third&description=azazazaz&gps=123.123.123&date_time=Wed,%204%20Jul%202001%2012:08:56%20-0700&address=miu&token=token&type_id=1&api_key=SHEMODED&city=%22Gomel%22
    @Override
    public JSONObject processRequest(Map<String, String[]> params) throws ServerQueryException{
        DataHelper dataHelper = DataHelper.getInstance();
        User user, eventCreator;
        JSONObject response;
        response = new JSONObject();
         
        isParameterExist(params, "event_id");
        isParameterExist(params, "name");
        isParameterExist(params, "description");
        isParameterExist(params, "gps");
        isParameterExist(params, "date_time");
        isParameterExist(params, "address");
        isParameterExist(params, "type_id");
        isParameterExist(params, "token");
        isParameterExist(params, "city");
        isApiKeyValid(params.get("api_key"));
        
        user = dataHelper.getUserByToken(params.get("token")[0]);
        if(user == null){
            throw new ServerQueryException("Invalid token");
        }
        
        eventCreator = dataHelper.getEventCreator(Long.parseLong(params.get("event_id")[0]));
        if(eventCreator == null){
            throw new ServerQueryException("Invalid event_id");
        }
        
        if (!user.getToken().equals(eventCreator.getToken()))
            throw new ServerQueryException("This user have not permission to this event. Check event id.");
        else{
            
            Date date_time;
        
            try {
                date_time = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH).parse(params.get("date_time")[0]);
            } catch (ParseException ex) {
                Logger.getLogger(UpdateEventProcessor.class.getName()).log(Level.SEVERE, null, ex);
                throw new ServerQueryException("invalid date format");
            }
            
            Event event = dataHelper.getEventById(Long.parseLong(params.get("event_id")[0]));   
            
            event.setName(params.get("name")[0]);
            event.setDescription(params.get("description")[0]);
            event.setGps(params.get("gps")[0]);
            event.setDateTime(date_time);
            event.setAddress(params.get("address")[0]);
            
            dataHelper.saveEventChanges(event);
            
            Gson gson = new Gson();
            response.put("result", "success");
            response.put("update event", gson.toJson(event, Event.class));
            return response;
        }
    }

}
