package nl.ordina.brewery.entity;

import java.time.Duration;

public interface Kettle {
  void changeTemperatureTo(Temperature goal);

  Temperature getTemperature();

  Volume getCapacity();

  void addIngredient(Ingredient ingredient);

  void keepStableTemperature(Duration duration);
}
