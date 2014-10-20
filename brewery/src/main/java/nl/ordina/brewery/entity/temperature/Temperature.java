package nl.ordina.brewery.entity.temperature;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlAccessorType(XmlAccessType.FIELD)
public class Temperature {

    public enum TemperatureUnit {

        @XmlEnumValue("celsius")
        CELSIUS
    }

    private int value;
    private TemperatureUnit unit;

    public static Temperature of(int degrees, TemperatureUnit scale) {
        return new Temperature(degrees, scale);
    }

    public Temperature() {
        // Required by JAXB
    }

    public Temperature(int value, TemperatureUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    public Temperature difference(Temperature goal) {
        assert (this.unit == goal.unit);
        return new Temperature(goal.value - this.value, this.unit);
    }

    public Temperature plus(Temperature verhoging) {
        return new Temperature(value + verhoging.value, unit);
    }

    public int getValue() {
        return value;
    }

    public TemperatureUnit getUnit() {
        return unit;
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
        final Temperature other = (Temperature) obj;
        if (this.unit != other.unit) {
            return false;
        }
        return this.value == other.value;
    }

    @Override
    public String toString() {
        return "Temperature{" + value + " " + unit + '}';
    }

}
