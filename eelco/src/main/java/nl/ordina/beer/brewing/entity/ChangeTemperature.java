package nl.ordina.beer.brewing.entity;

import java.time.Duration;
import java.util.Objects;
import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import nl.ordina.beer.entity.Kettle;
import nl.ordina.beer.entity.Temperature;
import static nl.ordina.beer.entity.Temperature.TemperatureUnit.CELSIUS;

public class ChangeTemperature implements BrewAction {

    private static final int DEGREES_INCREMENT = 5;

    private Temperature current;
    private final Temperature goal;
    private final Duration delay;

    public ChangeTemperature(Temperature goal) {
        this(goal, Duration.ofSeconds(1));
    }

    public ChangeTemperature(Temperature goal, Duration delay) {
        this.goal = goal;
        this.delay = delay;
    }

    @Override
    public void executeFor(Kettle kettle) {
        try {
            Thread.sleep(delay.getSeconds() * 1000); // Fake slow heating process
            kettle.changeTemperature(new Temperature(DEGREES_INCREMENT, CELSIUS), goal);
            current = kettle.getTemperature();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isCompleted() {
        return current.equals(goal);
    }

    public static class Encoder implements javax.websocket.Encoder.Text<ChangeTemperature> {

        @Override
        public void init(final EndpointConfig config) {
        }

        @Override
        public void destroy() {
        }

        @Override
        public String encode(final ChangeTemperature ct) throws EncodeException {
            return Json.createObjectBuilder()
                        .add("event", "temperature changed")
                        .add("temperature", Json.createObjectBuilder()
                                .add("scale", ct.current.getUnit().name())
                                .add("value", ct.current.getValue())
                                .build())
                        .build().toString();
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
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
        if (!Objects.equals(this.current, other.current)) {
            return false;
        }
        if (!Objects.equals(this.goal, other.goal)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ChangeTemperature{" + "current=" + current + ", goal=" + goal + '}';
    }
    
}
