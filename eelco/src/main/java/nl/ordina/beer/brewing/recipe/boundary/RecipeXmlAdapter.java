package nl.ordina.beer.brewing.recipe.boundary;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import static java.util.stream.Collectors.toList;
import javax.json.JsonObject;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import nl.ordina.beer.brewing.entity.AddIngredient;
import nl.ordina.beer.brewing.entity.BrewAction;
import nl.ordina.beer.brewing.entity.ChangeTemperature;
import nl.ordina.beer.brewing.entity.KeepTemperatureStable;
import nl.ordina.beer.brewing.recipe.entity.Recipe;
import nl.ordina.beer.entity.Ingredient;
import nl.ordina.beer.entity.Temperature;
import nl.ordina.beer.entity.Temperature.TemperatureUnit;
import nl.ordina.beer.entity.Volume;
import nl.ordina.beer.entity.Volume.VolumeUnit;

public class RecipeXmlAdapter extends XmlAdapter<JsonObject, Recipe> {

    @Override
    public Recipe unmarshal(JsonObject value) {
        final String name = value.getString("name");       
        final List<BrewAction> steps = value.getJsonArray("steps")
                .stream()
                .map(j -> (JsonObject) j)
                .map(this::mapStep)
                .collect(toList());
        return new Recipe(name, steps);
    }

    private BrewAction mapStep(JsonObject step) {
        switch (step.getString("type").toLowerCase()) {
            case "addingredient":
                return new AddIngredient(unmarshalIngredient(step.getJsonObject("ingredient")));
            case "changetemperature":
                return new ChangeTemperature(unmarshalTemperature(step.getJsonObject("temperature")));
            case "keeptemperaturestable":
                return new KeepTemperatureStable(unmarshalDuration(step.getJsonObject("duration")));
            default:
                throw new IllegalArgumentException(step.getString("type") + " is not a valid recipe step");
        }
    }

    public Ingredient unmarshalIngredient(JsonObject ingredient) {
        Volume volume = unmarshalVolume(ingredient.getJsonObject("volume"));
        return new Ingredient(ingredient.getString("name"), volume);
    }

    public Volume unmarshalVolume(JsonObject volume) {
        final int value = volume.getInt("value");
        final String unit = volume.getString("unit").toUpperCase();
        return new Volume(value, VolumeUnit.valueOf(unit));
    }

    public Temperature unmarshalTemperature(JsonObject temperature) {
        final int value = Integer.parseInt(temperature.getString("value"));
        final String unit = temperature.getString("unit").toUpperCase();
        return new Temperature(value, TemperatureUnit.valueOf(unit));
    }
    
    public Duration unmarshalDuration(JsonObject duration) {
        final int value = duration.getInt("value");
        final String unit = duration.getString("unit").toUpperCase();
        return Duration.of(value, ChronoUnit.valueOf(unit));
    }
    
    @Override
    public JsonObject marshal(Recipe value) {
        return null;
    }

}