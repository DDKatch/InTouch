/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intouch.Methods;

import com.intouch.db.DataHelper;
import com.intouch.hibernate.User;
import java.util.Date;

/**
 *
 * @author Владислав
 */
public class Method {
    
    public static String registration(String firstName, String lastName, String login, String password) {
        DataHelper dataHelper = DataHelper.getInstance();
        if(dataHelper.getUserByLogin(login)!=null){
            return "user with this login already exist";
        }
        else{
            User user = new User(firstName, lastName, login, password, new Date(), new Date());
            dataHelper.createNewUser(user);
        }
        return null;
    }
    
}
