package nl.ordina.brewery.entity.ingredient;

import nl.ordina.brewery.entity.Volume;
import org.junit.Test;

import static nl.ordina.brewery.entity.Volume.VolumeUnit.LITER;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class IngredientsTest {
    
    @Test
    public void volume_van_ingredienten_is_som_van_volumes_alle_losse_ingredienten() {
        Ingredients sut = new Ingredients();
        sut.add(new Ingredient("Water", new Volume(10, LITER)))
           .add(new Ingredient("Barley", new Volume(20, LITER)))
           .add(new Ingredient("Hops", new Volume(30, LITER)))
           .add(new Ingredient("Yeast", new Volume(40, LITER)));
        assertThat(sut.getVolume(), is(new Volume(100, LITER)));
    }
    
}
