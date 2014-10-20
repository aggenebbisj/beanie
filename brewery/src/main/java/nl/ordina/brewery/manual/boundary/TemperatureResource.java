package nl.ordina.brewery.manual.boundary;

import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import nl.ordina.brewery.entity.Kettle;
import nl.ordina.brewery.entity.Manual;
import nl.ordina.brewery.entity.temperature.Temperature;

@Path("kettle")
public class TemperatureResource {

    private static final Logger log = Logger.getLogger("TemperatureResource");

    @Inject
    @Manual
    Kettle kettle;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void change(Temperature temperature) {
        log.info(() -> String.format("Kettle %s: changing temperature to %s", kettle.getName(), temperature));
        kettle.changeTemperatureTo(temperature);
    }

}
