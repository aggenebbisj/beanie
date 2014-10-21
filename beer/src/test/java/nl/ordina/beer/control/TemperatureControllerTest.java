package nl.ordina.beer.control;

import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.enterprise.event.Event;
import nl.ordina.beer.entity.Kettle;
import nl.ordina.beer.entity.Temperature;
import static nl.ordina.beer.entity.Temperature.TemperatureUnit.CELSIUS;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TemperatureControllerTest {

    @InjectMocks
    private TemperatureController sut;

    @Mock
    private Event<TemperatureChangedEvent> event;

    @Mock 
    private TimerService timerService;
    
    private Kettle kettle = new Kettle();

    @Test
    public void should_increase_temperature_of_kettle_with_maximum_of_5_degrees() {
        sut.changeTemperature(new Temperature(50, CELSIUS), kettle);
        assertThat(kettle.getTemperature(), is(new Temperature(5, CELSIUS)));
    }
    
    @Test
    public void should_decrease_temperature_of_kettle_if_goal_is_lower() {
        kettle.setTemperature(new Temperature(50, CELSIUS));
        sut.changeTemperature(new Temperature(25, CELSIUS), kettle);
        assertThat(kettle.getTemperature(), is(new Temperature(45, CELSIUS)));
    }

    @Test
    public void should_fire_event_after_temperature_is_changed() {
        final Temperature goal = new Temperature(50, CELSIUS);
        sut.changeTemperature(goal, kettle);
        Mockito.verify(event).fire(new TemperatureChangedEvent(goal, kettle));
    }

    @Test
    public void should_set_timer_for_another_event_if_goal_is_not_reached_in_one_turn() {
        final Temperature goal = new Temperature(50, CELSIUS);
        sut.changeTemperature(goal, kettle);
//        Mockito.verify(timerService).createSingleActionTimer(Mockito.anyLong(), Mockito.anyObject());
    }

    @Test
    public void timeout_should_trigger_another_change_of_temperature() {
        // TODO -> difficult with mocking
    }
}
