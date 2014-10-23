
package nl.ordina.beer.entity;

import java.time.Duration;
import static java.time.temporal.ChronoUnit.MINUTES;
import static nl.ordina.beer.entity.Temperature.TemperatureUnit.CELSIUS;
import static nl.ordina.beer.entity.Volume.VolumeUnit.LITER;

public class EntityBuilder {

    private EntityBuilder() {
        // Utility class
    }
    
    public static Ingredient defaultIngredient() {
        return new Ingredient("water", new Volume(300, LITER));
    }
    
    public static Temperature defaultTemperature() {
        return new Temperature(65, CELSIUS);
    }
    
    public static Temperature defaultTemperatureIncrement() {
        return new Temperature(5, CELSIUS);
    }
    
    public static Duration defaultDuration() {
        return Duration.of(30, MINUTES);
    }
    
    public static Volume defaultVolume() {
        return new Volume(300, LITER);
    }    
    
}
