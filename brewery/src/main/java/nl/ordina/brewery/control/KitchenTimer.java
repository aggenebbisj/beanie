package nl.ordina.brewery.control;

import java.io.Serializable;
import nl.ordina.brewery.entity.waiting.WaitEvent;
import nl.ordina.brewery.entity.waiting.WaitCompletedEvent;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.lang.invoke.MethodHandles;
import java.time.Duration;
import java.util.logging.Logger;

import static java.time.temporal.ChronoUnit.SECONDS;
import static java.util.logging.Level.FINEST;
import static java.util.logging.Level.INFO;
import javax.ejb.Timer;
import nl.ordina.brewery.entity.Kettle;

@Stateless
public class KitchenTimer {

    private static final Logger log = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    @Resource
    TimerService timerService;

    private static final Duration REAL_DURATION = Duration.of(5, SECONDS);

    public void keepStableTemperature(@Observes WaitEvent event) {
        final Duration duration = event.getDuration();
        log.log(INFO, "Should wait for {0}, but will wait {1}", new Object[]{duration, REAL_DURATION});
        timerService.createSingleActionTimer(REAL_DURATION.getSeconds() * 1000, new TimerConfig(new Holder(event.getKettle()), false));
    }

    @Timeout
    public void timeout(Timer timer) {
        log.log(FINEST, "Timeout reached");
        final Holder holder = (Holder) timer.getInfo();
        holder.kettle.fireWaitCompletedEvent();
    }

    class Holder implements Serializable {

        private final Kettle kettle;

        public Holder(Kettle kettle) {
            this.kettle = kettle;
        }

        @Override
        public String toString() {
            return "Holder{" + "kettle=" + kettle + '}';
        }
        
    }
}