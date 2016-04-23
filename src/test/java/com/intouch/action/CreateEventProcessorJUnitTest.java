/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.intouch.action.CreateEventProcessor;
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
public class CreateEventProcessorJUnitTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    
    @Test
    public void testProcessRequestWithWrongApiKey() throws ServerQueryException {
        expectedException.expect(ServerQueryException.class);
        expectedException.expectMessage("Invalid parameter api_key.");
        
        Map<String, String[]> params = new HashMap<>();
        params.put("api_key", new String[] {"DUDKIN"});
        params.put("name", new String[] {"Buhalovo"});
        params.put("description", new String[] {"Buhaem 24 chasa"});
        params.put("gps", new String[] {"U Vasi na hate"});
        params.put("date_time", new String[] {"Buhaem 24 chasa"});
        params.put("address", new String[] {"Buhaem 24 chasa"});
        params.put("token", new String[] {"token"});
        params.put("type_id", new String[] {"Razvlekatel'noe"});
        
        JSONObject response = new CreateEventProcessor().processRequest(params);
    }
}
