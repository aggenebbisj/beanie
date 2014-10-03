package nl.ordina.brewery.business.brewing.entity;

import org.glassfish.jersey.jsonp.JsonProcessingFeature;
import org.junit.Before;
import org.junit.Test;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
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
        .add(
            createObjectBuilder()
                .add("type", "AddIngredient")
                .add("ingredient", createIngredient("water", 10)))
        .add(createObjectBuilder()
            .add("type", "ChangeTemperature")
            .add("value", 65)
            .add("unit", "CELSIUS"))
        .add(createObjectBuilder()
            .add("type", "AddIngredient")
            .add("ingredient", createIngredient("malt", 1)))
        .add(createObjectBuilder()
            .add("type", "StableTemperature")
            .add("duration", Duration.of(30, MINUTES).toString()));

    final JsonObject recipe = createObjectBuilder()
        .add("steps",
            createArrayBuilder()
                .add(
                    createObjectBuilder()
                        .add("name", "Mashing")
                        .add("actions", actions)))
        .build();

    Response changeTemperatureResponse = target.path("recipe").request()
        .post(Entity.entity(recipe, APPLICATION_JSON_TYPE));
    assertThat(changeTemperatureResponse.getStatus(), is(204));
  }

  private JsonObjectBuilder createIngredient(String type, int amount) {
    return createObjectBuilder()
        .add("type", type)
        .add("volume", createObjectBuilder()
            .add("amount", amount)
            .add("unit", "LITER"));
  }

}
