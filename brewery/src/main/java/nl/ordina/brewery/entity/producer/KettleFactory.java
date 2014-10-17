package nl.ordina.brewery.entity.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.ws.rs.Produces;
import nl.ordina.brewery.entity.Kettle;
import nl.ordina.brewery.entity.MonitoringEvent;

public class KettleFactory {

    @Produces
    @Automatic
    @ApplicationScoped
    public Kettle createAutomaticKettle(@Automatic Event<MonitoringEvent> event) {
        return new Kettle("Kettle2000", event);
    }
    
    @Produces
    @Manual
    @ApplicationScoped
    public Kettle createYeOldeKettle(@Manual Event<MonitoringEvent> event) {
        return new Kettle("Ye olde Kettle", event);
    }
}
