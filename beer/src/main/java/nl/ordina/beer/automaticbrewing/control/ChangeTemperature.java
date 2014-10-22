
package nl.ordina.beer.automaticbrewing.control;

import java.util.Objects;
import nl.ordina.beer.entity.Temperature;
import nl.ordina.beer.manualbrewing.control.Brewer;

public class ChangeTemperature implements RecipeStep<Temperature>{
    private final Temperature temperature;

    public ChangeTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    @Override
    public void executeStep(Brewer brewer) {
        brewer.changeTemperatureTo(temperature);
    }

    @Override
    public String toString() {
        return "ChangeTemperature{" + "temperature=" + temperature + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final ChangeTemperature other = (ChangeTemperature) obj;
        if (!Objects.equals(this.temperature, other.temperature)) {
            return false;
        }
        return true;
    }

}
