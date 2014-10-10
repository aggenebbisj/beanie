package nl.ordina.brewery.entity.ingredient;

import nl.ordina.brewery.entity.capacity.Volume;

public class Malt extends Ingredient<Malt> {
    
    public Malt(Volume volume) {
        super(volume);
    }

    @Override
    protected Malt newIngredient(Volume volume) {
        return new Malt(volume);
    }

  @Override
  public String getName() {
    return "Malt";
  }



}
