package nl.ordina.beer.brewing.control;

import nl.ordina.beer.brewing.entity.AddIngredient;
import nl.ordina.beer.brewing.entity.ChangeTemperature;
import nl.ordina.beer.brewing.entity.KeepTemperatureStable;

import static nl.ordina.beer.entity.EntityBuilder.*;

public class BrewActionBuilder {

    private BrewActionBuilder() {
        // Utility class
    }

    public static AddIngredient defaultAddIngredientAction() {
        return new AddIngredient(defaultIngredient());
    }

    public static ChangeTemperature defaultChangeTemperatureAction() {
        return new ChangeTemperature(defaultTemperature());
    }

    public static KeepTemperatureStable defaultKeepTemperatureStableAction() {
        return new KeepTemperatureStable(defaultDuration());
    }
}
