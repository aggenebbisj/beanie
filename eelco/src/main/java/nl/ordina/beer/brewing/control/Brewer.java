package nl.ordina.beer.brewing.control;

import static java.lang.String.format;
import java.util.List;
import nl.ordina.beer.brewing.entity.BrewAction;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import nl.ordina.beer.brewing.entity.BrewActionAddedEvent;
import nl.ordina.beer.brewing.entity.BrewActionCompletedEvent;
import nl.ordina.beer.entity.Kettle;

@ApplicationScoped
public class Brewer {
    private transient Logger logger = Logger.getLogger(getClass().getName());
    
    private final Queue<BrewAction> queue = new ConcurrentLinkedQueue<>();

    @Inject @Dependent
    private Kettle kettle;

    @Inject
    Event<BrewActionCompletedEvent> actionCompleted;

    @Inject
    Event<BrewActionAddedEvent> actionAdded;
    
    public void addActions(List<BrewAction> steps) {
        queue.addAll(steps);
        actionAdded.fire(new BrewActionAddedEvent());
    }
    
    public void addAction(BrewAction action) {
        queue.add(action);
        actionAdded.fire(new BrewActionAddedEvent());
    }

    public void onActionAdded(@Observes BrewActionAddedEvent actionAdded) {
        executeNextAction();
    }
    
    public void onActionCompleted(@Observes BrewActionCompletedEvent actionCompleted) {
        executeNextAction();
    }
    
    public BrewAction nextAction() {
        return queue.remove();
    }
    
    public void executeNextAction() {
        if (!queue.isEmpty()) {
            final BrewAction nextAction = nextAction();
            nextAction.brew(kettle);
            logger.info(() -> format("Brewer: action completed. Remaining in queue %s", queue));
            actionCompleted.fire(new BrewActionCompletedEvent(nextAction));
        }
    }

    public Kettle getKettle() {
        return kettle;
    }

}
