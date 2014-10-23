package nl.ordina.beer.brewing.control;

import java.util.List;
import nl.ordina.beer.brewing.entity.BrewAction;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import nl.ordina.beer.brewing.entity.BrewActionAdded;
import nl.ordina.beer.brewing.entity.BrewActionCompletedEvent;
import nl.ordina.beer.entity.Kettle;

@ApplicationScoped
public class Brewer {

    private final Queue<BrewAction> queue = new ConcurrentLinkedQueue<>();

    @Inject
    private Kettle kettle;

    @Inject
    Event<BrewActionCompletedEvent> actionCompleted;

    @Inject
    Event<BrewActionAdded> actionAdded;
    
    public void addActions(List<BrewAction> steps) {
        queue.addAll(steps);
        actionAdded.fire(new BrewActionAdded());
    }
    
    public void addAction(BrewAction action) {
        queue.add(action);
        actionAdded.fire(new BrewActionAdded());
    }

    public void onActionAdded(@Observes BrewActionAdded actionAdded) {
        executeNextAction();
    }
    
    public BrewAction nextAction() {
        return queue.remove();
    }
    
    public void executeNextAction() {
        if (!queue.isEmpty()) {
            final BrewAction nextAction = nextAction();
            nextAction.brew(kettle);
            actionCompleted.fire(new BrewActionCompletedEvent(nextAction));
        }
    }

    public Kettle getKettle() {
        return kettle;
    }

}
