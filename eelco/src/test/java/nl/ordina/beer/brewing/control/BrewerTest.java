
package nl.ordina.beer.brewing.control;

import static java.util.Arrays.asList;
import javax.enterprise.event.Event;
import static nl.ordina.beer.brewing.control.BrewActionBuilder.defaultAddIngredientAction;
import static nl.ordina.beer.brewing.control.BrewActionBuilder.defaultChangeTemperatureAction;
import nl.ordina.beer.brewing.entity.BrewActionAddedEvent;
import nl.ordina.beer.brewing.entity.BrewActionCompletedEvent;
import static nl.ordina.beer.entity.EntityBuilder.defaultIngredient;
import static nl.ordina.beer.entity.EntityBuilder.defaultTemperature;
import static nl.ordina.beer.entity.EntityBuilder.defaultTemperatureIncrement;
import nl.ordina.beer.entity.Kettle;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BrewerTest {

    @InjectMocks
    private Brewer sut;
    
    @Mock
    private Kettle kettle;
    
    @Mock
    private Event<BrewActionCompletedEvent> actionCompleted;
    
    @Mock
    private Event<BrewActionAddedEvent> actionAdded;
    
    @Test
    public void should_be_able_to_add_action_to_queue() {
        sut.addAction(defaultAddIngredientAction());
        assertThat(sut.nextAction(), is(defaultAddIngredientAction()));
    }
    
    @Ignore
    @Test
    public void should_be_able_to_add_multiple_actions_to_queue() {
        sut.addActions(asList(defaultAddIngredientAction(), defaultChangeTemperatureAction()));
        assertThat(sut.nextAction(), is(defaultAddIngredientAction()));
        assertThat(sut.nextAction(), is(defaultChangeTemperatureAction()));
    }
    
    @Ignore
    @Test
    public void adding_an_action_should_fire_an_event() {
        sut.addAction(defaultAddIngredientAction());
        verify(actionAdded).fire(any(BrewActionAddedEvent.class));
    }
    
    @Ignore
    @Test
    public void adding_multiple_actions_should_fire_an_event() {
        sut.addActions(asList(defaultAddIngredientAction()));
        verify(actionAdded).fire(any(BrewActionAddedEvent.class));
    }
    
    @Test 
    public void should_not_execute_action_when_queue_is_empty() {
        sut.executeNextAction();
        verifyZeroInteractions(kettle);
    }
    
    @Test
    public void should_execute_next_action_when_queue_is_not_empty() {
        sut.addAction(defaultAddIngredientAction());
        sut.addAction(defaultChangeTemperatureAction());
        sut.executeNextAction();
        verify(kettle).addIngredient(defaultIngredient());
    }
    
    @Test
    public void should_execute_same_action_if_action_not_fully_completed() {
        sut.onActionCompleted(new BrewActionCompletedEvent(defaultChangeTemperatureAction()));
        verify(kettle).changeTemperature(defaultTemperatureIncrement(), defaultTemperature());
    }
    
    @Ignore
    @Test
    public void should_fire_event_when_action_is_completed() {
        sut.actionCompleted = actionCompleted;
        sut.addAction(defaultAddIngredientAction());
        sut.executeNextAction();
        verify(actionCompleted).fire(new BrewActionCompletedEvent(defaultAddIngredientAction()));
    }
    
}
