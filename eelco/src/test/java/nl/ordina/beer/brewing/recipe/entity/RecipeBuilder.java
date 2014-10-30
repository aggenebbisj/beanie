package nl.ordina.beer.brewing.recipe.entity;

import java.time.Duration;
import static java.time.temporal.ChronoUnit.MINUTES;
import java.util.Collections;
import javax.json.Json;
import javax.json.JsonObject;
import nl.ordina.beer.brewing.entity.AddIngredient;
import nl.ordina.beer.brewing.entity.ChangeTemperature;
import nl.ordina.beer.brewing.entity.KeepTemperatureStable;
import nl.ordina.beer.entity.Ingredient;
import nl.ordina.beer.entity.Kettle;
import nl.ordina.beer.entity.Temperature;
import static nl.ordina.beer.entity.Temperature.TemperatureUnit.CELSIUS;
import nl.ordina.beer.entity.Volume;
import static nl.ordina.beer.entity.Volume.VolumeUnit.LITER;

public class RecipeBuilder {

    public static Kettle defaultKettle() {
        return new Kettle();
    }
    
    public static Ingredient defaultIngredient() {
        return new Ingredient("water", new Volume(300, LITER));
    }
    
    public static Temperature defaultTemperature() {
        return new Temperature(65, CELSIUS);
    }
    
    public static Duration defaultDuration() {
        return Duration.of(30, MINUTES);
    }
    
    public static AddIngredient defaultAddIngredientStep() {
        return new AddIngredient(defaultIngredient());
    }
    
    public static Recipe anEmptyRecipe() {
        return new Recipe("TestRecipe", Collections.emptyList());
    }
    
    public static Recipe aRecipe() {
        return new Recipe("TestRecipe",
                new AddIngredient(defaultIngredient()),
                new ChangeTemperature(defaultTemperature()),
                new KeepTemperatureStable(defaultDuration())
        );
    }

    public static JsonObject aRecipeJson() {
        return Json.createObjectBuilder()
                .add("name", "TestRecipe")
                .add("steps", Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                                .add("type", "addIngredient")
                                .add("ingredient", Json.createObjectBuilder()
                                        .add("name", "water")
                                        .add("volume", Json.createObjectBuilder()
                                                .add("value", 300)
                                                .add("unit", "liter")
                                        )
                                )
                        ).add(Json.createObjectBuilder()
                                .add("type", "changeTemperature")
                                .add("temperature", Json.createObjectBuilder()
                                        .add("value", "65")
                                        .add("unit", "celsius")
                                )
                        ).add(Json.createObjectBuilder()
                                .add("type", "keepTemperatureStable")
                                .add("duration", Json.createObjectBuilder()
                                        .add("value", 30)
                                        .add("unit", "minutes")
                                )
                        )
                ).build();
    }
}
