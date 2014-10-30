
package nl.ordina.beer.brewing.entity;

import javax.json.Json;
import javax.websocket.EncodeException;
import nl.ordina.beer.brewing.entity.EmptyKettle.Encoder;
import static nl.ordina.beer.entity.EntityBuilder.defaultIngredient;
import nl.ordina.beer.entity.Kettle;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

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
    public void test_json_marshalling() throws EncodeException {
        String expected = Json.createObjectBuilder()
                .add("event", "kettle emptied").build().toString();
        assertThat(new Encoder().encode(sut), is(expected));
    }
}
