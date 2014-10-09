package nl.ordina.brewery.entity;

public class Hop extends Ingredient<Hop> {
    
    public Hop(Volume volume) {
        super(volume);
    }

    @Override
    protected Hop newIngredient(Volume volume) {
        return new Hop(volume);
    }

  @Override
  public String getName() {
    return "Hop";
  }

}
