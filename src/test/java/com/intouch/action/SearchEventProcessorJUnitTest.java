/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.intouch.action.SearchEventProcessor;
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
public class SearchEventProcessorJUnitTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    
    @Test
    public void testProcessRequestWithWrongApiKey() throws ServerQueryException {
        expectedException.expect(ServerQueryException.class);
        expectedException.expectMessage("Invalid parameter api_key.");
        
        Map<String, String[]> params = new HashMap<>();
        params.put("api_key", new String[] {"DUDKIN"});
        params.put("token", new String[] {"token"});
        params.put("city", new String[] {"Gomil"});
        params.put("eventName", new String[] {"Buhach"});
        //params.put("typeId", new String[] {"156"});
        
        JSONObject response = new SearchEventProcessor().processRequest(params);
    }
    
    @Test
    public void testProcessRequestWithoutParameters() throws ServerQueryException {
        expectedException.expect(ServerQueryException.class);
        expectedException.expectMessage("No parameters to search.");
        
        Map<String, String[]> params = new HashMap<>();
        params.put("api_key", new String[] {"SHEMODED"});
        params.put("token", new String[] {"token"});
        
        JSONObject response = new SearchEventProcessor().processRequest(params);
    }
    
    @Test
    public void testProcessRequestSuccess() throws ServerQueryException {
        Map<String, String[]> params = new HashMap<>();
        params.put("api_key", new String[] {"SHEMODED"});
        params.put("token", new String[] {"token"});
        params.put("city", new String[] {"Gomil"});
        params.put("eventName", new String[] {"Buhach"});
        params.put("typeId", new String[] {"156"});
        
        JSONObject response = new SearchEventProcessor().processRequest(params);
        
        assertEquals(response.get("result"), "success");
    }
}