package nl.ordina.brewery.business.brewing.entity;

public class Hop extends Ingredient<Hop> {
    
    public Hop(Volume volume) {
        super(volume);
    }

    @Override
    protected Hop newIngredient(Volume volume) {
        return new Hop(volume);
    }

}
