package nl.ordina.beer.brewing.control;

import nl.ordina.beer.brewing.entity.BrewAction;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import nl.ordina.beer.brewing.entity.BrewActionCompletedEvent;
import nl.ordina.beer.entity.Kettle;

public class Brewer {

    private final Queue<BrewAction> queue = new ConcurrentLinkedQueue<>();

    @Inject
    private Kettle kettle;

    @Inject
    Event<BrewActionCompletedEvent> event;

    @PostConstruct
    public void init() {
        while (true) {
            executeNextAction();
        }
    }

    public void addAction(BrewAction action) {
        queue.add(action);
    }

    public BrewAction nextAction() {
        return queue.remove();
    }

    public void executeNextAction() {
        if (!queue.isEmpty()) {
            final BrewAction nextAction = nextAction();
            nextAction.brew(kettle);
            event.fire(new BrewActionCompletedEvent(nextAction));
        }
    }

    public Kettle getKettle() {
        return kettle;
    }

}
