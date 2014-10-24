package nl.ordina.beer.brewing.entity;

import org.junit.Test;

import javax.json.Json;
import javax.json.JsonObject;
import java.time.Duration;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class KeepTemperatureStableTest {

    private KeepTemperatureStable sut = new KeepTemperatureStable(Duration.ZERO);

    @Test
    public void test_json_marshalling() {
        JsonObject expected = Json.createObjectBuilder().add("event", "kitchentimer expired").build();
        assertThat(sut.toJson(), is(expected));
    }
}
