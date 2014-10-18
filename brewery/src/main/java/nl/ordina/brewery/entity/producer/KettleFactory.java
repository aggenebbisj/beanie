package nl.ordina.brewery.entity.producer;

import nl.ordina.brewery.entity.Kettle;
import nl.ordina.brewery.entity.MonitoringEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Produces;

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
