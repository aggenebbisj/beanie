
package nl.ordina.beer.brewing.entity;

import java.util.Objects;
import nl.ordina.beer.entity.Ingredient;
import nl.ordina.beer.entity.Kettle;

public class AddIngredient extends BrewAction {
    private final Ingredient ingredient;

    public AddIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public void executeFor(Kettle kettle) {
        kettle.addIngredient(ingredient);
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
