package nl.ordina.brewery.business.brewing.entity;

import nl.ordina.brewery.business.brewing.entity.event.*;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Alternative;
import java.time.Duration;

import static nl.ordina.brewery.business.brewing.entity.Temperature.TemperatureUnit.CELSIUS;
import static nl.ordina.brewery.business.brewing.entity.Volume.VolumeUnit.LITER;

@Alternative
public class StandardKettle implements Kettle {

  private Event<KettleEvent> event;
  private Temperature temperature = new Temperature(0, CELSIUS);
  private final Volume capacity;
  private final Ingredients ingredients = new Ingredients();

  public StandardKettle() {
    this(new Volume(500, LITER));
  }

  public StandardKettle(Volume capacity) {
    this.capacity = capacity;
  }

  @Override
  public void changeTemperatureTo(Temperature goal) {
    if(getTemperature().equals(goal)) event.fire(new TemperatureChangedEvent(goal));
    else                              event.fire(new TemperatureChangingEvent(this, goal));

  }

  @Override
  public Temperature getTemperature() {
    return temperature;
  }

  @Override
  public Volume getCapacity() {
    return capacity;
  }

  @Override
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
    event.fire(new TemperatureReadingEvent(temperature));
  }

  private boolean isMaximumCapacityReached(Volume addedVolume) {
    return capacity.compareTo(ingredients.getVolume().plus(addedVolume)) < 0;
  }

  @Override
  public void keepStableTemperature(Duration duration) {
    event.fire(new KitchenTimerEvent(duration, temperature));
  }
}
