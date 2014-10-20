package nl.ordina.brewery.manual.boundary;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.jsonp.JsonProcessingFeature;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class TemperatureResourceIT {

    private static final String TEMPERATURE_RESOURCE_ENDPOINT = "http://localhost:8080/brewery/resources/kettle";
    
    private WebTarget target;
    private Client client;
    
    @Before
    public void setUp() throws Exception {
        client = ClientBuilder.newClient().register(JsonProcessingFeature.class);
    }

    @Test
    public void changing_temperature_should_yield_204() {        
        target = client.target(TEMPERATURE_RESOURCE_ENDPOINT);
        String json = "{ \"value\":15, \"unit\":\"celsius\" }";
        
        Response response = target
                .request()
                .post(Entity.entity(json, APPLICATION_JSON_TYPE));
        assertThat(response.getStatus(), is(Response.Status.NO_CONTENT.getStatusCode()));
    }
    
}