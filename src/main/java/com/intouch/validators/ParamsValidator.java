/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intouch.validators;

import java.util.Map;
import com.intouch.secure.Secure;

/**
 *
 * @author Владислав
 */

public class ParamsValidator {    
    
    public String validate(Map<String, String[]> paramsMap){
        //System.out.println("VALIDATOR");
        String resultApi = checkApiKey(paramsMap.get("api_key"));
        if(resultApi!=null){
            return resultApi;
        }
        
        String resultMethod = checkMethod(paramsMap);
        if(resultMethod!=null){
            return resultMethod;
        }
        
        return null;
    }
    
    private String checkApiKey(String[] param) {
        if(param==null){
            return "parameter api_key is null";
        }
        else if(!param[0].equals(new Secure().getApi_key())){
            return "invalid parameter api_key";
        }
        return null;
    }
    
    private String checkMethod(Map<String, String[]> param){
        String result;
        if(param==null){
            return "parameter method is null";
        }
        switch(param.get("method")[0]){
            case "registration":{
                result = checkRegistrationMethod(param);
                return result;
            } 
            case "login":{
                result = checkLoginMethod(param);
                return result;
            } 
            case "logout":{
                return null;
            }   
            default: return "parameter method is invalid";
        }
    }
    
    private String checkRegistrationMethod(Map<String, String[]> param){
        if(param.get("first_name")==null){
            return "first name parameter is null";
        }
        if(param.get("last_name")==null){
            return "last name parameter is null";
        }
        if(param.get("login")==null){
            return "login parameter is null";
        }
        if(param.get("password")==null){
            return "password parameter is null";
        }
        return null;
    }
    
    private String checkLoginMethod(Map<String, String[]> param){
        if(param.get("login")==null){
            return "login parameter is null";
        }
        if(param.get("password")==null){
            return "password parameter is null";
        }
        return null;
    }
    
}
