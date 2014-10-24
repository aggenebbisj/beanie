package nl.ordina.beer.brewing.entity;

import nl.ordina.beer.entity.Kettle;
import nl.ordina.beer.entity.Temperature;

import javax.json.Json;
import javax.json.JsonObject;
import java.time.Duration;
import java.util.Objects;
import java.util.logging.Logger;

import static java.lang.String.format;
import static nl.ordina.beer.entity.Temperature.TemperatureUnit.CELSIUS;

public class ChangeTemperature extends BrewAction {

    private static final int DEGREES_INCREMENT = 5;

    private final Temperature goal;

    private final Duration delay;

    private transient Logger logger = Logger.getLogger(getClass().getName());

    private Temperature current;

    public ChangeTemperature(Temperature goal) {
        this(goal, Duration.ofSeconds(1));
    }

    public ChangeTemperature(Temperature goal, Duration delay) {
        this.goal = goal;
        this.delay = delay;
    }

    @Override
    public boolean isCompleted() {
        return current.equals(goal);
    }

    @Override
    public void executeFor(Kettle kettle) {
        try {
            logger.finest(() -> format("Faking slow heating process, waiting %s seconds", delay.getSeconds()));
            Thread.sleep(delay.getSeconds() * 1000); // Fake slow heating process
            kettle.changeTemperature(new Temperature(DEGREES_INCREMENT, CELSIUS), goal);
            current = kettle.getTemperature();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonObject toJson() {
        return Json.createObjectBuilder().add("event", "temperature changed").add("temperature",
                                                                                  Json.createObjectBuilder()
                                                                                      .add("scale",
                                                                                           current.getUnit().name())
                                                                                      .add("value", current.getValue())
                                                                                      .build()).build();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ChangeTemperature other = (ChangeTemperature) obj;
        if (!Objects.equals(this.goal, other.goal)) {
            return false;
        }
        return true;
    }

}
