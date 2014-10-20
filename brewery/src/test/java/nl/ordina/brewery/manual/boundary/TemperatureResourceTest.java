
package nl.ordina.brewery.manual.boundary;

import nl.ordina.brewery.entity.Kettle;
import nl.ordina.brewery.entity.temperature.Temperature;
import static nl.ordina.brewery.entity.temperature.Temperature.TemperatureUnit.CELSIUS;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TemperatureResourceTest {

    @InjectMocks
    private TemperatureResource sut;
    
    @Mock
    private Kettle kettle;
    
    @Test 
    public void change_temperature() {
        sut.change(new Temperature(65, CELSIUS));
        verify(kettle).changeTemperatureTo(new Temperature(65, CELSIUS));
    }
}
