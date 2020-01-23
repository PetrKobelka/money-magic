package cz.petrkobelka.study.moneymagic.persist;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * tests for in-memory state holder
 *
 * @author Petr Kobelka, info@petrkobelka.cz
 * @since 1.0
 */
class InMemoryStateTest {

    @Test
    void getInstance_withoutAnyData() {
        assertNotNull(InMemoryState.getInstance());
    }

    @Test
    void add() {
        final Map<String, Float> data = new HashMap<>();
        data.put("USD", 7.85f);
        data.put("CZK", 25000f);

        final InMemoryState state = InMemoryState.getInstance();
        state.add(data);

        assertEquals(2, state.getData().size());
        assertFalse(state.getData().containsKey("EUR"));
        assertTrue(state.getData().containsKey("CZK"));
        assertTrue(state.getData().containsKey("USD"));
        assertEquals(7.85f, state.getData().get("USD"));
        assertEquals(25000f, state.getData().get("CZK"));

        state.add("EUR", 27.45f);
        assertEquals(3, state.getData().size());
        assertTrue(state.getData().containsKey("EUR"));
        assertEquals(27.45f, state.getData().get("EUR"));

        state.add("EUR", 0.05f);
        assertEquals(3, state.getData().size());
        assertEquals(27.50f, state.getData().get("EUR"));
    }

}
