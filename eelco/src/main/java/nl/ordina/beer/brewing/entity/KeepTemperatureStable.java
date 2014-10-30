package nl.ordina.beer.brewing.entity;

import static java.lang.String.format;
import java.time.Duration;
import java.util.Objects;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import nl.ordina.beer.entity.Kettle;

public class KeepTemperatureStable extends BrewAction {

    @Inject
    private transient Logger logger;

    private final Duration duration;

    public KeepTemperatureStable(Duration duration) {
        this.duration = duration;
    }

    @Override
    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("event", "kitchentimer expired")
                .build();
    }

    @Override
    public void executeFor(Kettle kettle) {
        try {
            // Does nothing, like in real life brewing :)
            logger.finest(() -> format("Waiting for %s minutes", duration.getSeconds() / 60));
            Thread.sleep(duration.getSeconds() / 60 * 1000); // Speed up minutes to seconds
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "KeepTemperatureStable{duration=" + duration + '}';
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final KeepTemperatureStable other = (KeepTemperatureStable) obj;
        if (!Objects.equals(this.duration, other.duration)) {
            return false;
        }
        return true;
    }

}
