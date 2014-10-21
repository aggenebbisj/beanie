
package nl.ordina.brewery.manual.boundary;

import java.time.Duration;
import static java.time.temporal.ChronoUnit.MINUTES;
import nl.ordina.brewery.entity.Kettle;
import nl.ordina.brewery.entity.temperature.Temperature;
import static nl.ordina.brewery.entity.temperature.Temperature.TemperatureUnit.CELSIUS;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class KettleResourceTest {

    @InjectMocks
    private KettleResource sut;
    
    @Mock
    private Kettle kettle;
    
    @Before
    public void setup() {
        sut.adapter = new DurationXmlAdapter();
    }
    
    @Test 
    public void change_temperature_should_trigger_call_to_kettle_to_change_temperature() {
        sut.change(new Temperature(65, CELSIUS));
        verify(kettle).changeTemperatureTo(new Temperature(65, CELSIUS));
    }
  
    @Test 
    public void wait_should_result_in_event_fired() {
        String durationJson = "{ \"duration\":\"PT30M\" }"; 
        sut.waitFor(durationJson);
        verify(kettle).fireWaitEvent(Duration.of(30, MINUTES));
    }
}
