package nl.ordina.brewery.business.brewing.entity;

public abstract class Ingredient<T extends Ingredient> {
    
    private final Volume volume;

    public Ingredient(Volume volume) {
        this.volume = volume;
    }

    public T plus(T toevoeging) {
        return newIngredient(volume.plus(toevoeging.getVolume()));
    }

    public Volume getVolume() {
        return volume;
    }

    protected abstract T newIngredient(Volume plus);
    
}
