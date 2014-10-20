package nl.ordina.brewery.entity.waiting;

import nl.ordina.brewery.entity.Kettle;
import nl.ordina.brewery.entity.KettleAction;

import java.time.Duration;

public class WaitAction implements KettleAction {
    
    private Duration duration;

    public WaitAction() {
        // Required by JAXB
    }
    
    public WaitAction(Duration duration) {
        this.duration = duration;
    }

    @Override
    public void executeFor(Kettle kettle) {
        kettle.lock(duration);
    }

    @Override
    public String toString() {
        return "WaitAction{" + "duration=" + duration + '}';
    }
    
}
