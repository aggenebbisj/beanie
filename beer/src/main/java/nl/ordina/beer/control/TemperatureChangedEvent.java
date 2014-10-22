package nl.ordina.beer.control;

import java.util.Objects;
import javax.json.Json;
import javax.json.JsonObject;
import nl.ordina.beer.entity.Kettle;
import nl.ordina.beer.entity.Temperature;

public class TemperatureChangedEvent implements NotificationEvent, KettleActionEvent {

    private final Temperature goal;
    private final Kettle kettle;

    public TemperatureChangedEvent(Temperature goal, Kettle kettle) {
        this.goal = goal;
        this.kettle = kettle;
    }

    public Temperature getGoal() {
        return goal;
    }

    @Override
    public boolean isCompleted() {
        return kettle.getTemperature().equals(goal);
    }

    @Override
    public Kettle getKettle() {
        return kettle;
    }

    @Override
    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("event", "temperature changed")
                .add("temperature",
                        Json.createObjectBuilder()
                        .add("scale", kettle.getTemperature().getUnit().name())
                        .add("value", kettle.getTemperature().getValue())
                        .build())
                .build();
    }

    @Override
    public String toString() {
        return "TemperatureChangedEvent{" + "goal=" + goal + ", kettle=" + kettle + '}';
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
        final TemperatureChangedEvent other = (TemperatureChangedEvent) obj;
        if (!Objects.equals(this.goal, other.goal)) {
            return false;
        }
        if (!Objects.equals(this.kettle, other.kettle)) {
            return false;
        }
        return true;
    }

}
