package nl.ordina.beer.control;

import java.time.Duration;
import static java.time.temporal.ChronoUnit.MINUTES;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.enterprise.event.Event;
import nl.ordina.beer.control.KitchenTimer.Holder;
import nl.ordina.beer.entity.Kettle;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class KitchenTimerTest {

    @InjectMocks
    private KitchenTimer sut;

    @Mock
    private TimerService timerService;

    @Mock
    private Timer timer;

    @Mock
    private Event<KitchenTimerExpiredEvent> event;
    
    private Kettle kettle = new Kettle();

    @Test
    public void setting_timer_should_trigger_timerService() {
        sut.setFor(Duration.of(30, MINUTES), kettle);
        Mockito.verify(timerService).createSingleActionTimer(Mockito.anyLong(), Mockito.anyObject());
    }

    @Test
    public void should_fire_event_when_timer_expires() {
        Mockito.when(timer.getInfo()).thenReturn(new Holder(kettle));
        sut.timeout(timer);
        Mockito.verify(event).fire(new KitchenTimerExpiredEvent(kettle));
    }

    @Test
    public void should_unlock_kettle_if_timer_expires() {
        kettle.lock();
        Mockito.when(timer.getInfo()).thenReturn(new Holder(kettle));
        sut.timeout(timer);
        assertFalse(kettle.isLocked());
    }

}
