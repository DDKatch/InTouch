/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.intouch.action.GetEventTypesProcessor;
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
public class GetEventTypesJUnitTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    
    @Test
    public void testProcessRequestWithWrongApiKey() throws ServerQueryException {
        expectedException.expect(ServerQueryException.class);
        expectedException.expectMessage("Invalid parameter api_key.");
        
        Map<String, String[]> params = new HashMap<>();
        params.put("api_key", new String[] {"DUDKIN"});
        
        JSONObject response = new GetEventTypesProcessor().processRequest(params);
    }
    
    @Test
    public void testProcessRequestSuccess() throws ServerQueryException {
        Map<String, String[]> params = new HashMap<>();
        params.put("api_key", new String[] {"SHEMODED"});
        
        JSONObject response = new GetEventTypesProcessor().processRequest(params);
        
        assertEquals(response.get("result"), "success");
    }
}
