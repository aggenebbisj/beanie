package nl.ordina.beer.brewing.recipe.boundary;

import nl.ordina.beer.entity.Temperature;
import nl.ordina.beer.entity.Volume;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonObject;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.MINUTES;
import static nl.ordina.beer.brewing.recipe.entity.RecipeBuilder.aRecipe;
import static nl.ordina.beer.brewing.recipe.entity.RecipeBuilder.aRecipeJson;
import static nl.ordina.beer.entity.EntityBuilder.defaultIngredient;
import static nl.ordina.beer.entity.Temperature.TemperatureUnit.CELSIUS;
import static nl.ordina.beer.entity.Volume.VolumeUnit.LITER;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RecipeXmlAdapterTest {

    private RecipeJSONAdapter sut = new RecipeJSONAdapter();

    @Test
    public void test_unmarshal() {
        assertThat(sut.unmarshal(aRecipeJson()), is(aRecipe()));
    }

    @Test
    public void test_unmarshal_ingredient() {
        JsonObject ingredientJson = Json.createObjectBuilder().add("name", "water").add("volume",
                                                                                        Json.createObjectBuilder().add(
                                                                                                "value",
                                                                                                300).add("unit",
                                                                                                         "liter"))
                                        .build();
        assertThat(sut.unmarshalIngredient(ingredientJson), is(defaultIngredient()));
    }

    @Test
    public void test_unmarshal_volume() {
        JsonObject json = Json.createObjectBuilder().add("value", 300).add("unit", "liter").build();
        assertThat(sut.unmarshalVolume(json), is(new Volume(300, LITER)));
    }

    @Test
    public void test_unmarshal_temperature() {
        JsonObject json = Json.createObjectBuilder().add("value", 65).add("unit", "celsius").build();
        assertThat(sut.unmarshalTemperature(json), is(new Temperature(65, CELSIUS)));
    }

    @Test
    public void test_unmarshal_duration() {
        JsonObject json = Json.createObjectBuilder().add("value", 30).add("unit", "minutes").build();
        assertThat(sut.unmarshalDuration(json), is(Duration.of(30, MINUTES)));
    }
}
