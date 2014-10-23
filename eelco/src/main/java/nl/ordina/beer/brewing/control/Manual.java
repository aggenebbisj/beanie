package nl.ordina.beer.brewing.control;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.inject.Qualifier;

/**
 * Used to differentiate between brewing recipes and manual brewing
 * Manual means we are brewing manually via the web interface
 * 
 * @author Remko de Jong
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD, ElementType.TYPE})
@Qualifier
public @interface Manual {
    
}
