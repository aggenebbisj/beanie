
package nl.ordina.beer.brewing.boundary;

import java.time.Duration;
import static java.time.temporal.ChronoUnit.MINUTES;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class DurationXmlAdapterTest {
    
    private final DurationXmlAdapter sut = new DurationXmlAdapter();
    
    @Test
    public void unmarshal() throws Exception {
        String json = "{ \"duration\":\"PT30M\" }"; 
        assertThat(sut.unmarshal(json), is(Duration.of(30, MINUTES)));
    }
    
    @Test
    public void marshal() throws Exception {
        String json = "{ \"duration\":\"PT30M\" }"; 
        assertThat(sut.marshal(Duration.of(30, MINUTES)), is(json));
    }
}
