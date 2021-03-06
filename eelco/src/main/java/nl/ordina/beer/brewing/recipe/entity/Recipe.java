
package nl.ordina.beer.brewing.recipe.entity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import nl.ordina.beer.brewing.entity.BrewAction;

public class Recipe {
    private String name;
    private List<BrewAction> steps;

    public Recipe(String name, List<BrewAction> steps) {
        this.name = name;
        this.steps = steps;
    }
    
    public Recipe(String name, BrewAction... steps) {
        this(name, Arrays.asList(steps));
    }

    public List<BrewAction> getSteps() {
        return steps;
    }
    
    @Override
    public String toString() {
        return "Recipe{" + "name=" + name + ", steps=" + steps + '}';
    }

 
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Recipe other = (Recipe) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.steps, other.steps)) {
            return false;
        }
        return true;
    }

}
