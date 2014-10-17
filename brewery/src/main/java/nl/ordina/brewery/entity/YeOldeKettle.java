package nl.ordina.brewery.entity;

import java.time.Duration;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import nl.ordina.brewery.entity.capacity.Volume;
import nl.ordina.brewery.entity.ingredient.Ingredient;
import nl.ordina.brewery.entity.ingredient.IngredientAddedEvent;
import nl.ordina.brewery.entity.producer.Manual;
import nl.ordina.brewery.entity.temperature.Temperature;
import nl.ordina.brewery.entity.temperature.TemperatureChangingEvent;
import nl.ordina.brewery.entity.temperature.TemperatureReachedEvent;
import nl.ordina.brewery.entity.temperature.TemperatureReadingEvent;
import nl.ordina.brewery.entity.waiting.WaitCompletedEvent;
import nl.ordina.brewery.entity.waiting.WaitEvent;

@Manual
public class YeOldeKettle extends Kettle {

    @Inject
    private Event<MonitoredEvent> event;

    public YeOldeKettle() {
        this("Ye Olde Kettle", new Volume(500, Volume.VolumeUnit.LITER));
    }

    public YeOldeKettle(String name) {
        this(name, new Volume(500, Volume.VolumeUnit.LITER));
    }

    public YeOldeKettle(Volume capacity) {
        this("Ye Olde Kettle", capacity);
    }
    
    public YeOldeKettle(String name, Volume capacity) {
        super(name, capacity);
    }
   
    
    @Override
    public void fireTemperatureReachedEvent(Temperature goal) {
        event.fire(new TemperatureReachedEvent(goal));
    }

    @Override
    public void fireTemperatureChangingEvent(Temperature goal) {
        event.fire(new TemperatureChangingEvent(this, goal));
    }

    @Override
    public void fireIngredientAddedEvent(Ingredient ingredient) {
        event.fire(new IngredientAddedEvent(ingredient));
    }

    @Override
    public void fireTemperatureReadingEvent(Temperature temperature) {
        event.fire(new TemperatureReadingEvent(temperature));
    }

    @Override
    public void fireWaitEvent(Duration duration) {
        event.fire(new WaitEvent(duration, this));
    }

    @Override
    public void fireWaitCompletedEvent() {
        event.fire(new WaitCompletedEvent());
    }

}
