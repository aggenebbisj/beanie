package nl.ordina.brewery.entity.capacity;

import nl.ordina.brewery.entity.capacity.Volume;
import org.junit.Test;

import static nl.ordina.brewery.entity.capacity.Volume.VolumeUnit.LITER;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class VolumeTest {
    
    @Test
    public void test_comparable_interface() {
        Volume klein = new Volume(10, LITER);
        Volume groot = new Volume(100, LITER);
        assertThat(klein.compareTo(groot), is(-1));
        assertThat(groot.compareTo(klein), is(1));
        assertThat(klein.compareTo(klein), is(0));
    }
    
}
