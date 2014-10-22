
package nl.ordina.beer.manualbrewing.control;

import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import nl.ordina.beer.control.TemperatureChangedEvent;
import nl.ordina.beer.control.IngredientAddedEvent;
import nl.ordina.beer.control.TemperatureController;
import nl.ordina.beer.entity.Ingredient;
import nl.ordina.beer.entity.Kettle;
import nl.ordina.beer.entity.Temperature;

@Stateless
public class Brewer {
    
    @Inject
    Event<IngredientAddedEvent> ingredientAddedEvent;
    
    @Inject
    TemperatureController thermostat;
    
    @Inject
    Kettle kettle;
    
    public void addIngredient(Ingredient ingredient) {
        kettle.addIngredient(ingredient);
        ingredientAddedEvent.fire(new IngredientAddedEvent(ingredient, kettle));
    }
    
    public List<Ingredient> getIngredients() {
        return kettle.getIngredients();
    }

    public void emptyKettle() {
        kettle.flush();
    }
    
    public Kettle getKettle() {
        return kettle;
    }

    public void changeTemperatureTo(Temperature goal) {
        thermostat.changeTemperature(goal, kettle);
    }
}