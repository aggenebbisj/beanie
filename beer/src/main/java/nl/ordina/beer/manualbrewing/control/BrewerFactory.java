
package nl.ordina.beer.manualbrewing.control;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import nl.ordina.brewery.entity.Automatic;
import nl.ordina.brewery.entity.Manual;

public class BrewerFactory {

    @Produces 
    @Automatic
    @ApplicationScoped
    public Brewer createAutomaticBrewer(Brewer brewer) {
        brewer.kettle.setName("UberKettle 2000");
        return brewer;
    }

    @Produces 
    @Manual
    @ApplicationScoped
    public Brewer createManualBrewer(Brewer brewer) {
        brewer.kettle.setName("Ye Olde Kettle");
        return brewer;
    }
}
