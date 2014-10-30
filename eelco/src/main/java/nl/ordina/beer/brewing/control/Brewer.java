package nl.ordina.beer.brewing.control;

import nl.ordina.beer.brewing.entity.BrewAction;
import nl.ordina.beer.entity.Kettle;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

import static java.lang.String.format;

/**
 * This is the actual brewer. The one that makes the beer.
 * 
 * @author Ordina J-Tech
 */
@ApplicationScoped
public class Brewer {

    @Inject
    private transient Logger logger;

    final Queue<BrewAction> queue = new ConcurrentLinkedQueue<>();

    @Inject
    private Kettle kettle;

    @Inject
    private Event<BrewAction> actionExecuted;

    public void addActions(List<BrewAction> steps) {
        queue.addAll(steps);
        executeNextAction();
    }

    public void addAction(BrewAction action) {
        logger.finest(() -> "Brewer: queue size before adding: " + queue.size());
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
        logger.finest(() -> format("Brewer: action completed. Remaining in queue %s", queue));
        actionExecuted.fire(action);
        if (action.isCompleted()) 
            queue.remove();
        executeNextAction();
    }

    public Kettle getKettle() {
        return kettle;
    }

}
