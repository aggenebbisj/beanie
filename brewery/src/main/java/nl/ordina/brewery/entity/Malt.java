package nl.ordina.brewery.entity;

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
