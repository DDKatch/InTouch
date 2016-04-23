/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.intouch.action.LoginProcessor;
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
public class LoginProcessorJUnitTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    
    @Test
    public void testProcessRequestWithWrongApiKey() throws ServerQueryException {
        expectedException.expect(ServerQueryException.class);
        expectedException.expectMessage("Invalid parameter api_key.");
        
        Map<String, String[]> params = new HashMap<>();
        params.put("api_key", new String[] {"DUDKIN"});
        params.put("login", new String[] {"asd"});
        params.put("password", new String[] {"123123"});
        
        JSONObject response = new LoginProcessor().processRequest(params);
    }
    
    @Test
    public void testProcessRequestWithoutOneParameter() throws ServerQueryException {
        expectedException.expect(ServerQueryException.class);
        expectedException.expectMessage("Parameter password not found.");
        
        Map<String, String[]> params = new HashMap<>();
        params.put("api_key", new String[] {"SHEMODED"});
        params.put("login", new String[] {"asd"});
        
        JSONObject response = new LoginProcessor().processRequest(params);
    }
    
    @Test
    public void testProcessRequestWithWrongParameter() throws ServerQueryException {
        expectedException.expect(ServerQueryException.class);
        expectedException.expectMessage("Parameter login not found.");
        
        Map<String, String[]> params = new HashMap<>();
        params.put("api_key", new String[] {"SHEMODED"});
        params.put("first_name", new String[] {"asd"});
        params.put("password", new String[] {"123123"});
        
        JSONObject response = new LoginProcessor().processRequest(params);
    }
    
    @Test
    public void testProcessRequestIfUserExists() throws ServerQueryException {
        expectedException.expect(ServerQueryException.class);
        expectedException.expectMessage("Invalid login or password.");
        
        Map<String, String[]> params = new HashMap<>();
        params.put("api_key", new String[] {"SHEMODED"});
        params.put("login", new String[] {"LOGIN"});
        params.put("password", new String[] {"12321"});
        
        JSONObject response = new LoginProcessor().processRequest(params);
    }
    
    @Test
    public void testProcessRequestSuccess() throws ServerQueryException {
        Map<String, String[]> params = new HashMap<>();
        params.put("api_key", new String[] {"SHEMODED"});
        params.put("login", new String[] {"LOGIN"});
        params.put("password", new String[] {"123123"});
        
        JSONObject response = new LoginProcessor().processRequest(params);
        
        assertEquals(response.get("result"), "success");
    }
}
