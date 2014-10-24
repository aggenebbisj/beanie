package nl.ordina.beer.brewing.entity;

import nl.ordina.beer.entity.Ingredient;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonObject;

import static nl.ordina.beer.entity.EntityBuilder.defaultIngredient;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AddIngredientTest {

    private AddIngredient sut = new AddIngredient(defaultIngredient());

    @Test
    public void test_json_marshalling() {
        final Ingredient ingredient = defaultIngredient();
        JsonObject expected = Json.createObjectBuilder().add("event", "ingredient added").add("ingredient",
                                                                                              Json.createObjectBuilder()
                                                                                                  .add("name",
                                                                                                       ingredient
                                                                                                               .getName())
                                                                                                  .add("volume",
                                                                                                       Json.createObjectBuilder()
                                                                                                           .add("value",
                                                                                                                ingredient
                                                                                                                        .getVolume()
                                                                                                                        .getValue())
                                                                                                           .add("unit",
                                                                                                                ingredient
                                                                                                                        .getVolume()
                                                                                                                        .getUnit()
                                                                                                                        .name())
                                                                                                           .build())
                                                                                                  .build()).build();
        assertThat(sut.toJson(), is(expected));
    }

}
