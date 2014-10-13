package nl.ordina.brewery.entity;

import java.time.Duration;
import nl.ordina.brewery.entity.capacity.Volume;
import nl.ordina.brewery.entity.temperature.Temperature;
import nl.ordina.brewery.entity.ingredient.Ingredients;
import nl.ordina.brewery.entity.ingredient.Ingredient;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import nl.ordina.brewery.entity.ingredient.IngredientAddedEvent;
import nl.ordina.brewery.entity.temperature.TemperatureChangingEvent;
import nl.ordina.brewery.entity.temperature.TemperatureReachedEvent;
import nl.ordina.brewery.entity.temperature.TemperatureReadingEvent;
import nl.ordina.brewery.entity.waiting.WaitEvent;

@ApplicationScoped
public class Kettle {
  
  @Inject
  private Event<MonitoringEvent> event;
  
  private Temperature temperature = new Temperature(0, Temperature.TemperatureUnit.CELSIUS);
  private final Volume capacity;
  private final Ingredients ingredients = new Ingredients();
  private boolean isLocked = false;

  public Kettle() {
    this(new Volume(500, Volume.VolumeUnit.LITER));
  }

  public Kettle(Volume capacity) {
    this.capacity = capacity;
  }

  public void changeTemperatureTo(Temperature goal) {
    verifyKettleIsNotLocked();
    if(getTemperature().equals(goal)) event.fire(new TemperatureReachedEvent(goal));
    else                              event.fire(new TemperatureChangingEvent(this, goal));

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
    event.fire(new IngredientAddedEvent(ingredient));
  }

  private boolean isMaximumCapacityReached(Volume addedVolume) {
    return capacity.compareTo(ingredients.getVolume().plus(addedVolume)) < 0;
  }

  public void setEvent(Event<MonitoringEvent> event) {
    this.event = event;
  }

  public void changeInternalTemperature(Temperature newTemperature) {
    verifyKettleIsNotLocked();
    temperature = newTemperature;
    event.fire(new TemperatureReadingEvent(temperature));
  }

  public void lock(Duration duration) {
    this.isLocked = true;
    event.fire(new WaitEvent(duration));
  }

  private void verifyKettleIsNotLocked() {
    if (isLocked) throw new IllegalStateException("The kettle is locked, go away");
  }

    public void unlock() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
