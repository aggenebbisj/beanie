package nl.ordina.beer.entity;

import java.util.Objects;

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

    public Volume getVolume() {
        return volume;
    }

    public void setVolume(Volume volume) {
        this.volume = volume;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ingredient other = (Ingredient) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.volume, other.volume)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ingredient{" + "name=" + name + ", volume=" + volume + "}";
    }

}
