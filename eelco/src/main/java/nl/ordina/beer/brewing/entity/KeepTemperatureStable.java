
package nl.ordina.beer.brewing.entity;

import static java.lang.String.format;
import java.time.Duration;
import java.util.Objects;
import java.util.logging.Logger;
import nl.ordina.beer.entity.Kettle;

public class KeepTemperatureStable extends BrewAction {
    private transient Logger logger = Logger.getLogger(getClass().getName());
    
    private final Duration duration;

    public KeepTemperatureStable(Duration duration) {
        this.duration = duration;
    }

    @Override
    public void executeFor(Kettle kettle) {
        try {
            // Does nothing, like in real life brewing :)
            logger.info(() -> format("Waiting for %s minutes", duration.getSeconds() / 60));
            Thread.sleep(duration.getSeconds() / 60 * 1000); // Speed up minutes to seconds
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "KeepTemperatureStable{" + "logger=" + logger + ", duration=" + duration + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final KeepTemperatureStable other = (KeepTemperatureStable) obj;
        if (!Objects.equals(this.duration, other.duration)) {
            return false;
        }
        return true;
    }

}
