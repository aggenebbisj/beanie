package nl.ordina.beer.entity;

import static nl.ordina.beer.entity.Temperature.TemperatureUnit.CELSIUS;
import static nl.ordina.beer.entity.Volume.VolumeUnit.LITER;
import org.junit.Test;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class KettleTest {

    private Kettle sut = new Kettle();
    
    @Test
    public void initial_temperature_should_be_zero_degrees() {
        assertThat(sut.getTemperature(), is(new Temperature(0, CELSIUS)));
    }

    @Test
    public void default_capacity_should_be_500_liters() {
        assertThat(sut.getCapacity(), is(new Volume(500, LITER)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_be_able_to_add_more_volume_than_capacity() {
        sut.addIngredient(new Ingredient("Water", new Volume(600, LITER)));
    }
    
    @Test
    public void should_increase_temperature_of_kettle_with_maximum_of_5_degrees() {
        sut.changeTemperatureToWithSteps(new Temperature(50, CELSIUS), new Temperature(5, CELSIUS));
        assertThat(sut.getTemperature(), is(new Temperature(5, CELSIUS)));
    }
    
    @Test
    public void should_not_increase_temperature_of_kettle_beyond_goal() {
        sut.changeTemperatureToWithSteps(new Temperature(4, CELSIUS), new Temperature(5, CELSIUS));
        assertThat(sut.getTemperature(), is(new Temperature(4, CELSIUS)));
    }
    
    @Test
    public void should_decrease_temperature_of_kettle_if_goal_is_lower() {
        sut.setTemperature(new Temperature(50, CELSIUS));
        sut.changeTemperatureToWithSteps(new Temperature(25, CELSIUS), new Temperature(5, CELSIUS));
        assertThat(sut.getTemperature(), is(new Temperature(45, CELSIUS)));
    }
    
}
