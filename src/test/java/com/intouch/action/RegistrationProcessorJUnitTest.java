/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import com.intouch.action.RegistrationProcessor;
import com.intouch.exceptions.ServerQueryException;
import org.json.simple.JSONObject;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Владислав
 */
public class RegistrationProcessorJUnitTest {
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
        params.put("first_name", new String[] {"asd"});
        params.put("last_name", new String[] {"asd"});
        params.put("applicationId", new String[] {"fsdfs"});
        params.put("deviceId", new String[] {"assdfsdsdd"});
        
        JSONObject response = new RegistrationProcessor().processRequest(params);
        
    }
    
    @Test
    public void testProcessRequestWithoutOneParameter() throws ServerQueryException {
        expectedException.expect(ServerQueryException.class);
        expectedException.expectMessage("Parameter last_name not found.");
        
        Map<String, String[]> params = new HashMap<>();
        params.put("api_key", new String[] {"SHEMODED"});
        params.put("login", new String[] {"asd"});
        params.put("password", new String[] {"123123"});
        params.put("first_name", new String[] {"asd"});
        params.put("applicationId", new String[] {"fsdfs"});
        params.put("deviceId", new String[] {"assdfsdsdd"});
        
        JSONObject response = new RegistrationProcessor().processRequest(params);
    }
    
    @Test
    public void testProcessRequestWithWrongParameter() throws ServerQueryException {
        Map<String, String[]> params = new HashMap<>();
        params.put("api_key", new String[] {"SHEMODED"});
        params.put("login", new String[] {"asd"});
        params.put("address", new String[] {"123123"});
        params.put("first_name", new String[] {"asd"});
        params.put("last_name", new String[] {"asd"});
        params.put("applicationId", new String[] {"fsdfs"});
        params.put("deviceId", new String[] {"assdfsdsdd"});
        
        expectedException.expect(ServerQueryException.class);
        expectedException.expectMessage("Parameter password not found.");
        
        JSONObject response = new RegistrationProcessor().processRequest(params);
    }
    
    @Test
    public void testProcessRequestIfUserExists() throws ServerQueryException {
        Map<String, String[]> params = new HashMap<>();
        params.put("api_key", new String[] {"SHEMODED"});
        params.put("login", new String[] {"LOGIN"});
        params.put("password", new String[] {"123123"});
        params.put("first_name", new String[] {"asd"});
        params.put("last_name", new String[] {"asd"});
        params.put("applicationId", new String[] {"fsdfs"});
        params.put("deviceId", new String[] {"assdfsdsdd"});
        
        expectedException.expect(ServerQueryException.class);
        expectedException.expectMessage("User with login "+ params.get("login")[0] +"already exist.");
        
        JSONObject response = new RegistrationProcessor().processRequest(params);
        response = new RegistrationProcessor().processRequest(params);
    }
    
    @Test
    public void testProcessRequestSuccess() throws ServerQueryException {
        Map<String, String[]> params = new HashMap<>();
        params.put("api_key", new String[] {"SHEMODED"});
        params.put("login", new String[] {"jhgjg" + Math.random()});
        params.put("password", new String[] {"123123"});
        params.put("first_name", new String[] {"asd"});
        params.put("last_name", new String[] {"asd"});
        params.put("applicationId", new String[] {"fsdfs"});
        params.put("deviceId", new String[] {"assdfsdsdd"});
        
        JSONObject response = new RegistrationProcessor().processRequest(params);
        assertEquals(response.get("result"), "success");
    }
}


