/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intouch.validators;

import com.intouch.exceptions.ServerQueryException;
import java.util.Map;
import com.intouch.secure.Secure;

/**
 *
 * @author Владислав
 */

public class ParamsValidator {    
    
    public void validate(Map<String, String[]> paramsMap) throws ServerQueryException{
        checkApiKey(paramsMap.get("api_key"));  
        checkMethod(paramsMap);
    }
    
    private void checkApiKey(String[] param) throws ServerQueryException {
        if(param==null){
            throw new ServerQueryException("parameter api_key is null");
        }
        else if(!param[0].equals(new Secure().getApi_key())){
            throw new ServerQueryException("invalid parameter api_key");
        }
    }
    
    private void checkMethod(Map<String, String[]> param) throws ServerQueryException{
        if(param==null){
            throw new ServerQueryException("parameter method is null");
        }
        switch(param.get("method")[0]){
            case "registration":{
                checkRegistrationMethod(param);
            } 
            case "login":{
                checkLoginMethod(param);
            } 
            case "logout":{
                break;
            }   
            default: throw new ServerQueryException("parameter method is invalid");
        }
    }
    
    private void checkRegistrationMethod(Map<String, String[]> param) throws ServerQueryException{
        if(param.get("first_name")==null){
            throw new ServerQueryException("first name parameter is null");
        }
        if(param.get("last_name")==null){
            throw new ServerQueryException("last name parameter is null");
        }
        if(param.get("login")==null){
            throw new ServerQueryException("login parameter is null");
        }
        if(param.get("password")==null){
            throw new ServerQueryException("password parameter is null");
        }
    }
    
    private void checkLoginMethod(Map<String, String[]> param) throws ServerQueryException{
        if(param.get("login")==null){
            throw new ServerQueryException("login parameter is null");
        }
        if(param.get("password")==null){
            throw new ServerQueryException("password parameter is null");
        }
    }    
}
