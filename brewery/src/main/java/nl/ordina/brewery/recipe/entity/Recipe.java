package nl.ordina.brewery.recipe.entity;

import nl.ordina.brewery.entity.ingredient.Ingredient;
import nl.ordina.brewery.entity.KettleAction;
import nl.ordina.brewery.entity.ingredient.AddIngredientAction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Recipe {
    
    private String name;
    @XmlAnyElement
    @XmlElement(name = "steps")
    private List<KettleAction> steps = new ArrayList<>();

    public Recipe() {
        // Required by JAXB
    }

    public Recipe(List<KettleAction> steps) {
        this.steps = steps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<KettleAction> getSteps() {
        return steps;
    }

    public void setSteps(List<KettleAction> steps) {
        this.steps = steps;
    }
    
    public List<Ingredient> getIngredients() {
        return steps.stream()
                .filter(a -> a instanceof AddIngredientAction)
                .map(a -> ((AddIngredientAction) a).getIngredient())
                .collect(toList());
    }

    public void addStep(KettleAction action) {
        steps.add(action);
    }

    @Override
    public String toString() {
        return "Recipe{" + "name=" + name + ", steps=" + steps + '}';
    }

}
