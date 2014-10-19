package nl.ordina.brewery.entity;

import java.time.Duration;
import nl.ordina.brewery.entity.temperature.Temperature;
import nl.ordina.brewery.entity.ingredient.Ingredient;
import org.junit.Test;


import static nl.ordina.brewery.entity.temperature.Temperature.TemperatureUnit.CELSIUS;
import static nl.ordina.brewery.entity.Volume.VolumeUnit.LITER;
import nl.ordina.brewery.entity.ingredient.Ingredient;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Ignore;

public class KettleTest {

    private Kettle sut;
    
    @Before
    public void setup() {
        sut = new Kettle("TestKettle") {

            @Override
            public void fireTemperatureReachedEvent(Temperature goal) {}

            @Override
            public void fireTemperatureChangingEvent(Temperature goal) {}

            @Override
            public void fireIngredientAddedEvent(Ingredient ingredient) {}

            @Override
            public void fireTemperatureReadingEvent(Temperature temperature) {}

            @Override
            public void fireWaitEvent(Duration duration) {}

            @Override
            public void fireWaitCompletedEvent() {}
            
        };
    }
    
    @Test
    public void initiele_temperatuur_is_nul_graden_celsius() {
        assertThat(sut.getTemperature(), is(new Temperature(0, CELSIUS)));
    }

    @Test
    public void standaard_capaciteit_is_500_liter() {
        assertThat(sut.getCapacity(), is(new Volume(500, LITER)));
    }

    @Test
    public void ingredient_toevoegen_zonder_overschrijden_maximum_capaciteit() {
        try {
            sut.addIngredient(new Ingredient("Water", new Volume(100, LITER)));
            sut.addIngredient(new Ingredient("Hops", new Volume(400, LITER)));
        } catch (IllegalArgumentException e) {
            fail("Capaciteit zou niet mogen zijn overschreden");
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void teveel_ingredienten_toevoegen_geeft_exceptie() {
        sut.addIngredient(new Ingredient("Water", new Volume(600, LITER)));
    }

    @Ignore
    @Test(expected = IllegalStateException.class)
    public void performing_action_on_kettle_while_kettle_is_locked_should_throw_exception() {
        sut.lock(Duration.ZERO);
        sut.addIngredient(new Ingredient("Water", new Volume(300, LITER)));
    }

}
