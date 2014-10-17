package nl.ordina.brewery.entity.waiting;

import nl.ordina.brewery.entity.MonitoredEvent;

import javax.json.Json;
import javax.json.JsonObject;
import java.time.Duration;
import nl.ordina.brewery.entity.Kettle;

public class WaitEvent implements MonitoredEvent {

    private final Duration duration;
    private final Kettle kettle;

    public WaitEvent(Duration duration, Kettle kettle) {
        this.duration = duration;
        this.kettle = kettle;
    }

    public Duration getDuration() {
        return duration;
    }
    
    public Kettle getKettle() {
        return kettle;
    }

    @Override
    public JsonObject createJson() {
        return Json.createObjectBuilder()
                .add("event", "kitchen timer started")
                .add("duration", duration.toString())
                .build();
    }

    @Override
    public String toString() {
        return "WaitEvent{" + "duration=" + duration + ", kettle=" + kettle + '}';
    }

}
