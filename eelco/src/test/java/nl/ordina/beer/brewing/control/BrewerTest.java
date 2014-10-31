
package nl.ordina.beer.brewing.control;

import nl.ordina.beer.brewing.entity.BrewAction;
import nl.ordina.beer.entity.Kettle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.enterprise.event.Event;

import static java.util.Arrays.asList;
import java.util.logging.Logger;
import static nl.ordina.beer.brewing.control.BrewActionBuilder.defaultAddIngredientAction;
import static nl.ordina.beer.brewing.control.BrewActionBuilder.defaultChangeTemperatureAction;
import static nl.ordina.beer.entity.EntityBuilder.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class BrewerTest {

    @InjectMocks
    private Brewer sut;
    
    @Mock
    private Kettle kettle;
    
    @Mock
    private Event<BrewAction> actionCompleted;
    
    @Mock
    private Logger logger;
    
    @Test 
    public void should_not_execute_action_when_queue_is_empty() {
        sut.executeNextAction();
        verifyZeroInteractions(kettle);
    }
    
    @Test
    public void should_execute_next_action_immediately_if_queue_is_empty() {
        sut.addAction(defaultAddIngredientAction());
        verify(kettle).addIngredient(defaultIngredient());
    }
    
    @Test
    public void should_not_execute_next_action_immediately_if_queue_is_not_empty() {
        sut.queue.add(defaultAddIngredientAction());
        sut.addAction(defaultAddIngredientAction());
        verifyZeroInteractions(kettle);
    }
    
    @Test
    public void should_fire_event_if_action_completed() {
        sut.addAction(defaultAddIngredientAction());
        verify(actionCompleted).fire(defaultAddIngredientAction());
    }
    
    @Test
    public void adding_multiple_action_should_execute_them_all() {
        Mockito.when(kettle.getTemperature()).thenReturn(defaultTemperature());
        sut.addActions(asList(defaultAddIngredientAction(), defaultChangeTemperatureAction()));
        verify(kettle).addIngredient(defaultIngredient());
        verify(kettle).changeTemperature(defaultTemperatureIncrement(), defaultTemperature());
    }
    
}
