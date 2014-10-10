package nl.ordina.brewery.entity;

import nl.ordina.brewery.entity.capacity.Volume;
import nl.ordina.brewery.entity.temperature.Temperature;
import nl.ordina.brewery.entity.ingredient.Ingredients;
import nl.ordina.brewery.entity.ingredient.Ingredient;
import java.time.Duration;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import nl.ordina.brewery.entity.ingredient.IngredientAddedEvent;
import nl.ordina.brewery.entity.temperature.KitchenTimerEvent;
import nl.ordina.brewery.entity.temperature.TemperatureChangingEvent;
import nl.ordina.brewery.entity.temperature.TemperatureReachedEvent;
import nl.ordina.brewery.entity.temperature.TemperatureReadingEvent;

@ApplicationScoped
public class Kettle {
  
  @Inject
  private Event<MonitorableEvent> event;
  
  private Temperature temperature = new Temperature(0, Temperature.TemperatureUnit.CELSIUS);
  private final Volume capacity;
  private final Ingredients ingredients = new Ingredients();

  public Kettle() {
    this(new Volume(500, Volume.VolumeUnit.LITER));
  }

  public Kettle(Volume capacity) {
    this.capacity = capacity;
  }

  public void changeTemperatureTo(Temperature goal) {
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
    if (isMaximumCapacityReached(ingredient.getVolume())) {
      throw new IllegalArgumentException("Maximum capacity of kettle reached. Boooom ...");
    }
    ingredients.add(ingredient);
    event.fire(new IngredientAddedEvent(ingredient));
  }


  public void setEvent(Event<MonitorableEvent> event) {
    this.event = event;
  }

  public void changeInternalTemperature(Temperature newTemperature) {
    temperature = newTemperature;
    event.fire(new TemperatureReadingEvent(temperature));
  }

  private boolean isMaximumCapacityReached(Volume addedVolume) {
    return capacity.compareTo(ingredients.getVolume().plus(addedVolume)) < 0;
  }

  public void keepStableTemperature(Duration duration) {
    event.fire(new KitchenTimerEvent(duration, temperature));
  }
}
