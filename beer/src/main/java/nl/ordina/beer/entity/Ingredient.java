package nl.ordina.beer.entity;

public class Ingredient {

    private String name;
    private Volume volume;

    public Ingredient() {
        // Required by JAXB
    }
    
    public Ingredient(String name, Volume volume) {
        this.name = name;
        this.volume = volume;
    }
    
    public Ingredient plus(Ingredient toevoeging) {
        volume = volume.plus(toevoeging.getVolume());
        return this;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Volume getVolume() {
        return volume;
    }

    public void setVolume(Volume volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Ingredient{" + "name=" + name + ", volume=" + volume + '}';
    }
    
}
