package nl.ordina.brewery.business.brewing.boundary;

import nl.ordina.brewery.business.brewing.entity.Temperature;
import nl.ordina.brewery.business.brewing.entity.event.TemperatureChangedEvent;
import nl.ordina.brewery.business.brewing.entity.event.TemperatureChangingEvent;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class TimedTemperatureChange {
  private static final Logger log = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

  public static final int MAXIMUM_TEMPERATURE_CHANGE = 25;
  @Resource
  TimerService timerService;

  @Inject
  Event<TemperatureChangedEvent> changedEvent;
  @Inject
  Event<TemperatureChangingEvent> changingEvent;

  public void changeTemperature(@Observes TemperatureChangingEvent event) {
    final Temperature difference = event.getKettle().getTemperature().difference(event.getGoal());
    Temperature change = calculateChangePerTimeslot(difference);

    timerService.createSingleActionTimer(1000, new TimerConfig(new ChangeHolder(event, change), false));
  }

  private Temperature calculateChangePerTimeslot(Temperature difference) {
    return difference.getValue() > MAXIMUM_TEMPERATURE_CHANGE ? new Temperature(MAXIMUM_TEMPERATURE_CHANGE, difference.getUnit()) : difference;
  }

  @Timeout
  public void timeout(Timer timer) {
    final ChangeHolder holder = (ChangeHolder) timer.getInfo();
    TemperatureChangingEvent event = holder.getEvent();

    log.log(Level.FINER, "Timeout for event {0}  with change {1}", new Object[] {event, holder.getChange()} );

    Temperature newTemperature = event.getKettle().getTemperature().plus(holder.getChange());
    event.getKettle().changeInternalTemperature(newTemperature);

    final Temperature difference = newTemperature.difference(event.getGoal());
    if (difference.getValue() != 0) {
      Temperature change = calculateChangePerTimeslot(difference);
      timerService.createSingleActionTimer(1000, new TimerConfig(new ChangeHolder(event, change), false));
    }
    else {
      // why does the kettle not react to the changed event????
      changedEvent.fire(new TemperatureChangedEvent(newTemperature));

    }
  }


}

class ChangeHolder implements Serializable {
  private final TemperatureChangingEvent event;
  private final Temperature change;

  ChangeHolder(TemperatureChangingEvent event, Temperature change) {
    this.event = event;
    this.change = change;
  }

  public TemperatureChangingEvent getEvent() {
    return event;
  }

  public Temperature getChange() {
    return change;
  }

  @Override
  public String toString() {
    return "ChangeHolder{" +
        "event=" + event +
        ", change=" + change +
        '}';
  }
}

