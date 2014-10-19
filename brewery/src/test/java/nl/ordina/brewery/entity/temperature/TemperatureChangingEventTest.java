
package nl.ordina.brewery.entity.temperature;

import java.time.Duration;
import static java.time.temporal.ChronoUnit.MINUTES;
import nl.ordina.brewery.entity.Kettle;
import nl.ordina.brewery.entity.Volume;
import nl.ordina.brewery.entity.ingredient.Ingredient;
import nl.ordina.brewery.entity.ingredient.IngredientAddedEvent;
import org.junit.Test;

import static nl.ordina.brewery.entity.temperature.Temperature.TemperatureUnit.*;
import nl.ordina.brewery.entity.waiting.WaitCompletedEvent;
import nl.ordina.brewery.entity.waiting.WaitEvent;
import nl.ordina.brewery.recipe.entity.RecipeCompletedEvent;

public class TemperatureChangingEventTest {

    @Test
    public void should_render_json() {
        TemperatureChangingEvent sut = new TemperatureChangingEvent(
                new Kettle(), 
                new Temperature(65, CELSIUS)
        );
        
        System.out.println(sut.createJson());
    }
    
    @Test
    public void temperature_reading_event() {
        TemperatureReadingEvent sut = new TemperatureReadingEvent(
                new Temperature(65, CELSIUS)
        );
        
        System.out.println(sut.createJson());
    }
   
    @Test
    public void temperature_reached_event() {
        TemperatureReachedEvent sut = new TemperatureReachedEvent(
                new Temperature(65, CELSIUS)
        );
        
        System.out.println(sut.createJson());
    }
    
    @Test
    public void ingredient_added_event() {
        IngredientAddedEvent sut = new IngredientAddedEvent(
                new Ingredient("Hops", new Volume(300, Volume.VolumeUnit.LITER))
        );
        
        System.out.println(sut.createJson());
    }
    
    @Test
    public void wait_completed_event() {
        WaitCompletedEvent sut = new WaitCompletedEvent();
        
        System.out.println(sut.createJson());
    }
    
    @Test
    public void wait_event() {
        WaitEvent sut = new WaitEvent(
                Duration.of(30, MINUTES),
                new Kettle()
        );
        
        System.out.println(sut.createJson());
    }
    
    @Test
    public void recipe_completed_event() {
        RecipeCompletedEvent sut = new RecipeCompletedEvent();
        
        System.out.println(sut.createJson());
    }
    
}
