package nl.ordina.brewery.business.brewing.entity;

public class Mout extends Ingredient<Mout> {
    
    public Mout(Volume volume) {
        super(volume);
    }

    @Override
    protected Mout newIngredient(Volume volume) {
        return new Mout(volume);
    }

}
