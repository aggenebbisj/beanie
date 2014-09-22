package nl.ordina.brewery.business.brewing.entity;

import static nl.ordina.brewery.business.brewing.entity.Volume.VolumeEenheid.LITER;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class IngredientenTest {
    
    @Test
    public void volume_van_ingredienten_is_som_van_volumes_alle_losse_ingredienten() {
        Ingredienten sut = new Ingredienten();
        sut.voegToe(new Water(new Volume(10, LITER)))
           .voegToe(new Hop(new Volume(20, LITER)));        
        assertThat(sut.getVolume(), is(new Volume(30, LITER)));
    }
    
}
