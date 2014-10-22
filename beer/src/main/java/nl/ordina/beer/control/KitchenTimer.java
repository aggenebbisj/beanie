package nl.ordina.beer.control;

import java.io.Serializable;
import java.time.Duration;
import static java.time.temporal.ChronoUnit.SECONDS;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import nl.ordina.beer.entity.Kettle;

@Stateless
public class KitchenTimer {

    private static final Logger log = Logger.getLogger(TemperatureController.class.getName());

    @Resource
    TimerService timerService;

    @Inject
    private Event<KitchenTimerExpiredEvent> event;
    
    public void setFor(Duration of, Kettle kettle) {
        log.info(() -> "Waiting 5 seconds before removing lock...");
        // Always wait for 5 seconds
        timerService.createSingleActionTimer(Duration.of(5, SECONDS).getSeconds() * 1000, new TimerConfig(new Holder(kettle), false));
    }

    @Timeout
    public void timeout(Timer timer) {
        final Holder holder = (Holder) timer.getInfo();
        log.info(() -> "Unlocking kettle " + holder.getKettle());
        holder.getKettle().unlock();
        event.fire(new KitchenTimerExpiredEvent(holder.getKettle()));
    }
    
    static class Holder implements Serializable {

        private final Kettle kettle;

        Holder(Kettle kettle) {
            this.kettle = kettle;
        }

        public Kettle getKettle() {
            return kettle;
        }

        @Override
        public String toString() {
            return "Holder{" + "kettle=" + kettle + '}';
        }

    }

}
