package nl.ordina.brewery.recipe.entity;

import nl.ordina.brewery.entity.ingredient.Ingredient;
import nl.ordina.brewery.entity.KettleAction;
import nl.ordina.brewery.entity.ingredient.AddIngredientAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Recipe {

    private List<Step> steps = new ArrayList<>();

    public Recipe() {
        // Required by JAXB
    }

    public Recipe(List<Step> steps) {
        this.steps = steps;
    }

    public Recipe(String name, List<KettleAction> actions) {
        this.steps = Arrays.asList(new Step(name, actions));
    }

    public List<Ingredient> getIngredients() {
        return steps.stream()
                .flatMap(this::getActions)
                .filter(a -> a instanceof AddIngredientAction)
                .map(a -> ((AddIngredientAction) a).getIngredient())
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

    @Override
    public String toString() {
        return "Recipe{" + "steps=" + steps + '}';
    }

}
