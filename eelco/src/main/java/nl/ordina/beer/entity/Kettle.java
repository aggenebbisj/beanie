package nl.ordina.beer.entity;

import java.util.ArrayList;
import java.util.List;

public class Kettle {

    private final List<Ingredient> ingredients = new ArrayList<>();
    private Temperature temperature = new Temperature(0, Temperature.TemperatureUnit.CELSIUS);

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
    
    public boolean isEmpty() {
        return ingredients.isEmpty();
    }
    
    public void flush() {
        ingredients.clear();
    }
    
    public Temperature getTemperature() {
        return temperature;
    }

    public boolean isOnTemperature(Temperature goal) {
        return temperature.equals(goal);
    }
    
    public void changeTemperature(Temperature increment, Temperature goal) {
       if (temperature.isLowerThan(goal)) {
            temperature = temperature.plus(increment).min(goal);
        } else if (!temperature.equals(goal)) { // cool down
            temperature = temperature.minus(increment).max(goal);
        }
    }
    
}
