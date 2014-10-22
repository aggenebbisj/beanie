
package nl.ordina.beer.automaticbrewing.control;

import java.time.Duration;
import java.util.Objects;
import nl.ordina.beer.manualbrewing.control.Brewer;

public class KeepTemperatureStable implements RecipeStep<Duration>{
    private final Duration duration;

    public KeepTemperatureStable(Duration duration) {
        this.duration = duration;
    }

    @Override
    public void executeStep(Brewer brewer) {
        brewer.lockKettle(duration);
    }

    @Override
    public String toString() {
        return "KeepTemperatureStable{" + "duration=" + duration + '}';
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
