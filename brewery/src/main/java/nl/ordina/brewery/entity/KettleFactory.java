package nl.ordina.brewery.entity;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class KettleFactory {

  @Produces
  @ApplicationScoped
  public Kettle createKettle(Event<KettleEvent> event) {
    final StandardKettle kettle = new StandardKettle(new Volume(500, Volume.VolumeUnit.LITER));
    kettle.setEvent(event);
    return kettle;
  }


}
