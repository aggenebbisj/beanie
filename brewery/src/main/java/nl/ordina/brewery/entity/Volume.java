package nl.ordina.brewery.entity;

import javax.xml.bind.annotation.XmlEnumValue;

public class Volume implements Comparable<Volume> {

    public enum VolumeUnit {
        @XmlEnumValue("liter")
        LITER
    }

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

    public void setValue(int value) {
        this.value = value;
    }

    public VolumeUnit getUnit() {
        return unit;
    }

    public void setUnit(VolumeUnit unit) {
        this.unit = unit;
    }

    @Override
    public int compareTo(Volume o) {
        return value < o.value ? -1 : value > o.value ? 1 : 0;
    }

    @Override
    public int hashCode() {
        return 7;
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

}
