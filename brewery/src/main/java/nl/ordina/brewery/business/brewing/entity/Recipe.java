package nl.ordina.brewery.business.brewing.entity;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Recipe {
  private List<Ingredient> ingredients = new ArrayList<>();
  private List<Step> steps = new ArrayList<>();

  public List<Ingredient> getIngredients() {
    return steps.stream()
        .flatMap(s -> s.getIngredients().stream())
        .collect(toList());
  }

  public List<Step> getSteps() {
    return steps;
  }

  public void addStep(String name, List<Ingredient> ingredients, List<Action> actions) {
    steps.add(new Step(name, ingredients, actions));
  }

  @Override
  public String toString() {
    return "Recipe{" +
        "ingredients=" + ingredients +
        '}';
  }
}

class Step {

  private final String name;
  private final List<Ingredient> ingredients;
  private final List<Action> actions;

  public Step(String name, List<Ingredient> ingredients, List<Action> actions) {

    this.name = name;
    this.ingredients = ingredients;
    this.actions = actions;
  }

  public List<Ingredient> getIngredients() {
    return ingredients;
  }

  public List<Action> getActions() {
    return actions;
  }
}
