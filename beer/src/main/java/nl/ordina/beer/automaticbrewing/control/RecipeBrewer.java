
package nl.ordina.beer.automaticbrewing.control;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import nl.ordina.beer.automaticbrewing.entity.Recipe;
import nl.ordina.beer.control.NotificationEvent;
import nl.ordina.beer.manualbrewing.control.Brewer;
import nl.ordina.brewery.entity.Automatic;

@ApplicationScoped
public class RecipeBrewer {

    @Inject @Automatic
    Brewer brewer;

    @Inject
    Event<RecipeCompletedEvent> recipeCompletedEvent;
    
    private Recipe recipe;
    
    public void brew(Recipe recipe) {
        this.recipe = recipe;
        doNextStep();
    }
    
    public void doNextStep() {
        System.out.println("--- recipeBrewer hash: " + hashCode());
        if (recipe.hasNextStep()) recipe.nextStep().executeStep(brewer);
        else recipeCompletedEvent.fire(new RecipeCompletedEvent(recipe.getName()));
    }
    
    public void onStepCompleted(@Observes NotificationEvent recipeStepEvent) {
        if (recipeStepEvent.getKettle().isAutomatic() && recipeStepEvent.isCompleted())
            doNextStep();
    }

    void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
    
    @Override
    public String toString() {
        return "RecipeBrewer{" + "recipe=" + recipe + '}';
    }
    
}
