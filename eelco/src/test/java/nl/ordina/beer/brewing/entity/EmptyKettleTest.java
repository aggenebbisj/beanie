package nl.ordina.beer.brewing.entity;

import nl.ordina.beer.entity.Kettle;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonObject;

import static nl.ordina.beer.entity.EntityBuilder.defaultIngredient;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class EmptyKettleTest {

    private EmptyKettle sut = new EmptyKettle();

    @Test
    public void should_empty_the_kettle() {
        Kettle kettle = new Kettle();
        kettle.addIngredient(defaultIngredient());
        assertFalse(kettle.isEmpty());
        sut.executeFor(kettle);
        assertTrue(kettle.isEmpty());
    }

    @Test
    public void test_json_marshalling() {
        JsonObject expected = Json.createObjectBuilder().add("event", "kettle emptied").build();
        assertThat(sut.toJson(), is(expected));
    }
}
