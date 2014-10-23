
package nl.ordina.beer.brewing.entity;

import java.util.Objects;

public class BrewActionCompletedEvent {
    private final BrewAction action;

    public BrewActionCompletedEvent(BrewAction action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "BrewActionCompletedEvent{" + "action=" + action + '}';
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
        final BrewActionCompletedEvent other = (BrewActionCompletedEvent) obj;
        if (!Objects.equals(this.action, other.action)) {
            return false;
        }
        return true;
    }
    
}
