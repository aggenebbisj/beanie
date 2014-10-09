package nl.ordina.brewery.recipe.entity;

import nl.ordina.brewery.entity.KettleAction;

import java.util.List;

public class Step {

  private final String name;
  private final List<KettleAction> actions;

  public Step(String name,  List<KettleAction> actions) {

    this.name = name;
    this.actions = actions;
  }

  public List<KettleAction> getActions() {
    return actions;
  }

  @Override
  public String toString() {
    return "Step{" +
        "name='" + name + '\'' +
        '}';
  }
}
