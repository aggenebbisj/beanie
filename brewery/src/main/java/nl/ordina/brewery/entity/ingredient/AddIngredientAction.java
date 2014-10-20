package nl.ordina.brewery.entity.ingredient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import nl.ordina.brewery.entity.Kettle;
import nl.ordina.brewery.entity.KettleAction;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AddIngredientAction implements KettleAction {

    private Ingredient ingredient;

    public AddIngredientAction() {
        // Required by JAXB
    }
    
    public AddIngredientAction(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
    
    @Override
    public void executeFor(Kettle kettle) {
        kettle.addIngredient(ingredient);
    }

    @Override
    public String toString() {
        return "AddIngredientAction{" + "ingredient=" + ingredient + '}';
    }

}
