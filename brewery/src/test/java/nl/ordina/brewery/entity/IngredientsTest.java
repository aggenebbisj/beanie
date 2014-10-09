package nl.ordina.brewery.entity;

import static nl.ordina.brewery.entity.Volume.VolumeUnit.LITER;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import nl.ordina.brewery.entity.*;
import org.junit.Test;

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
