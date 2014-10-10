package nl.ordina.brewery.business.brewing.entity;

import java.util.ArrayList;
import java.util.List;
import static nl.ordina.brewery.business.brewing.entity.Volume.VolumeEenheid.LITER;

public class Ingredients {
    
    private final List<Ingredient> ingredienten = new ArrayList<>();
    
    public Volume getVolume() {
        return ingredienten.stream()
                           .map(Ingredient::getVolume)
                           .reduce(new Volume(0, LITER), (i1, i2) -> i1.plus(i2));
    }

    public Ingredients add(Ingredient ingredient) {
        ingredienten.add(ingredient);
        return this;
    }
    
}
