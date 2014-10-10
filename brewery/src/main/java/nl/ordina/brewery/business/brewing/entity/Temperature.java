package nl.ordina.brewery.business.brewing.entity;

public class Temperature {


  public enum Schaal {
    CELSIUS;
  }

  private final int waarde;
  private final Schaal schaal;

  public static Temperature ofDegrees(int degrees, Schaal scale) {
    return new Temperature(degrees, scale);
  }

  public Temperature(int waarde, Schaal schaal) {
    this.waarde = waarde;
    this.schaal = schaal;
  }

  public Temperature plus(Temperature verhoging) {
    return new Temperature(waarde + verhoging.waarde, schaal);
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
    final Temperature other = (Temperature) obj;
    if (this.schaal != other.schaal) {
      return false;
    }
    if (this.waarde != other.waarde) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "Temperatuur{" + "waarde=" + waarde + ", schaal=" + schaal + '}';
  }

}
