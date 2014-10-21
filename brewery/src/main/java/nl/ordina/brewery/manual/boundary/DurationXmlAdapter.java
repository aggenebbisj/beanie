package nl.ordina.brewery.manual.boundary;

import java.time.Duration;
import static java.time.temporal.ChronoUnit.MINUTES;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.bind.annotation.adapters.XmlAdapter;

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
