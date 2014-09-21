package nl.ordina.brewery.business.brewing.entity;

public class Temperatuur {

    public enum Schaal {
        CELSIUS,
    }
    
    private final int waarde;
    private final Schaal schaal;

    public Temperatuur(int waarde, Schaal schaal) {
        this.waarde = waarde;
        this.schaal = schaal;
    }

    public Temperatuur plus(Temperatuur verhoging) {        
        return new Temperatuur(waarde + verhoging.waarde, schaal);
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
        final Temperatuur other = (Temperatuur) obj;
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
