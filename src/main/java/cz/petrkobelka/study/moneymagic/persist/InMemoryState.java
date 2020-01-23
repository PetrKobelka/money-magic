package cz.petrkobelka.study.moneymagic.persist;

import java.util.HashMap;
import java.util.Map;

/**
 * to hold data about currencies and balance, singleton pattern
 *
 * @author Petr Kobelka, info@petrkobelka.cz
 * @since 1.0
 */
public class InMemoryState {
    /**
     * hold data about currencies and amounts
     */
    private Map<String, Float> data = new HashMap<>();
    /**
     * singleton instance
     */
    private static InMemoryState instance;

    private InMemoryState() {
    }

    /**
     * get instance of in-memory state. Object is created only if it is needed
     *
     * @return instance of actual memory state
     */
    public static InMemoryState getInstance() {

        if (instance == null) {
            instance = new InMemoryState();
        }
        return instance;
    }

    /**
     * load data from map to state, commonly used from init file
     *
     * @param loadedValues values loadded in some kind of batch
     */
    public void add(Map<String, Float> loadedValues) {
        loadedValues.entrySet().forEach(
                entrySet -> getInstance().add(entrySet.getKey(), entrySet.getValue())
        );
    }

    /**
     * add record into state
     *
     * @param currency
     * @param amount
     */
    public void add(String currency, Float amount) {
        if (!data.containsKey(currency)) {
            data.put(currency, amount);
        } else {
            data.put(currency, data.get(currency) + amount);
            /*
            // variant for remove empty ballance == 0, need more think
            if (data.get(currency) + amount == 0) {
                data.remove(currency);
            } else {
                data.put(currency, data.get(currency) + amount);
            }
            */
        }

    }

    /**
     * @return return current data in evidence
     */
    public Map<String, Float> getData() {
        return data;
    }

}
