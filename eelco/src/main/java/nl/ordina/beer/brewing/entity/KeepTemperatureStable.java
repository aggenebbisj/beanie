package nl.ordina.beer.brewing.entity;

import nl.ordina.beer.entity.Kettle;

import javax.json.Json;
import javax.json.JsonObject;
import java.time.Duration;
import java.util.Objects;
import java.util.logging.Logger;

import static java.lang.String.format;

public class KeepTemperatureStable extends BrewAction {

    private final Duration duration;

    private transient Logger logger = Logger.getLogger(getClass().getName());

    public KeepTemperatureStable(Duration duration) {
        this.duration = duration;
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
    public JsonObject toJson() {
        return Json.createObjectBuilder().add("event", "kitchentimer expired").build();
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

    @Override
    public String toString() {
        return "KeepTemperatureStable{duration=" + duration + '}';
    }

}
