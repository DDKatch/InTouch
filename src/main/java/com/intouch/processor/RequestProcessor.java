/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intouch.processor;

import com.intouch.action.Processor;
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
        InputStream inputStream = request.getServletContext().getResourceAsStream("/WEB-INF/prop.properties");
        Properties properties = new Properties();
        String str = request.getContextPath();
        properties.load(inputStream);
        Class cls = Class.forName(properties.getProperty(request.getParameter("method")));
        Processor processor = (Processor) cls.newInstance();
        inputStream.close();
        return processor.processRequest(request.getParameterMap());
    }
}
