package nl.ordina.brewery.business.brewing.entity;

public class Water {
    private final Volume volume;

    public Water(Volume volume) {
        this.volume = volume;
    }

    public Water plus(Water toegevoegdWater) {
        return new Water(volume.plus(toegevoegdWater.volume));
    }

    public Volume getVolume() {
        return volume;
    }
}
