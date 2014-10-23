
package nl.ordina.beer.brewing.entity;

import java.time.Duration;
import static java.time.temporal.ChronoUnit.MINUTES;
import java.util.Objects;
import nl.ordina.beer.entity.Kettle;

public class KeepTemperatureStable extends BrewAction {
    private final Duration duration;

    public KeepTemperatureStable(Duration duration) {
        this.duration = duration;
    }

    @Override
    public void executeFor(Kettle kettle) {
//        try {
            // Does nothing, like in real life brewing :)
//            Thread.sleep(duration.get(MINUTES) * 1000); // Speed up minutes to seconds
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
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
