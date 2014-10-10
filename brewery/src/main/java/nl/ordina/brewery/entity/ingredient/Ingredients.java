package nl.ordina.brewery.entity.ingredient;

import java.util.ArrayList;
import java.util.List;
import nl.ordina.brewery.entity.capacity.Volume;

public class Ingredients {
    
    private final List<Ingredient> ingredienten = new ArrayList<>();
    
    public Volume getVolume() {
        return ingredienten.stream()
                           .map(Ingredient::getVolume)
                           .reduce(new Volume(0, Volume.VolumeUnit.LITER), Volume::plus);
    }

    public Ingredients add(Ingredient ingredient) {
        ingredienten.add(ingredient);
        return this;
    }
    
}
