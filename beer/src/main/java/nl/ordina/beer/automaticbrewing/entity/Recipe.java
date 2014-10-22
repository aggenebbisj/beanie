
package nl.ordina.beer.automaticbrewing.entity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import nl.ordina.beer.automaticbrewing.control.RecipeStep;

public class Recipe {
    private String name;
    private List<RecipeStep> steps;
    
    public Recipe(String name, List<RecipeStep> steps) {
        this.name = name;
        this.steps = steps;
    }
    
    public Recipe(String name, RecipeStep... steps) {
        this.name = name;
        this.steps = Arrays.asList(steps);
    }
    
    public boolean hasNextStep() {
        return steps.iterator().hasNext();
    }
    
    public RecipeStep nextStep() {
         return steps.iterator().next();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Recipe{" + "name=" + name + ", steps=" + steps + '}';
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
