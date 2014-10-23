
package nl.ordina.beer.brewing.control;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

public class BrewerProducer {

    @Produces 
    @Automatic
    @ApplicationScoped
    public Brewer createAutomaticBrewer(Brewer brewer) {
//        brewer.kettle.setName("UberKettle 2000");
        return brewer;
    }

    @Produces 
    @Manual
    @ApplicationScoped
    public Brewer createManualBrewer(Brewer brewer) {
//        brewer.kettle.setName("Ye Olde Kettle");
        return brewer;
    }
}
