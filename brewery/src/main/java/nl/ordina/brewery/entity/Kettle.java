package nl.ordina.brewery.entity;

import java.time.Duration;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import nl.ordina.brewery.entity.temperature.Temperature;
import nl.ordina.brewery.entity.ingredient.Ingredients;
import nl.ordina.brewery.entity.ingredient.Ingredient;
import nl.ordina.brewery.entity.ingredient.IngredientAddedEvent;
import nl.ordina.brewery.entity.temperature.TemperatureChangingEvent;
import nl.ordina.brewery.entity.temperature.TemperatureReachedEvent;
import nl.ordina.brewery.entity.temperature.TemperatureReadingEvent;
import nl.ordina.brewery.entity.waiting.WaitCompletedEvent;
import nl.ordina.brewery.entity.waiting.WaitEvent;

@ApplicationScoped @Manual
public class Kettle {

    private final Volume capacity;
    private Ingredients ingredients = new Ingredients();
    private final String name;
    
    private Temperature temperature = new Temperature(0, Temperature.TemperatureUnit.CELSIUS);
    private boolean isLocked = false;

    @Inject
    private Event<MonitoredEvent> event;
    
    public Kettle() {
        this("Ye Olde Kettle");
    }
    
    public Kettle(String name) {
        this(name, new Volume(500, Volume.VolumeUnit.LITER));
    }
    
    public Kettle(String name, Volume capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public void flush() {
        ingredients = new Ingredients();
    }

    public void changeTemperatureTo(Temperature goal) {
        verifyKettleIsNotLocked();
        if (getTemperature().equals(goal)) {
            fireTemperatureReachedEvent(goal);
        } else {
            fireTemperatureChangingEvent(goal);
        }

    }

    public void changeInternalTemperature(Temperature newTemperature) {
        verifyKettleIsNotLocked();
        temperature = newTemperature;
        fireTemperatureReadingEvent(temperature);
    }
    
    public Temperature getTemperature() {
        return temperature;
    }

    public Volume getCapacity() {
        return capacity;
    }

    public void addIngredient(Ingredient ingredient) {
        verifyKettleIsNotLocked();
        if (isMaximumCapacityReached(ingredient.getVolume())) {
            throw new IllegalArgumentException("Maximum capacity of kettle reached. Boooom ...");
        }
        ingredients.add(ingredient);
        fireIngredientAddedEvent(ingredient);
    }

    public Ingredients getIngredients() {
        return ingredients;
    }

    private boolean isMaximumCapacityReached(Volume addedVolume) {
        return capacity.compareTo(ingredients.getVolume().plus(addedVolume)) < 0;
    }

    public void lock(Duration duration) {
//        this.isLocked = true;
        fireWaitEvent(duration);
    }

    private void verifyKettleIsNotLocked() {
        if (isLocked) {
            throw new IllegalStateException("The kettle is locked, go away");
        }
    }

    public void unlock() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getName() {
        return name;
    }

    public void fireTemperatureReachedEvent(Temperature goal) {
        event.fire(new TemperatureReachedEvent(goal));
    }

    public void fireTemperatureChangingEvent(Temperature goal) {
        event.fire(new TemperatureChangingEvent(this, goal));
    }

    public void fireIngredientAddedEvent(Ingredient ingredient) {
        event.fire(new IngredientAddedEvent(ingredient));
    }

    public void fireTemperatureReadingEvent(Temperature temperature) {
        event.fire(new TemperatureReadingEvent(temperature));
    }

    public void fireWaitEvent(Duration duration) {
        event.fire(new WaitEvent(duration, this));
    }

    public void fireWaitCompletedEvent() {
        event.fire(new WaitCompletedEvent());
    }

}
