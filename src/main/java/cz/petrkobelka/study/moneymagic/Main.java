package cz.petrkobelka.study.moneymagic;

import cz.petrkobelka.study.moneymagic.file.FileParser;
import cz.petrkobelka.study.moneymagic.parser.ArgumentParser;
import cz.petrkobelka.study.moneymagic.parser.CurrencyLineParser;
import cz.petrkobelka.study.moneymagic.persist.InMemoryState;
import cz.petrkobelka.study.moneymagic.scheduled.ListOutput;
import cz.petrkobelka.study.moneymagic.validator.CurrencyLineValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Timer;

/**
 * Main application class. How to run check readme.md
 *
 * @author Petr Kobelka, info@petrkobelka.cz
 * @since 1.0
 */
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    /**
     * automatic output of memory will be written every n sec
     */
    private static final int OUTPUT_PER_SEC = 60;
    /**
     * command to quit application
     */
    private static final String QUIT_CMD = "quit";

    /**
     * main method to run whole application
     *
     * @param args input arguments for processing
     */
    public static void main(String[] args) {

        System.out.println("Welcome to MagicMoney");
        System.out.println("Scheduled balance shows every " + OUTPUT_PER_SEC + " sec");

        final InMemoryState inMemoryState = InMemoryState.getInstance();

        // incoming arguments
        LOGGER.debug("Count of arguments {}", args.length);
        for (int i = 0; i < args.length; i++) {
            LOGGER.debug(String.format("%d. %s", i, args[i]));
        }

        if (args.length > 0) {
            LOGGER.debug("Try processing arguments --------------");
            final ArgumentParser argumentParser = new ArgumentParser();
            argumentParser.parse(args);

            if (argumentParser.getInitFile() != null) {
                LOGGER.debug("Loaded values --------- ");
                final FileParser fileParser = new FileParser();
                final Map<String, Float> loadedData = fileParser.loadData(argumentParser.getInitFile());
                for (Map.Entry<String, Float> entry : loadedData.entrySet()) {
                    LOGGER.debug(String.format("%s -> %s", entry.getKey(), entry.getValue()));
                }
                inMemoryState.add(loadedData);
            }
        }

        startTimedTasks();
        handleUserInput();

        LOGGER.debug("End application");
    }

    /**
     * star timed output of values in state
     */
    private static void startTimedTasks() {
        // start output task
        final ListOutput listOutputTast = new ListOutput();
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(listOutputTast, 0, OUTPUT_PER_SEC * 1000);
    }

    /**
     * method to handle data from user input
     */
    private static void handleUserInput() {
        boolean loop = true;

        final InMemoryState inMemoryState = InMemoryState.getInstance();
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final CurrencyLineParser currencyLineParser = new CurrencyLineParser();

        while (loop) {
            try {
                final String line = reader.readLine();

                if (QUIT_CMD.equals(line)) {
                    System.out.println("Exiting application. Thank you for using me.");
                    LOGGER.debug("{} command appeared.", QUIT_CMD);
                    loop = false;
                } else {
                    final String[] userData = currencyLineParser.parse(line);
                    final List<String> errors = CurrencyLineValidator.check(userData);
                    if (errors.isEmpty()) {
                        inMemoryState.add(userData[0], Float.parseFloat(userData[1]));
                        System.out.println("Success: Added " + line);
                    } else {
                        errors.forEach(s -> {
                            System.out.println("Error: " + s);
                        });
                    }
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

}
