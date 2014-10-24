package nl.ordina.beer.brewing.recipe.boundary;

import org.glassfish.jersey.jsonp.JsonProcessingFeature;
import org.junit.Before;
import org.junit.Test;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static nl.ordina.beer.brewing.recipe.entity.RecipeBuilder.aRecipeJson;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RecipeResourceIT {

  private Client client;

  @Before
  public void setUp() throws Exception {
    client = ClientBuilder.newClient()
        .register(JsonProcessingFeature.class);
  }

  @Test
  public void test() {
    final WebTarget target = client
        .target("http://localhost:8080/eelco/resources/brewer");
    

    final JsonObject recipe = aRecipeJson();
    
    Response changeTemperatureResponse = target.path("recipe").request()
        .post(Entity.entity(recipe, APPLICATION_JSON_TYPE));
    assertThat(changeTemperatureResponse.getStatus(), is(204));
  }

}
