package nl.ordina.beer.entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import static nl.ordina.beer.entity.Temperature.TemperatureUnit.CELSIUS;
import static nl.ordina.beer.entity.Volume.VolumeUnit.LITER;

@XmlAccessorType(XmlAccessType.FIELD)
public class Kettle {

    private final List<Ingredient> ingredients = new ArrayList<>();
    private Volume capacity = new Volume(500, Volume.VolumeUnit.LITER);
    private Temperature temperature = new Temperature(0, Temperature.TemperatureUnit.CELSIUS);
    private String name = "Nameless kettle";

    public Kettle() {
    }

    public Kettle(Volume volume) {
        this.capacity = volume;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        if (isMaximumCapacityReached(ingredient.getVolume())) {
            throw new IllegalArgumentException("Maximum capacity of kettle reached. Boooom ...");
        }
        this.ingredients.add(ingredient);
    }

    private Volume getVolume() {
        return ingredients.stream()
                .map(Ingredient::getVolume)
                .reduce(new Volume(0, LITER), Volume::plus);
    }

    private boolean isMaximumCapacityReached(Volume addedVolume) {
        return capacity.compareTo(getVolume().plus(addedVolume)) < 0;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public void changeTemperatureToWithSteps(Temperature goal, Temperature step) {
        if (temperature.isLowerThan(goal)) {
            temperature = temperature.plus(step).min(goal);
        } else if (!temperature.equals(goal)) { // cool down
            temperature = temperature.minus(step).max(goal);
        }
    }
    
    public Volume getCapacity() {
        return capacity;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void flush() {
        ingredients.clear();
    }

    @Override
    public String toString() {
        return "Kettle{" + "name=" + name + '}';
    }

}