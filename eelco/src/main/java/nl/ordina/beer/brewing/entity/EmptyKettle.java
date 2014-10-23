
package nl.ordina.beer.brewing.entity;

import nl.ordina.beer.entity.Kettle;

public class EmptyKettle extends BrewAction {

    @Override
    public void executeFor(Kettle kettle) {
        kettle.flush();
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }
    
}
