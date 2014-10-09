package nl.ordina.brewery.entity;

import nl.ordina.brewery.entity.event.KitchenTimerEvent;
import nl.ordina.brewery.entity.event.TimerExpiredEvent;

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

@Stateless
public class KitchenTimer {
  private static final Logger log = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

  @Resource
  TimerService timerService;

  @Inject
  Event<TimerExpiredEvent> bus;
  private static final Duration REAL_DURATION = Duration.of(5, SECONDS);

  public void keepStableTemperature(@Observes KitchenTimerEvent event) {
    final Duration duration = event.getDuration();
    log.log(INFO, "Should wait for {0}, but will wait {1}", new Object[] {duration, REAL_DURATION});
    timerService.createSingleActionTimer(REAL_DURATION.getSeconds() * 1000, new TimerConfig());
  }

  @Timeout
  public void timeout() {
    log.log(FINEST, "Timeout reached");
    bus.fire(new TimerExpiredEvent());
  }


}
