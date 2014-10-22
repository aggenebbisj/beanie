
package nl.ordina.beer.automaticbrewing.entity;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import nl.ordina.beer.automaticbrewing.control.RecipeStep;

public class Recipe {
    private String name;
    private List<RecipeStep> steps;
    private final Iterator<RecipeStep> iterator;

    public Recipe(String name, List<RecipeStep> steps) {
        this.name = name;
        this.steps = steps;
        this.iterator = steps.iterator();
    }
    
    public Recipe(String name, RecipeStep... steps) {
        this(name, Arrays.asList(steps));
    }
    
    public boolean hasNextStep() {
        return iterator.hasNext();
    }
    
    public RecipeStep nextStep() {
         return iterator.next();
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
