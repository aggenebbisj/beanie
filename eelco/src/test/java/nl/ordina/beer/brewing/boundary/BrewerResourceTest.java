package nl.ordina.beer.brewing.boundary;

import nl.ordina.beer.brewing.control.Brewer;
import nl.ordina.beer.brewing.entity.EmptyKettle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.logging.Logger;

import static nl.ordina.beer.brewing.control.BrewActionBuilder.*;
import static nl.ordina.beer.entity.EntityBuilder.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BrewerResourceTest {

    @InjectMocks
    private BrewerRestEndpoint sut;

    @Mock
    private Brewer brewer;

    @Mock
    private Logger logger;

    private DurationXmlAdapter durationAdapter = new DurationXmlAdapter();

    @Test
    public void request_to_add_ingredient_should_add_action_to_brewer() {
        sut.addIngredient(defaultIngredient());
        verify(brewer).addAction(defaultAddIngredientAction());
    }

    @Test
    public void request_to_change_temperature_should_add_action_to_brewer() {
        sut.changeTemperatureTo(defaultTemperature());
        verify(brewer).addAction(defaultChangeTemperatureAction());
    }

    @Test
    public void request_to_keep_temperature_stable_should_add_action_to_brewer() {
        sut.durationAdapter = durationAdapter;
        sut.keepTemperatureStable(durationAdapter.marshal(defaultDuration()));
        verify(brewer).addAction(defaultKeepTemperatureStableAction());
    }

    @Test
    public void request_to_empty_kettle_should_add_action_to_brewer() {
        sut.emptyKettle();
        verify(brewer).addAction(any(EmptyKettle.class));
    }

    @Test
    public void request_for_kettle_should_return_kettle() {
        sut.getKettle();
        verify(brewer).getKettle();
    }
}
