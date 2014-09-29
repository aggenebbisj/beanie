package nl.ordina.brewery.business.brewing.boundary;

import nl.ordina.brewery.business.brewing.entity.Kettle;
import nl.ordina.brewery.business.brewing.entity.KettleEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class KettleFactory {

  @Produces
  @ApplicationScoped
  public Kettle createKettle(Event<KettleEvent> event) {
    final Kettle kettle = new Kettle();
    kettle.setEvent(event);
    return kettle;
  }


}
