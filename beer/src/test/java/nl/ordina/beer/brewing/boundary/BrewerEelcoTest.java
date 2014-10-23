
package nl.ordina.beer.brewing.boundary;

import java.time.Duration;
import static java.time.temporal.ChronoUnit.SECONDS;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.enterprise.event.Observes;
import org.junit.Test;

public class BrewerEelcoTest {
    
    @Test
    public void brew(){
//        EelcoBrewer brewer = new EelcoBrewer();
//        brewer.brew();
    }

//    @Stateless
//    @Startup
//    class EelcoBrewer{
//        final Kettle kettle;
//        Queue<Action> actions = new ConcurrentLinkedQueue();
//
//        @Resource
//        TimerService timerService;
//        
//        void addAction(Action action) {
//            actions.add(action);            
//        }
//
//        @PostConstruct
//        @Timeout
//        void brew(){
//            while (!actions.isEmpty()) {
//                actions.remove().execute(kettle);
//            }
//            timerService.createSingleActionTimer(Duration.of(1, SECONDS).getSeconds(), null);
//        }
//        
//        void observe(@Observes Action event){
//            actions.add(event);
//            executeActions();
//        }
//        
//    }
//     class Kettle{
//         
//     }
//    
//    interface Action{
//        void execute(Kettle kettle);
//    }
}
