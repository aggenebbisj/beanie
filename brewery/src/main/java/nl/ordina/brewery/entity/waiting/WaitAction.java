package nl.ordina.brewery.entity.waiting;

import nl.ordina.brewery.entity.Kettle;
import nl.ordina.brewery.entity.KettleAction;

import java.time.Duration;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import nl.ordina.brewery.entity.MonitoringEvent;

public class WaitAction implements KettleAction {

    @Inject
    private Event<MonitoringEvent> event;

    private final Duration duration;

    public WaitAction(Duration duration) {
        this.duration = duration;
    }

    @Override
    public void executeFor(Kettle kettle) {
        kettle.lock(duration);
    }

    @Override
    public String toString() {
        return "WaitAction{" + "event=" + event + ", duration=" + duration + '}';
    }
    
}
