package nl.ordina.brewery.manual.boundary;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.jsonp.JsonProcessingFeature;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class IngredientResourceIT {

    private static final String INGREDIENT_RESOURCE_ENDPOINT = "http://localhost:8080/brewery/resources/kettle";
    
    private WebTarget target;
    private Client client;
    
    @Before
    public void setUp() throws Exception {
        client = ClientBuilder.newClient().register(JsonProcessingFeature.class);
    }

    @Test
    public void adding_valid_ingredient_should_yield_204() {        
        target = client.target(INGREDIENT_RESOURCE_ENDPOINT);
        String json = "{ \"ingredient\":\"water\", \"volume\": { \"value\":\"300\", \"unit\":\"liter\" } }";
        
        Response response = target.path("ingredients")
                .request()
                .post(Entity.entity(json, APPLICATION_JSON_TYPE));
        assertThat(response.getStatus(), is(Response.Status.NO_CONTENT.getStatusCode()));
    }
    
    @Test
    public void getting_ingredients_should_yield_200() {
        target = client.target(INGREDIENT_RESOURCE_ENDPOINT);
        Response response = target.path("ingredients")
                .request()
                .get();
        assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));
    }
    
    @Test
    public void deleting_ingredients_should_yield_204() {
        target = client.target(INGREDIENT_RESOURCE_ENDPOINT);
        Response response = target.path("ingredients")
                .request()
                .delete();
        assertThat(response.getStatus(), is(Response.Status.NO_CONTENT.getStatusCode()));
    }
    
//    @Test
//    public void adding_valid_ingredient_should_return_ingredient_on_subsequent_get() {        
//        target = client.target(INGREDIENT_RESOURCE_ENDPOINT);
//        String json = "{ \"ingredient\":\"water\", \"volume\": { \"value\":\"300\", \"unit\":\"liter\" } }";
//        
//        target.path("ingredients")
//              .request()
//              .post(Entity.entity(json, APPLICATION_JSON_TYPE));
//                
//        Response response = target.path("ingredients")
//                .request()
//                .get();
//                
//        
//        assertThat(response.getEntity(), is(expectedResult));
//    }
}