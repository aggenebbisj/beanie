
package nl.ordina.beer.automaticbrewing.control;

import java.util.Objects;
import nl.ordina.beer.entity.Ingredient;
import nl.ordina.beer.manualbrewing.control.Brewer;

public class AddIngredient implements RecipeStep<Ingredient> {
    private final Ingredient ingredient;

    public AddIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public void executeStep(Brewer brewer) {
        brewer.addIngredient(ingredient);
    }

    @Override
    public String toString() {
        return "AddIngredient{" + "ingredient=" + ingredient + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AddIngredient other = (AddIngredient) obj;
        if (!Objects.equals(this.ingredient, other.ingredient)) {
            return false;
        }
        return true;
    }

    
}
