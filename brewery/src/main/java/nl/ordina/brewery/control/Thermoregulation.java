package nl.ordina.brewery.control;

import nl.ordina.brewery.entity.temperature.Temperature;
import nl.ordina.brewery.entity.temperature.TemperatureChangingEvent;
import nl.ordina.brewery.entity.temperature.TemperatureReachedEvent;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.ordina.brewery.entity.Kettle;

@Stateless
public class Thermoregulation {

    private static final Logger log = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());
    
    @Resource
    TimerService timerService;
    
    @Inject
    Event<TemperatureReachedEvent> changedEvent;
    @Inject
    Event<TemperatureChangingEvent> changingEvent;
    
    public void changeTemperature(@Observes TemperatureChangingEvent event) {
        handleChange(event);
    }
    
    @Timeout
    public void timeout(Timer timer) {
        final Holder holder = (Holder) timer.getInfo();
        TemperatureChangingEvent event = holder.getEvent();
        
        log.log(Level.FINEST, "Timeout for event {0}  with new temperature {1}", new Object[]{event, holder.getNewTemperature()});
        
        event.getKettle().changeInternalTemperature(holder.getNewTemperature());

//    handleChange(event);
        TemperatureChangeCalculator calculator = createCalculator(event);
        
        if (calculator.isEqual()) {
            fireChanged(event.getGoal(), event.getKettle());
        } else {
            timerService.createSingleActionTimer(1000, new TimerConfig(new Holder(event, calculator.calculateNewTemperature()), false));
        }
        
    }
    
    private TemperatureChangeCalculator createCalculator(TemperatureChangingEvent event) {
        return new TemperatureChangeCalculator(event.getKettle().getTemperature(), event.getGoal());
    }
    
    private void handleChange(TemperatureChangingEvent event) {
        TemperatureChangeCalculator calculator = createCalculator(event);
        
        if (calculator.isEqual()) {
            fireChanged(event.getGoal(), event.getKettle());
        } else {
            timerService.createSingleActionTimer(1000, new TimerConfig(new Holder(event, calculator.calculateNewTemperature()), false));
        }
    }
    
    private void fireChanged(Temperature newTemperature, Kettle kettle) {
        kettle.fireTemperatureReachedEvent(newTemperature);
    }
    
}

class Holder implements Serializable {
    
    private final TemperatureChangingEvent event;
    private final Temperature newTemperature;
    
    Holder(TemperatureChangingEvent event, Temperature newTemperature) {
        this.event = event;
        this.newTemperature = newTemperature;
    }
    
    public TemperatureChangingEvent getEvent() {
        return event;
    }
    
    public Temperature getNewTemperature() {
        return newTemperature;
    }
    
    @Override
    public String toString() {
        return "ChangeHolder{"
                + "event=" + event
                + ", newTemperature=" + newTemperature
                + '}';
    }
}
