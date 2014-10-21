package nl.ordina.brewery.manual.boundary;

import org.glassfish.jersey.jsonp.JsonProcessingFeature;
import org.junit.Before;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.time.Duration;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class KettleResourceIT2 {

  private Client client;

  @Before
  public void setUp() throws Exception {
    client = ClientBuilder.newClient()
        .register(JsonProcessingFeature.class);
  }

  @Test
  public void test() {
    final WebTarget target = client
        .target("http://localhost:8080/brewery-1.0-SNAPSHOT/resources/kettle");

    final JsonObject goal = Json.createObjectBuilder()
        .add("goal", Json.createObjectBuilder()
            .add("value", 65)
            .add("unit", "CELSIUS")
            .build())
        .build();

    Response changeTemperatureResponse = target.path("temperature").request()
        .post(Entity.entity(goal, APPLICATION_JSON_TYPE));
    assertThat(changeTemperatureResponse.getStatus(), is(204));


    Response waterResponse = target.path("ingredient").request()
        .put(Entity.entity(create("water", 20), APPLICATION_JSON_TYPE));
    assertThat(waterResponse.getStatus(), is(204));

    Response maltResponse = target.path("ingredient").request()
        .put(Entity.entity(create("malt", 1), APPLICATION_JSON_TYPE));
    assertThat(maltResponse.getStatus(), is(204));

    final JsonObject steadyTemperature = Json.createObjectBuilder()
        .add("duration", Duration.ofMinutes(30).toString())
        .build();
    Response steadyTemperatureResponse = target.path("temperature").request()
        .post(Entity.entity(steadyTemperature, APPLICATION_JSON_TYPE));
    assertThat(steadyTemperatureResponse.getStatus(), is(204));


  }

  private JsonObject create(String type, int amount) {
    return Json.createObjectBuilder()
          .add("type", type)
          .add("volume",
              Json.createObjectBuilder()
                  .add("amount", amount)
                  .add("unit", "LITER")
                  .build())
          .build();
  }
}
