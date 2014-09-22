package nl.ordina.brewery.business.brewing.entity;

import static nl.ordina.brewery.business.brewing.entity.Temperatuur.Schaal.CELSIUS;
import static nl.ordina.brewery.business.brewing.entity.Volume.VolumeEenheid.LITER;

public class Ketel {
    private Temperatuur temperatuur = new Temperatuur(0, CELSIUS);
    private final Volume capaciteit;    
    private final Ingredienten ingredienten = new Ingredienten();
    
    public Ketel() {
        this(new Volume(500, LITER));
    }
    
    public Ketel(Volume capaciteit) {
        this.capaciteit = capaciteit;
    }

    public void verhoogTemperatuur(Temperatuur verhoging) {
        temperatuur = temperatuur.plus(verhoging);
    }
    
    public Temperatuur getTemperatuur() {
        return temperatuur;
    }

    public Volume getCapaciteit() {
        return capaciteit;
    }

    public void voegWaterToe(Water water) {
        voegIngredientToe(water);
    }
    
    public void voegHopToe(Hop hop) {
        voegIngredientToe(hop);
    }

    private void voegIngredientToe(Ingredient toegevoegdIngredient) {
        if (isMaximumCapaciteitOverschreden(toegevoegdIngredient.getVolume())) {
            throw new IllegalArgumentException("Maximumcapaciteit ketel overschreden. Het bier is mislukt...");
        }        
        ingredienten.voegToe(toegevoegdIngredient);
    }
    
    private boolean isMaximumCapaciteitOverschreden(Volume toegevoegdVolume) {
        return capaciteit.compareTo(ingredienten.getVolume().plus(toegevoegdVolume)) < 0;
    }

}
