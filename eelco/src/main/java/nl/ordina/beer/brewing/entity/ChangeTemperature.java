package nl.ordina.beer.brewing.entity;

import java.time.Duration;
import java.util.Objects;
import nl.ordina.beer.entity.Kettle;
import nl.ordina.beer.entity.Temperature;
import static nl.ordina.beer.entity.Temperature.TemperatureUnit.CELSIUS;

public class ChangeTemperature extends BrewAction {

    private static final int DEGREES_INCREMENT = 5;
    
    private final Temperature goal;
    private final Duration delay;

    public ChangeTemperature(Temperature temperature) {
        this(temperature, Duration.ofSeconds(1));
    }

    public ChangeTemperature(Temperature temperature, Duration delay) {
        this.goal = temperature;
        this.delay = delay;
    }

    @Override
    public void executeFor(Kettle kettle) {
        try {
            while (!kettle.isOnTemperature(goal)) {
                Thread.sleep(delay.getSeconds() * 1000); // Fake slow heating process
                kettle.changeTemperature(new Temperature(DEGREES_INCREMENT, CELSIUS), goal);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
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
        if (!Objects.equals(this.goal, other.goal)) {
            return false;
        }
        return true;
    }

}
