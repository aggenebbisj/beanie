package nl.ordina.brewery.control;

import nl.ordina.brewery.entity.Kettle;
import nl.ordina.brewery.entity.MonitorableEvent;
import nl.ordina.brewery.entity.StandardKettle;
import nl.ordina.brewery.entity.Volume;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class KettleFactory {

  @Produces
  @ApplicationScoped
  public Kettle createKettle(Event<MonitorableEvent> event) {
    final StandardKettle kettle = new StandardKettle(new Volume(500, Volume.VolumeUnit.LITER));
    kettle.setEvent(event);
    return kettle;
  }


}
