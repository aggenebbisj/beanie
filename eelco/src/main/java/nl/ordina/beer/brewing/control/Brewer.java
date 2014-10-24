package nl.ordina.beer.brewing.control;

import static java.lang.String.format;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import nl.ordina.beer.brewing.entity.BrewAction;
import nl.ordina.beer.brewing.entity.BrewActionCompletedEvent;
import nl.ordina.beer.entity.Kettle;

@ApplicationScoped
public class Brewer {

    private transient Logger logger = Logger.getLogger(getClass().getName());

    final Queue<BrewAction> queue = new ConcurrentLinkedQueue<>();

    @Inject
    private Kettle kettle;

    @Inject
    Event<BrewActionCompletedEvent> actionCompleted;

    public void addActions(List<BrewAction> steps) {
        queue.addAll(steps);
        executeNextAction();
    }

    public void addAction(BrewAction action) {
        logger.info(() -> "Brewer: queue size before adding: " + queue.size());
        if (queue.isEmpty()) {
            queue.add(action);
            executeNextAction();
        } else queue.add(action); 
    }

    public BrewAction nextAction() {
        return queue.peek();
    }

    public void executeNextAction() {
        if (!queue.isEmpty()) {
            executeAction(nextAction());
        }
    }

    private void executeAction(BrewAction action) {
        action.executeFor(kettle);
        logger.info(() -> format("Brewer: action completed. Remaining in queue %s", queue));
        actionCompleted.fire(new BrewActionCompletedEvent(action));
        if (action.isCompleted()) 
            queue.remove();
        executeNextAction();
    }

    public Kettle getKettle() {
        return kettle;
    }

}
