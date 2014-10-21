package nl.ordina.beer.control;

import java.io.Serializable;
import java.util.logging.Level;
import javax.annotation.Resource;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import nl.ordina.beer.entity.Kettle;
import nl.ordina.beer.entity.Temperature;
import static nl.ordina.beer.entity.Temperature.TemperatureUnit.CELSIUS;

@Stateless
public class TemperatureController {

    private static final Logger log = Logger.getLogger(TemperatureController.class.getName());
    private static final Temperature INCREMENT = new Temperature(5, CELSIUS);
    
    @Resource
    TimerService timerService;

    @Inject
    private Event<TemperatureChangedEvent> event;

    public void changeTemperature(Temperature goal, Kettle kettle) {
        log.info(() -> "Changing temperature to " + goal);
        final TemperatureChangedEvent tcEvent = new TemperatureChangedEvent(goal, kettle);
        
        kettle.changeTemperatureToWithSteps(goal, INCREMENT);
        
        event.fire(tcEvent);
        
        if (!kettle.getTemperature().equals(goal)) {
            final TimerConfig timerConfig = new TimerConfig(
                    new Holder(tcEvent),
                    false
            );
            timerService.createSingleActionTimer(1000, timerConfig);
        }
    }
    
    @Timeout
    public void timeout(Timer timer) {
        final Holder holder = (Holder) timer.getInfo();
        log.log(Level.INFO, "Timeout for event {0}", holder.getEvent());
        changeTemperature(holder.getEvent().getGoal(), holder.getEvent().getKettle());
    }

}

class Holder implements Serializable {

    private final TemperatureChangedEvent event;

    Holder(TemperatureChangedEvent event) {
        this.event = event;
    }

    public TemperatureChangedEvent getEvent() {
        return event;
    }

    @Override
    public String toString() {
        return "Holder{" + "event=" + event + '}';
    }

}
