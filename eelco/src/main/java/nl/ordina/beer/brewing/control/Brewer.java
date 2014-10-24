package nl.ordina.beer.brewing.control;

import static java.lang.String.format;
import java.util.List;
import nl.ordina.beer.brewing.entity.BrewAction;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
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

    @Inject
    private Kettle kettle;

    @Inject
    Event<BrewActionCompletedEvent> actionCompleted;

    @Inject
    Event<BrewActionAddedEvent> actionAdded;
    
    public void addActions(List<BrewAction> steps) {
        steps.stream().forEach((action) -> {
            queue.add(action);
        });
    }

    public void addAction(BrewAction action) {
        logger.info(() -> "Brewer: queue size before adding: " + queue.size());
        queue.add(action);
        //TODO: Blocking call, queue is redundant, or make nonblocking (asynchroon) MDB, Queue Observer......
        if ((action.equals(nextAction()))) executeNextAction();
    }

    public void onActionCompleted(@Observes BrewActionCompletedEvent actionCompleted) {
        if (actionCompleted.getAction().isCompleted(kettle)) {
            queue.remove();
            logger.info(() -> "Brewer: Action completed. Current queue size: " + queue.size());
            executeNextAction();
        }
        else executeAction(actionCompleted.getAction());
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
        action.brew(kettle);
        //TODO: Move into the brew method, then the action raises the action complete event
        logger.info(() -> format("Brewer: action completed. Remaining in queue %s", queue));
        actionCompleted.fire(new BrewActionCompletedEvent(action));
    }

    public Kettle getKettle() {
        return kettle;
    }

}
