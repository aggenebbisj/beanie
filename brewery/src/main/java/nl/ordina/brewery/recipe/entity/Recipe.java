package nl.ordina.brewery.recipe.entity;

import nl.ordina.brewery.entity.Ingredient;
import nl.ordina.brewery.entity.KettleAction;
import nl.ordina.brewery.entity.action.AddIngredient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Recipe {
  private List<Step> steps = new ArrayList<>();

  public Recipe() {
  }

  public Recipe(List<Step> steps) {
    this.steps = steps;
  }

  public List<Ingredient> getIngredients() {
    return steps.stream()
        .flatMap(this::getActions)
        .filter(a -> a instanceof AddIngredient)
        .map(a -> ((AddIngredient) a).getIngredient())
        .collect(toList());
  }

  private Stream<KettleAction> getActions(Step s) {
    return s.getActions().stream();
  }

  public List<Step> getSteps() {
    return steps;
  }

  public void addStep(String name, List<KettleAction> actions) {
    steps.add(new Step(name, actions));
  }

}

