/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.intouch.action.UpdateEventProcessor;
import com.intouch.exceptions.ServerQueryException;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Владислав
 */
public class UpdateEventProcessorJUnitTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    
    @Test
    public void testProcessRequestWithWrongApiKey() throws ServerQueryException {
        expectedException.expect(ServerQueryException.class);
        expectedException.expectMessage("Invalid parameter api_key.");
               
        Map<String, String[]> params = new HashMap<>();
        params.put("event_id", new String[] {"2"});
        params.put("name", new String[] {"Buhalovo"});
        params.put("description", new String[] {"Buhaem 24 chasa"});
        params.put("gps", new String[] {"U Vasi na hate"});
        params.put("date_time", new String[] {"Buhaem 24 chasa"});
        params.put("address", new String[] {"Buhaem 24 chasa"});
        params.put("type_id", new String[] {"2"});
        params.put("token", new String[] {"Buhaem 24 chasa"});
        params.put("city", new String[] {"Minsk"});
        params.put("api_key", new String[] {"DUDKIN"});
        
        JSONObject response = new UpdateEventProcessor().processRequest(params);
    }
    
    @Test
    public void testProcessRequestWithoutOneParameter() throws ServerQueryException {
        expectedException.expect(ServerQueryException.class);
        expectedException.expectMessage("Parameter name not found.");
        
        Map<String, String[]> params = new HashMap<>();
        params.put("event_id", new String[] {"2"});
        params.put("description", new String[] {"Buhaem 24 chasa"});
        params.put("gps", new String[] {"U Vasi na hate"});
        params.put("date_time", new String[] {"Buhaem 24 chasa"});
        params.put("address", new String[] {"Buhaem 24 chasa"});
        params.put("type_id", new String[] {"2"});
        params.put("token", new String[] {"Buhaem 24 chasa"});
        params.put("city", new String[] {"Minsk"});
        params.put("api_key", new String[] {"DUDKIN"});
        
        JSONObject response = new UpdateEventProcessor().processRequest(params);
    }
    
    @Test
    public void testProcessRequestWithWrongParameter() throws ServerQueryException {
        expectedException.expect(ServerQueryException.class);
        expectedException.expectMessage("Parameter description not found.");
        
        Map<String, String[]> params = new HashMap<>();
        params.put("event_id", new String[] {"2"});
        params.put("name", new String[] {"Buhalovo"});
        params.put("desc", new String[] {"Buhaem 24 chasa"});
        params.put("gps", new String[] {"U Vasi na hate"});
        params.put("date_time", new String[] {"Buhaem 24 chasa"});
        params.put("address", new String[] {"Buhaem 24 chasa"});
        params.put("type_id", new String[] {"2"});
        params.put("token", new String[] {"Buhaem 24 chasa"});
        params.put("city", new String[] {"Minsk"});
        params.put("api_key", new String[] {"DUDKIN"});
        
        JSONObject response = new UpdateEventProcessor().processRequest(params);
    }
    
    @Test
    public void testProcessRequestSuccess() throws ServerQueryException {
        Map<String, String[]> params = new HashMap<>();
        params.put("event_id", new String[] {"3"});
        params.put("name", new String[] {"Buhalovo"});
        params.put("description", new String[] {"Buhaem 24 chasa"});
        params.put("gps", new String[] {"U Vasi na hate"});
        params.put("date_time", new String[] {"Wed, 4 Jul 2001 12:08:56 -0700"});
        params.put("address", new String[] {"Buhaem 24 chasa"});
        params.put("type_id", new String[] {"2"});
        params.put("token", new String[] {"token"});
        params.put("city", new String[] {"Minsk"});
        params.put("api_key", new String[] {"SHEMODED"});
        
        JSONObject response = new UpdateEventProcessor().processRequest(params);
        
        assertEquals(response.get("result"), "success");
    }
}
