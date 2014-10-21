package nl.ordina.brewery.manual.boundary;

import java.time.Duration;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import nl.ordina.brewery.entity.Kettle;
import nl.ordina.brewery.entity.Manual;
import nl.ordina.brewery.entity.temperature.Temperature;

@Path("kettle")
public class KettleResource {

    private static final Logger log = Logger.getLogger("TemperatureResource");

    @Inject
    DurationXmlAdapter adapter;
    
    @Inject
    @Manual
    Kettle kettle;

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("temperature")
    public void change(Temperature temperature) {
        log.info(() -> String.format("Kettle %s: changing temperature to %s", kettle.getName(), temperature));
        kettle.changeTemperatureTo(temperature);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("wait")
    public void waitFor(String duration) {
        log.info(() -> String.format("Kettle %s: waiting for %s", kettle.getName(), duration));        
        kettle.fireWaitEvent(adapter.unmarshal(duration));
    }

}
