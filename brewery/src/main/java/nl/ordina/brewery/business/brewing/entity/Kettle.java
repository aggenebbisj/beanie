package nl.ordina.brewery.business.brewing.entity;

import nl.ordina.brewery.business.brewing.entity.event.IngredientAddedEvent;
import nl.ordina.brewery.business.brewing.entity.event.TemperatureChangingEvent;
import nl.ordina.brewery.business.brewing.entity.event.TimerExpiredEvent;

import javax.enterprise.event.Event;
import java.time.Duration;

import static nl.ordina.brewery.business.brewing.entity.Temperature.TemperatureUnit.CELSIUS;
import static nl.ordina.brewery.business.brewing.entity.Volume.VolumeUnit.LITER;

public class Kettle {

  private Event<KettleEvent> event;
  private Temperature temperature = new Temperature(0, CELSIUS);
  private final Volume capacity;
  private final Ingredients ingredients = new Ingredients();

  public Kettle() {
    this(new Volume(500, LITER));
  }

  public Kettle(Volume capacity) {
    this.capacity = capacity;
  }

  public void changeTemperatureTo(Temperature goal) {
//    final int difference = this.temperature.difference(goal);
    event.fire(new TemperatureChangingEvent(this, goal));
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


  public void setEvent(Event<KettleEvent> event) {
    this.event = event;
  }

  public void changeInternalTemperature(Temperature newTemperature) {
    temperature = newTemperature;
  }

  private boolean isMaximumCapacityReached(Volume addedVolume) {
    return capacity.compareTo(ingredients.getVolume().plus(addedVolume)) < 0;
  }

  public void keepStableTemperature(Duration duration) {
    event.fire(new TimerExpiredEvent(duration, temperature));
  }
}
