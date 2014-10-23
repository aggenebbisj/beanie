
package nl.ordina.beer.automaticbrewing.control;

import nl.ordina.beer.brewing.control.Brewer;

public interface RecipeStep<T> {
    
    void executeStep(Brewer brewer);

}
