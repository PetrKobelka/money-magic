package cz.petrkobelka.study.moneymagic.scheduled;

import cz.petrkobelka.study.moneymagic.persist.InMemoryState;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimerTask;

/**
 * simple task to output current state for all data (currency + amount)
 * we can also use another libraries from third party like Quartz
 *
 * @author Petr Kobelka, info@petrkobelka.cz
 * @since 1.0
 */
@NoArgsConstructor
public class ListOutput extends TimerTask {

    @Override
    public void run() {
        final InMemoryState instance = InMemoryState.getInstance();
        System.out.println("===========================================");
        System.out.println("=============   CURRENT STATE   ===========");
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        System.out.println("-------------------------------------------");
        instance.getData().entrySet().forEach(stringFloatEntry -> {
            System.out.println(stringFloatEntry.getKey() + "\t\t" + stringFloatEntry.getValue());
        });
        System.out.println("===========================================");
    }
}
