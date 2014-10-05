package nl.ordina.brewery.business.brewing.boundary;

import nl.ordina.brewery.business.brewing.entity.Kettle;
import nl.ordina.brewery.business.brewing.entity.KettleEvent;
import nl.ordina.brewery.business.brewing.entity.StandardKettle;
import nl.ordina.brewery.business.brewing.entity.Volume;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Produces;

import static nl.ordina.brewery.business.brewing.entity.Volume.VolumeUnit.LITER;

@ApplicationScoped
public class KettleFactory {

  @Produces
  @ApplicationScoped
  public Kettle createKettle(Event<KettleEvent> event) {
    final StandardKettle kettle = new StandardKettle(new Volume(500, LITER));
    kettle.setEvent(event);
    return kettle;
  }


}
