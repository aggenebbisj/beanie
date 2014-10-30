package nl.ordina.beer.brewing.entity;

import javax.json.Json;
import javax.websocket.EncodeException;
import nl.ordina.beer.brewing.entity.AddIngredient.Encoder;
import static nl.ordina.beer.entity.EntityBuilder.defaultIngredient;
import nl.ordina.beer.entity.Ingredient;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class AddIngredientTest {

    private AddIngredient sut = new AddIngredient(defaultIngredient());

    @Test
    public void test_json_marshalling() throws EncodeException {
        final Ingredient ingredient = defaultIngredient();
        String expected = Json.createObjectBuilder()
                .add("event", "ingredient added")
                .add("ingredient",
                        Json.createObjectBuilder()
                        .add("name", ingredient.getName())
                        .add("volume",
                                Json.createObjectBuilder()
                                .add("value", ingredient.getVolume().getValue())
                                .add("unit", ingredient.getVolume().getUnit().name())
                                .build())
                        .build())
                .build().toString();
        assertThat(new Encoder().encode(sut), is(expected));
    }

}
