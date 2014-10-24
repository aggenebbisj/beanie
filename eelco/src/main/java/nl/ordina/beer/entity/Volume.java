package nl.ordina.beer.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlAccessorType(XmlAccessType.FIELD)
public class Volume implements Comparable<Volume> {

    private int value;

    private VolumeUnit unit;

    public Volume() {
        // Required by JAXB
    }

    public Volume(int value, VolumeUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    public Volume plus(Volume extraVolume) {
        return new Volume(value + extraVolume.value, unit);
    }

    public int getValue() {
        return value;
    }

    public VolumeUnit getUnit() {
        return unit;
    }

    @Override
    public int compareTo(Volume o) {
        return value < o.value ? -1 : value > o.value ? 1 : 0;
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
        return this.unit == other.unit;
    }

    @Override
    public String toString() {
        return "Volume{" + "value=" + value + ", unit=" + unit + '}';
    }

    public enum VolumeUnit {
        @XmlEnumValue("liter")
        LITER
    }

}
