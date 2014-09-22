package nl.ordina.brewery.business.brewing.entity;

public class Gist extends Ingredient<Gist> {
    
    public Gist(Volume volume) {
        super(volume);
    }

    @Override
    protected Gist newIngredient(Volume volume) {
        return new Gist(volume);
    }

}
