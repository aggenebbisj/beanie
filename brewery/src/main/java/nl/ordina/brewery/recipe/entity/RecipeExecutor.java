package nl.ordina.brewery.recipe.entity;

import nl.ordina.brewery.entity.KettleAction;

import java.util.Iterator;
import nl.ordina.brewery.entity.Kettle;


public class RecipeExecutor {

    private Iterator<KettleAction> steps;

    public RecipeExecutor(Recipe recipe) {
        steps = recipe.getSteps().iterator();
    }

    public void nextStep(Kettle kettle) {
        if(steps.hasNext()) steps.next().executeFor(kettle);
    }

    public boolean isDone() {
        return !steps.hasNext();
    }

}
