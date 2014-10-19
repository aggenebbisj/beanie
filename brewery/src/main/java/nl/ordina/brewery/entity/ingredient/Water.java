package nl.ordina.brewery.entity.ingredient;

import nl.ordina.brewery.entity.capacity.Volume;

public class Water extends Ingredient<Water> {

    public Water(Volume volume) {
        super(volume);
    }

    @Override
    protected Water newIngredient(Volume volume) {
        return new Water(volume);
    }

    @Override
    public String getName() {
        return "Water";
    }
}
