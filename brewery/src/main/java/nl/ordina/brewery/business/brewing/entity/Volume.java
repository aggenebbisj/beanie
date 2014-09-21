package nl.ordina.brewery.business.brewing.entity;

public class Volume implements Comparable<Volume>{

    public enum VolumeEenheid {
        LITER;
    }
    
    private final int waarde;
    private final VolumeEenheid volumeEenheid;

    public Volume(int waarde, VolumeEenheid volumeEenheid) {
        this.waarde = waarde;
        this.volumeEenheid = volumeEenheid;
    }
    
    public Volume plus(Volume extraVolume) {
        return new Volume(waarde + extraVolume.waarde, volumeEenheid);
    }

    public int compareTo(Volume o) {
        return waarde < o.waarde ? -1 : waarde > o.waarde ? 1 : 0;
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
        if (this.waarde != other.waarde) {
            return false;
        }
        if (this.volumeEenheid != other.volumeEenheid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Volume{" + "waarde=" + waarde + ", volumeEenheid=" + volumeEenheid + '}';
    }
}
