package nl.ordina.brewery.recipe.boundary;

import nl.ordina.brewery.entity.RecipeBuilder;
import org.glassfish.jersey.jsonp.JsonProcessingFeature;
import org.junit.Before;
import org.junit.Test;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.MINUTES;
import static javax.json.Json.createArrayBuilder;
import static javax.json.Json.createObjectBuilder;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static nl.ordina.brewery.entity.RecipeBuilder.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BrewerResourceIT {

  private Client client;

  @Before
  public void setUp() throws Exception {
    client = ClientBuilder.newClient()
        .register(JsonProcessingFeature.class);
  }

  @Test
  public void test() {
    final WebTarget target = client
        .target("http://localhost:8080/brewery-1.0-SNAPSHOT/resources/brewer");

    final JsonArrayBuilder actions = createArrayBuilder()
        .add(createAddIngredientAction(createIngredient("water", 10)))
        .add(RecipeBuilder.createChangeTemperature(65))
        .add(createAddIngredientAction(createIngredient("malt", 1)))
        .add(RecipeBuilder.createStableTemperature(Duration.of(30, MINUTES)));

    final JsonObject recipe = createObjectBuilder()
        .add("steps", createArrayBuilder().add(createStep("Mashing", actions)))
        .build();

    Response changeTemperatureResponse = target.path("recipe").request()
        .post(Entity.entity(recipe, APPLICATION_JSON_TYPE));
    assertThat(changeTemperatureResponse.getStatus(), is(204));
  }

}
