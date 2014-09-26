package nl.ordina.brewery.business.brewing.entity;

import java.time.Duration;

import static nl.ordina.brewery.business.brewing.entity.Temperature.Schaal.CELSIUS;
import static nl.ordina.brewery.business.brewing.entity.Volume.VolumeEenheid.LITER;

public class Kettle {
    
    private Temperature temperature = new Temperature(0, CELSIUS);
    private final Volume capacity;
    private final Ingredients ingredients = new Ingredients();
    
    public Kettle() {
        this(new Volume(500, LITER));
    }
    
    public Kettle(Volume capacity) {
        this.capacity = capacity;
    }

    public void changeTemperatureTo(Temperature temperature) {
        this.temperature = temperature;
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
    }


    private boolean isMaximumCapacityReached(Volume addedVolume) {
        return capacity.compareTo(ingredients.getVolume().plus(addedVolume)) < 0;
    }

  public void keepStableTemperature(Duration duration) {

  }
}
