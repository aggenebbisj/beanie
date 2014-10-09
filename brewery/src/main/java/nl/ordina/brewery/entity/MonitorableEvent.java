package nl.ordina.brewery.entity;

import javax.json.JsonObject;

public interface MonitorableEvent {
  JsonObject createJson();
}

