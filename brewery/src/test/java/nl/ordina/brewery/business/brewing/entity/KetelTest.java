package nl.ordina.brewery.business.brewing.entity;

import static nl.ordina.brewery.business.brewing.entity.Volume.VolumeEenheid.LITER;
import static nl.ordina.brewery.business.brewing.entity.Temperatuur.Schaal.CELSIUS;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class KetelTest {
    
    @Test
    public void initiele_temperatuur_is_nul_graden_celsius() {
        assertThat(new Ketel().getTemperatuur(), is(new Temperatuur(0, CELSIUS)));
    }
    
    @Test
    public void standaard_capaciteit_is_500_liter() {
        assertThat(new Ketel().getCapaciteit(), is(new Volume(500, LITER)));
    }
    
    @Test
    public void temperatuur_verhogen_leidt_tot_hogere_temperatuur() {
        Ketel sut = new Ketel();
        sut.verhoogTemperatuur(new Temperatuur(10, CELSIUS));
        assertThat(sut.getTemperatuur(), is(new Temperatuur(10, CELSIUS)));
    }

    @Test
    public void water_toevoegen_zonder_overschrijden_maximum_capaciteit() {
        Ketel sut = new Ketel(new Volume(500, LITER));
        sut.voegWaterToe(new Water(new Volume(100, LITER)));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void teveel_water_toevoegen_geeft_exceptie() {
        Ketel sut = new Ketel(new Volume(500, LITER));
        sut.voegWaterToe(new Water(new Volume(600, LITER)));
    }
}
