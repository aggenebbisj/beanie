package nl.ordina.brewery.entity;

public class Temperature {


  public Temperature difference(Temperature goal) {
    assert(this.unit == goal.unit);
    return new Temperature(goal.value - this.value, this.unit);
  }

  public enum TemperatureUnit {
    CELSIUS
  }

  private final int value;
  private final TemperatureUnit unit;

  public static Temperature of(int degrees, TemperatureUnit scale) {
    return new Temperature(degrees, scale);
  }

  public Temperature(int value, TemperatureUnit unit) {
    this.value = value;
    this.unit = unit;
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
