/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intouch.Methods;

import com.intouch.db.DataHelper;
import com.intouch.hibernate.User;
import com.intouch.secure.Encryption;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Владислав
 */
public class Method {
    
    public static User registration(String firstName, String lastName, String login, String password) {
        DataHelper dataHelper = DataHelper.getInstance();
        if(!dataHelper.getUserByLogin(login).isEmpty()){
            
            return null;
        }
        else{
            User user = new User(firstName, lastName, login, password, new Date(), new Date());
            dataHelper.createNewUser(user);
            return user;
        }
        
    }
    
    public static User logIn(String login, String password){
        DataHelper dataHelper = DataHelper.getInstance();
        List<User> userList = dataHelper.getUser(login, password);
        if(userList.isEmpty()){
            return null;
        }
        else{
            User user = userList.get(0);
            return user;
        }
    }
    
}
