package nl.ordina.beer.brewing.boundary;

import java.time.Duration;
import static java.time.temporal.ChronoUnit.MINUTES;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapter to unmarshal and marshal <code>java.time.Duration</code> objects. 
 * JAXB does not not have out of the box support for this yet.
 * 
 * @author Ordina J-Tech
 */
public class DurationXmlAdapter extends XmlAdapter<String, Duration> {

    @Override
    public Duration unmarshal(String value) {
        try {
            Matcher m = Pattern.compile("(\\d+)").matcher(value);
            m.find();
            return Duration.of(Long.parseLong(m.group()), MINUTES);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String marshal(Duration value) {
        try {
            return String.format("{ \"duration\":\"%s\" }", value.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
