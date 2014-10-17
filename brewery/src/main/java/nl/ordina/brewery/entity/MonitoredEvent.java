package nl.ordina.brewery.entity;

import javax.json.JsonObject;

public interface MonitoredEvent {
  JsonObject createJson();
}

