package nl.ordina.brewery.business.brewing.entity;

import java.time.Duration;

public interface Action {

  void executeFor(Kettle kettle);
}

