
package nl.ordina.beer.control;

import java.util.Objects;
import javax.json.Json;
import javax.json.JsonObject;
import nl.ordina.beer.entity.Kettle;

public class KitchenTimerExpiredEvent implements NotificationEvent, KettleActionEvent {
    private final Kettle kettle;

    public KitchenTimerExpiredEvent(Kettle kettle) {
        this.kettle = kettle;
    }

    @Override
    public boolean isCompleted() {
        return true;
    }
    
    @Override
    public Kettle getKettle() {
        return kettle;
    }
    
    @Override
    public JsonObject toJson() {
        return Json.createObjectBuilder()
        .add("event", "kitchentimer expired")
        .add("kettle", kettle.getName())
        .build();
    }

    @Override
    public String toString() {
        return "KitchenTimerExpiredEvent{" + "kettle=" + kettle + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final KitchenTimerExpiredEvent other = (KitchenTimerExpiredEvent) obj;
        if (!Objects.equals(this.kettle, other.kettle)) {
            return false;
        }
        return true;
    }
    
}
