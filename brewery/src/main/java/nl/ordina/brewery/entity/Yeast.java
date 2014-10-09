package nl.ordina.brewery.entity;

public class Yeast extends Ingredient<Yeast> {
    
    public Yeast(Volume volume) {
        super(volume);
    }

    @Override
    protected Yeast newIngredient(Volume volume) {
        return new Yeast(volume);
    }

  @Override
  public String getName() {
    return "Yeast";
  }

}
