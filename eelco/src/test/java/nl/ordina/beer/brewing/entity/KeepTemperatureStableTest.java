package nl.ordina.beer.brewing.entity;

import java.time.Duration;
import javax.json.Json;
import javax.websocket.EncodeException;
import nl.ordina.beer.brewing.entity.KeepTemperatureStable.Encoder;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class KeepTemperatureStableTest {

    private KeepTemperatureStable sut = new KeepTemperatureStable(Duration.ZERO);

    @Test
    public void test_json_marshalling() throws EncodeException {
        String expected = Json.createObjectBuilder()
                .add("event", "kitchentimer expired").build().toString();
        assertThat(new Encoder().encode(sut), is(expected));
    }
}
