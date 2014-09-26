package nl.ordina.brewery.business.brewing.entity;

import static nl.ordina.brewery.business.brewing.entity.Volume.VolumeEenheid.LITER;
import static nl.ordina.brewery.business.brewing.entity.Temperature.Schaal.CELSIUS;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.Test;

public class KettleTest {
    
    @Test
    public void initiele_temperatuur_is_nul_graden_celsius() {
        assertThat(new Kettle().getTemperature(), is(new Temperature(0, CELSIUS)));
    }
    
    @Test
    public void standaard_capaciteit_is_500_liter() {
        assertThat(new Kettle().getCapacity(), is(new Volume(500, LITER)));
    }
    
    @Test
    public void temperatuur_verhogen_leidt_tot_hogere_temperatuur() {
        Kettle sut = new Kettle();
        sut.changeTemperatureTo(new Temperature(10, CELSIUS));
        assertThat(sut.getTemperature(), is(new Temperature(10, CELSIUS)));
    }

    @Test
    public void ingredient_toevoegen_zonder_overschrijden_maximum_capaciteit() {
        Kettle sut = new Kettle(new Volume(500, LITER));
        try {
            sut.addIngredient(new Water(new Volume(100, LITER)));
            sut.addIngredient(new Hop(new Volume(400, LITER)));
        } catch (Exception e) {
            fail("Capaciteit zou niet mogen zijn overschreden");
        }
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void teveel_ingredienten_toevoegen_geeft_exceptie() {
        Kettle sut = new Kettle(new Volume(500, LITER));
        sut.addIngredient(new Water(new Volume(600, LITER)));
    }
}
