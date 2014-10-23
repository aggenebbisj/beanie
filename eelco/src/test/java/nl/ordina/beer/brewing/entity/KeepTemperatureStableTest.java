package nl.ordina.beer.brewing.entity;

import java.time.Duration;
import javax.json.Json;
import javax.json.JsonObject;
import static nl.ordina.beer.entity.EntityBuilder.defaultDuration;
import static nl.ordina.beer.entity.EntityBuilder.defaultTemperature;
import nl.ordina.beer.entity.Kettle;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class KeepTemperatureStableTest {

    private KeepTemperatureStable sut = new KeepTemperatureStable(Duration.ZERO);

    @Test
    public void test_json_marshalling() {
        JsonObject expected = Json.createObjectBuilder()
                .add("event", "kitchentimer expired")
                .build();
        assertThat(sut.toJson(), is(expected));
    }
}
