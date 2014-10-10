package nl.ordina.brewery.entity;

import nl.ordina.brewery.entity.capacity.Volume;
import nl.ordina.brewery.entity.ingredient.Hop;
import nl.ordina.brewery.entity.ingredient.Ingredients;
import nl.ordina.brewery.entity.ingredient.Yeast;
import nl.ordina.brewery.entity.ingredient.Malt;
import nl.ordina.brewery.entity.ingredient.Water;
import org.junit.Test;

import static nl.ordina.brewery.entity.capacity.Volume.VolumeUnit.LITER;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class IngredientsTest {
    
    @Test
    public void volume_van_ingredienten_is_som_van_volumes_alle_losse_ingredienten() {
        Ingredients sut = new Ingredients();
        sut.add(new Water(new Volume(10, LITER)))
           .add(new Hop(new Volume(20, LITER)))
           .add(new Malt(new Volume(30, LITER)))
           .add(new Yeast(new Volume(40, LITER)));
        assertThat(sut.getVolume(), is(new Volume(100, LITER)));
    }
    
}
