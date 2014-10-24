
package nl.ordina.beer.brewing.entity;

import java.util.Objects;
import javax.json.JsonObject;

public class BrewActionCompletedEvent {
    private final BrewAction action;

    public BrewActionCompletedEvent(BrewAction action) {
        this.action = action;
    }

    public BrewAction getAction() {
        return action;
    }
    
    public JsonObject toJson() {
        return action.toJson();
    }
    
    @Override
    public String toString() {
        return "BrewActionCompletedEvent{" + "action=" + action + '}';
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
