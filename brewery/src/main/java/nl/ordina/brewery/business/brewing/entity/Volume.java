package nl.ordina.brewery.business.brewing.entity;

public class Volume implements Comparable<Volume>{

    public enum VolumeUnit {
        LITER;
    }
    
    private final int value;
    private final VolumeUnit unit;

    public Volume(int value, VolumeUnit unit) {
        this.value = value;
        this.unit = unit;
    }
    
    public Volume plus(Volume extraVolume) {
        return new Volume(value + extraVolume.value, unit);
    }

    public int compareTo(Volume o) {
        return value < o.value ? -1 : value > o.value ? 1 : 0;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Volume other = (Volume) obj;
        if (this.value != other.value) {
            return false;
        }
        if (this.unit != other.unit) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Volume{" + "value=" + value + ", unit=" + unit + '}';
    }


  public int getValue() {
    return value;
  }

  public VolumeUnit getUnit() {
    return unit;
  }
}
