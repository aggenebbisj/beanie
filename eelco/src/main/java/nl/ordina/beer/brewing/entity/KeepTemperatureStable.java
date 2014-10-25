package nl.ordina.beer.brewing.entity;

import nl.ordina.beer.entity.Kettle;

import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import java.time.Duration;
import java.util.logging.Logger;

import static java.lang.String.format;

public class KeepTemperatureStable implements BrewAction {

    private final Duration duration;

    private transient Logger logger = Logger.getLogger(getClass().getName());

    private boolean completed;

    public KeepTemperatureStable(Duration duration) {
        this.duration = duration;
    }

    @Override
    public boolean isCompleted() {
        return completed;
    }

    @Override
    public void executeFor(Kettle kettle) {
        completed = false;
        try {
            // Does nothing, like in real life brewing :)
            logger.finest(() -> format("Waiting for %s minutes", duration.getSeconds() / 60));
            Thread.sleep(duration.getSeconds() / 60 * 1000); // Speed up minutes to seconds
            completed = true;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static class Encoder implements javax.websocket.Encoder.Text<KeepTemperatureStable> {

        @Override
        public String encode(final KeepTemperatureStable object) throws EncodeException {
            return Json.createObjectBuilder().add("event", "kitchentimer expired").build().toString();
        }

        @Override
        public void init(final EndpointConfig config) {
        }

        @Override
        public void destroy() {
        }
    }
}
