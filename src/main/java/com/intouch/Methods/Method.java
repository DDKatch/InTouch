/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intouch.Methods;

import com.intouch.db.DataHelper;
import com.intouch.exceptions.ServerQueryException;
import com.intouch.hibernate.User;
import java.util.Date;

/**
 *
 * @author Владислав
 */
public class Method {
    
    public static User registration(String firstName, String lastName, String login, String password, String token) throws ServerQueryException {
        DataHelper dataHelper = DataHelper.getInstance();
        if(dataHelper.getUserByLogin(login)!=null){
            throw new ServerQueryException("User with login "+login+" is already exist.");
        }
        else{ 
            User user = new User(firstName, lastName, login, password, new Date(), new Date(), token);           
            dataHelper.createNewUser(user);
            return user;
        }
        
        
    }
    
    public static User logIn(String login, String password) throws ServerQueryException{
        DataHelper dataHelper = DataHelper.getInstance();
        User user = dataHelper.getUser(login, password);
        if(user==null){
            throw new ServerQueryException("User with login "+login+" not found.");
        }
        return user;
    }
    
}
