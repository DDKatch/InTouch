/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intouch.validators;

import java.util.Map;

/**
 *
 * @author Владислав
 */
public class ParamsValidator {
    public String validate(Map<String, String[]> paramsMap){
        String resultApi = checkApiKey(paramsMap.get("api_key"));
        if(resultApi!=null){
            return resultApi;
        }
        String resultMethod = checkMethod(paramsMap.get("method"));
        if(resultMethod!=null){
            return resultMethod;
        }
        return null;
    }
    
    private String checkApiKey(String[] param){
        if(param==null){
            return "parameter api_key not found";
        }
        else if(!param[0].equals("SCHEMODED")){
            return "invalid parameter api_key";
        }
        return null;
    }
    private String checkMethod(String[] param){
        if(param==null){
            return "parameter method not found";
        }
        //Здесь нужно проверить на наличие существующих методов
        // Так же дальше нужно проверять наличие конкретных параметров
        return null;
    }
}
