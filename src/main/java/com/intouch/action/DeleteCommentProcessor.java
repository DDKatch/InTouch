/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intouch.action;

import com.google.gson.Gson;
import com.intouch.db.DataHelper;
import com.intouch.exceptions.ServerQueryException;
import com.intouch.hibernate.Comments;
import com.intouch.hibernate.User;
import java.util.Map;
import org.json.simple.JSONObject;

/**
 *
 * @author kachu
 */
public class DeleteCommentProcessor extends Processor{
    @Override
    public JSONObject processRequest(Map<String, String[]> params) throws ServerQueryException {
        isParameterExist(params, "token");
        isParameterExist(params, "comment_id");
        isApiKeyValid(params.get("api_key"));
        
        DataHelper dataHelper = DataHelper.getInstance();
        User user = dataHelper.getUserByToken(params.get("token")[0]);
        if(user == null){
            throw new ServerQueryException("User with token "+ params.get("tonen")[0]+ "does no found");
        }
        
        Comments comment = dataHelper.getCommentById(Long.parseLong(params.get("comment_id")[0]));
        
        if(user.getId() != comment.getUserId())
            throw new ServerQueryException("User isn't comment creator");
        
        dataHelper.deleteComment(comment);
        
        JSONObject response = new JSONObject();
        response.put("result", "success");
        response.put("comment", "deleted");
        return response;
    }
    
}
