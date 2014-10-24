package nl.ordina.beer.entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
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
        temperature = new Temperature(0, Temperature.TemperatureUnit.CELSIUS);
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

    @Override
    public String toString() {
        return "Kettle{" + "ingredients=" + ingredients + ", temperature=" + temperature + '}';
    }
    
}
