package nl.ordina.brewery.entity;

import java.time.Duration;
import nl.ordina.brewery.entity.capacity.Volume;
import nl.ordina.brewery.entity.temperature.Temperature;
import nl.ordina.brewery.entity.ingredient.Ingredients;
import nl.ordina.brewery.entity.ingredient.Ingredient;

public abstract class Kettle {

    private final Volume capacity;
    private final Ingredients ingredients = new Ingredients();
    private final String name;
    
    private Temperature temperature = new Temperature(0, Temperature.TemperatureUnit.CELSIUS);
    private boolean isLocked = false;

    public Kettle(String name) {
        this(name, new Volume(500, Volume.VolumeUnit.LITER));
    }
    
    public Kettle(String name, Volume capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public void changeTemperatureTo(Temperature goal) {
        verifyKettleIsNotLocked();
        if (getTemperature().equals(goal)) {
            fireTemperatureReachedEvent(goal);
        } else {
            fireTemperatureChangingEvent(goal);
        }

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

    public void changeInternalTemperature(Temperature newTemperature) {
        verifyKettleIsNotLocked();
        temperature = newTemperature;
        fireTemperatureReadingEvent(temperature);
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

    public abstract void fireTemperatureReachedEvent(Temperature goal);

    public abstract void fireTemperatureChangingEvent(Temperature goal);

    public abstract void fireIngredientAddedEvent(Ingredient ingredient);

    public abstract void fireTemperatureReadingEvent(Temperature temperature);

    public abstract void fireWaitEvent(Duration duration);

    public abstract void fireWaitCompletedEvent();

}
