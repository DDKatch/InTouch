/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intouch.processor;

import com.intouch.action.Processor;
import com.intouch.db.DataHelper;
import com.intouch.exceptions.ServerQueryException;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import org.json.simple.JSONObject;

/**
 *
 * @author Владислав
 */
public class RequestProcessor {
    
    public JSONObject processRequest(HttpServletRequest request) throws Exception{
        JSONObject jSONObject;
        InputStream inputStream = request.getServletContext().getResourceAsStream("/WEB-INF/prop.properties");
        Properties properties = new Properties();
        String str = request.getContextPath();
        properties.load(inputStream);
        Class cls = Class.forName(properties.getProperty(request.getParameter("method")));
        Processor processor = (Processor) cls.newInstance();
        inputStream.close();
        try{
            jSONObject = processor.processRequest(request.getParameterMap());
            processor.updateLastVisitTime(request.getParameterMap());
        }     
        catch(ServerQueryException ex){
            jSONObject = new JSONObject();
            jSONObject.put("result", "error");
            jSONObject.put("error_type", ex.getMessage());
        }
        catch(Exception ex){
            jSONObject = new JSONObject();
            jSONObject.put("result", "error");
            jSONObject.put("error_type", ex.getMessage());
            if(!DataHelper.getInstance().getSession().getTransaction().wasCommitted()){
                DataHelper.getInstance().getSession().getTransaction().commit();
            }
        }
        return jSONObject;
    }
}
