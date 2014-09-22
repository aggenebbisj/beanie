package nl.ordina.brewery.business.brewing.entity;

public class Water extends Ingredient<Water> {

    public Water(Volume volume) {
        super(volume);
    }

    @Override
    protected Water newIngredient(Volume volume) {
        return new Water(volume);
    }
}
