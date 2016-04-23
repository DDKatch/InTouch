/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.intouch.action.FollowUserProcessor;
import com.intouch.exceptions.ServerQueryException;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Владислав
 */
public class FollowUserProcessorJUnitTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    
    @Test
    public void testProcessRequestWithWrongApiKey() throws ServerQueryException {
        expectedException.expect(ServerQueryException.class);
        expectedException.expectMessage("Invalid parameter api_key.");
        
        Map<String, String[]> params = new HashMap<>();
        params.put("api_key", new String[] {"DUDKIN"});
        params.put("token", new String[] {"token"});
        params.put("followed_login", new String[] {"LOGIN"});
        
        JSONObject response = new FollowUserProcessor().processRequest(params);
    }
    
    @Test
    public void testProcessRequestWithoutOneParameter() throws ServerQueryException {
        expectedException.expect(ServerQueryException.class);
        expectedException.expectMessage("Parameter followed_login not found.");
        
        Map<String, String[]> params = new HashMap<>();
        params.put("api_key", new String[] {"SHEMODED"});
        params.put("token", new String[] {"token"});
        
        JSONObject response = new FollowUserProcessor().processRequest(params);
    }
    
    @Test
    public void testProcessRequestWithWrongParameter() throws ServerQueryException {
        expectedException.expect(ServerQueryException.class);
        expectedException.expectMessage("Parameter followed_login not found.");
        
        Map<String, String[]> params = new HashMap<>();
        params.put("api_key", new String[] {"SHEMODED"});
        params.put("token", new String[] {"token"});
        params.put("name", new String[] {"Vasya"});
        
        JSONObject response = new FollowUserProcessor().processRequest(params);
    }
    
    @Test
    public void testProcessRequestWithWrongFollowedLogin() throws ServerQueryException {
        expectedException.expect(ServerQueryException.class);
        expectedException.expectMessage("User '.' not found.");
        
        Map<String, String[]> params = new HashMap<>();
        params.put("api_key", new String[] {"SHEMODED"});
        params.put("token", new String[] {"token"});
        params.put("followed_login", new String[] {"."});
        
        JSONObject response = new FollowUserProcessor().processRequest(params);
    }
    
    @Test
    public void testProcessRequestWithSuccess() throws ServerQueryException {
        Map<String, String[]> params = new HashMap<>();
        params.put("api_key", new String[] {"SHEMODED"});
        params.put("token", new String[] {"token"});
        params.put("followed_login", new String[] {"LOGIN"});
        
        JSONObject response = new FollowUserProcessor().processRequest(params);
        
        assertEquals(response.get("result"), "success");
    }
}
