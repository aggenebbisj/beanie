package nl.ordina.brewery.business.brewing.entity;

import static nl.ordina.brewery.business.brewing.entity.Temperatuur.Schaal.CELSIUS;
import static nl.ordina.brewery.business.brewing.entity.Volume.VolumeEenheid.LITER;

public class Ketel {
    private Temperatuur temperatuur = new Temperatuur(0, CELSIUS);
    private Volume capaciteit;
    private Water water = new Water(new Volume(0, LITER));

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

    public void voegWaterToe(Water toegevoegdWater) {
        if (isMaximumCapaciteitOverschreden(toegevoegdWater.getVolume())) {
            throw new IllegalArgumentException("Maximumcapaciteit ketel overschreden. Het bier is mislukt...");
        }
        this.water = this.water.plus(toegevoegdWater);
    }
    
    public Volume getCapaciteit() {
        return capaciteit;
    }

    private boolean isMaximumCapaciteitOverschreden(Volume toegevoegdVolume) {
        return water.plus(new Water(toegevoegdVolume)).getVolume().compareTo(capaciteit) > 0;
    }
}
