package nl.ordina.brewery.manual.boundary;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import nl.ordina.brewery.entity.capacity.Volume;
import nl.ordina.brewery.entity.ingredient.Ingredient;
import nl.ordina.brewery.entity.ingredient.Water;

public class IngredientJson {
    public IngredientType ingredient;
    public VolumeJson volume;

    public static class VolumeJson {

        public int value;
        public String unit;

        @Override
        public String toString() {
            return "VolumeJson{" + "value=" + value + ", unit=" + unit + '}';
        }
        
        public Volume toDomain(){
            return new Volume(value, getUnit(unit));
        }
        
        private Volume.VolumeUnit getUnit(final String volumeUnit){
            switch(volumeUnit.toLowerCase()){
                case "liter": return Volume.VolumeUnit.LITER;
                default: throw new IllegalStateException("VolumeUnit Undefined");
            }
        }
    }

    @Override
    public String toString() {
        return "IngredientJson{" + "ingredient=" + ingredient + ", volume=" + volume + '}';
    }

    public Ingredient toDomain(){
        switch(ingredient){
            case water: return new Water(volume.toDomain());
            default: throw new IllegalStateException("Type Undefined");
        }
    }
}
